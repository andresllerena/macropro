package com.macropro.dao;

import java.util.List;

import com.macropro.model.User;
import com.macropro.model.UserGoal;

public interface IUserDAO {

	public List<User> getUsers();

	public void addUser(User newUser);

	public boolean usernameExists(String username);

	public boolean emailExists(String email);

	public User findByUsername(String username);

	public User addGoalAndMacroPlan(String username, UserGoal newUserGoal);
	
}
