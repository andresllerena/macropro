package com.macropro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macropro.dao.IUserDAO;
import com.macropro.model.User;
import com.macropro.model.UserGoal;

@Service
public class UserService implements IUserService {

	private final String ERROR_MESSAGE = "User already exists";
	
	@Autowired
	private IUserDAO userDAO;
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Transactional (rollbackFor = {Exception.class, UserExistsException.class})
	@Override
	public void addUser(User newUser) throws UserExistsException {
		Set<String> exceptionSet = new HashSet<String>();
		
		if (usernameExists(newUser.getUsername()))
			exceptionSet.add("username");
		
		if (emailExists(newUser.getEmail()))
			exceptionSet.add("email");
		
		if (!exceptionSet.isEmpty())
			throw new UserExistsException(ERROR_MESSAGE, exceptionSet, new Exception(ERROR_MESSAGE));
		
		userDAO.addUser(newUser);
	}

	private boolean emailExists(String email) {
		return userDAO.emailExists(email);
		
	}

	private boolean usernameExists(String username) {
		return userDAO.usernameExists(username);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public User addGoalAndMacroPlan(String username, UserGoal newUserGoal) {
		return userDAO.addGoalAndMacroPlan(username, newUserGoal);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
}
