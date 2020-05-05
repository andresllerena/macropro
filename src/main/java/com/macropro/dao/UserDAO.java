package com.macropro.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.macropro.enums.UserRole;
import com.macropro.model.User;
import com.macropro.model.UserAuthority;
import com.macropro.model.UserGoal;
import com.macropro.model.UserMacroPlan;

@Repository
public class UserDAO implements IUserDAO {

	@Autowired Logger logger;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsers() {
		Session current = sessionFactory.getCurrentSession();
		List<User> users = current.createQuery("from User", User.class).getResultList();
		return users;
	}

	@Override
	public void addUser(User newUser) {
		Session current = sessionFactory.getCurrentSession();
		UserAuthority gns = (UserAuthority) current.get(UserAuthority.class, 1); // id = 1 --> "USER_GNS"
		String encodedPassword  = passwordEncoder.encode(newUser.getPassword());
		newUser.setPassword(encodedPassword);
		newUser.setMatchingPassword(encodedPassword);
		newUser.setEnabled(true);
		newUser.addUserAuthority(gns);
		current.saveOrUpdate(newUser);
	}

	@Override
	public boolean usernameExists(String username) {
		Session current = sessionFactory.getCurrentSession();
		User user = current.createQuery("from User where username=:username", User.class)
				.setParameter("username", username)
				.uniqueResult();
		logger.debug("Username (" + username + ") exists in database: " + (user != null));
		return (user != null);
	}
	
	@Override
	public User findByUsername(String username) {
		Session current = sessionFactory.getCurrentSession();
		User user = current.createQuery("from User where username=:username", User.class)
				.setParameter("username", username)
				.uniqueResult();
		logger.debug("Username (" + username + ") exists in database: " + (user != null));
		return user;
	}

	@Override
	public boolean emailExists(String email) {
		Session current = sessionFactory.getCurrentSession();
		User user = current.createQuery("from User where email=:email", User.class)
				.setParameter("email", email)
				.uniqueResult();
		logger.debug("Email (" + email + ") exists in database: " + (user != null));
		return (user != null);
	}

	@Override
	public User addGoalAndMacroPlan(String username, UserGoal userGoal) {
		Session current = sessionFactory.getCurrentSession();
		
		User currentUser = current.createQuery("from User where username=:username", User.class)
				.setParameter("username", username)
				.uniqueResult();
		
		UserGoal prevUserGoal = currentUser.getGoal();
		UserMacroPlan prevMacroPlan = currentUser.getMacroPlan();
		
		currentUser.setGoal(userGoal);
		currentUser.setMacroPlan(User.generateMacroPlan(userGoal));
		
		// if user has not submitted a goal before
		if (!currentUser.containsUserAuthority(new UserAuthority(UserRole.USER_GOAL_SUBMITTED.getRole()))) {
			UserAuthority gns = (UserAuthority) current.get(UserAuthority.class, 1); // id = 1 --> "USER_GS"
			UserAuthority gs = (UserAuthority) current.get(UserAuthority.class, 2); // id = 2 --> "USER_GS"
			
			currentUser.addUserAuthority(gs);
			currentUser.removeUserAuthority(gns);
			currentUser.setSubmittedGoal(true);
		} 
		// if user has previously submitted a goal
		else {			
			current.delete(prevUserGoal);
			current.delete(prevMacroPlan);
		}
		current.saveOrUpdate(currentUser);
		return currentUser;
	}
}
