package com.macropro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macropro.dao.IFoodDAO;
import com.macropro.dao.IUserDAO;
import com.macropro.model.Food;
import com.macropro.model.FoodLog;
import com.macropro.model.FoodLogEntry;
import com.macropro.model.User;

@Service
public class FoodService implements IFoodService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IFoodDAO foodDAO;

	@Transactional (rollbackFor = Exception.class)
	@Override
	public User addFood(String username, Food food) {
		User user = userDAO.findByUsername(username);
		return foodDAO.addFood(user, food);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public FoodLog getFoodLog(LocalDate date, User currentUser) {
		return foodDAO.getFoodLog(date, currentUser);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public FoodLog addFoodLog(LocalDate date, User currentUser) {
		return foodDAO.addFoodLog(date, currentUser);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public List<FoodLogEntry> getFoodLogEntries(LocalDate date, User currentUser) {
		return foodDAO.getFoodLogEntries(date, currentUser);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void addFoodsToLog(List<FoodLogEntry> entries, int meal, LocalDate date, User currentUser) {
		foodDAO.addFoodsToLog(entries, meal, date, currentUser);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void removeFoodFromLog(int entryIndex, int meal, LocalDate date, User currentUser) {
		foodDAO.removeFoodFromLog(entryIndex, meal, date, currentUser);
	}
}
