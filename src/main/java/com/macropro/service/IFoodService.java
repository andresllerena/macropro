package com.macropro.service;

import java.time.LocalDate;
import java.util.List;

import com.macropro.model.Food;
import com.macropro.model.FoodLog;
import com.macropro.model.FoodLogEntry;
import com.macropro.model.User;

public interface IFoodService {

	public User addFood(String username, Food food);

	public FoodLog getFoodLog(LocalDate date, User currentUser);

	public FoodLog addFoodLog(LocalDate date, User currentUser);

	public List<FoodLogEntry> getFoodLogEntries(LocalDate date, User currentUser);

	public void addFoodsToLog(List<FoodLogEntry> selected, int meal, LocalDate date, User currentUser);

	public void removeFoodFromLog(int entryIndex, int meal, LocalDate date, User currentUser);

	public User deleteFood(int id);

	public Food getFood(int id);

	public User updateFood(Food food, User currentUser);
	
}
