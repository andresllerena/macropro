package com.macropro.dao;

import java.time.LocalDate;
import java.util.List;

import com.macropro.model.Food;
import com.macropro.model.FoodLog;
import com.macropro.model.FoodLogEntry;
import com.macropro.model.User;

public interface IFoodDAO {

	User addFood(User user, Food food);

	FoodLog getFoodLog(LocalDate date, User currentUser);

	FoodLog addFoodLog(LocalDate date, User currentUser);

	List<FoodLogEntry> getFoodLogEntries(LocalDate date, User currentUser);

	void addFoodsToLog(List<FoodLogEntry> entries, int meal, LocalDate date, User currentUser);

	void removeFoodFromLog(int entryIndex, int meal, LocalDate date, User currentUser);

	User deleteFood(int id);

	Food getFood(int id);

	Food updateFood(Food food, User currentUser);

	User addFood(Food food);

}
