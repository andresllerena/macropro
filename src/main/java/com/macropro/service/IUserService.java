package com.macropro.service;

import java.util.List;

import com.macropro.model.User;
import com.macropro.model.UserGoal;

public interface IUserService {

	public List<User> getUsers();

	public void addUser(User newUser) throws UserExistsException;

	public User addGoalAndMacroPlan(String username, UserGoal newUserGoal);

	public User findByUsername(String username);
	
}
