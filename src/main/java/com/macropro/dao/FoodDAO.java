package com.macropro.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.macropro.model.Food;
import com.macropro.model.FoodLog;
import com.macropro.model.FoodLogEntry;
import com.macropro.model.User;

@Repository
public class FoodDAO implements IFoodDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User addFood(User user, Food food) {
		Session current = sessionFactory.getCurrentSession();
		food.setCreator(user);
		FoodLogEntry fle = new FoodLogEntry(food, Double.valueOf(1.0));
		current.merge(fle);
		return user;
	}
	
	@Override
	public User addFood(Food food) {
		Session current = sessionFactory.getCurrentSession();
		FoodLogEntry fle = new FoodLogEntry(food, Double.valueOf(1.0));
		current.merge(fle);
		return food.getCreator();
	}

	@Override
	public FoodLog getFoodLog(LocalDate date, User currentUser) {
		Session current = sessionFactory.getCurrentSession();
		
		/*
		 * FoodLog log =
		 * current.createQuery("select f from FoodLog f inner join f.user u" +
		 * " where u.id= :id and f.date= :date", FoodLog.class)
		 */
		FoodLog log = current.createQuery("from FoodLog where user_id= :userid and date= :seldate", FoodLog.class)
		.setParameter("userid", currentUser.getId())
		.setParameter("seldate", date)
		.uniqueResult();
		
		return log;
	}

	@Override
	public FoodLog addFoodLog(LocalDate date, User currentUser) {
		Session current = sessionFactory.getCurrentSession();
		FoodLog log = new FoodLog(date, currentUser);
		current.merge(log);
		return log;
	}

	@Override
	public List<FoodLogEntry> getFoodLogEntries(LocalDate date, User currentUser) {
		Session current = sessionFactory.getCurrentSession();
		List<FoodLogEntry> foodLogEntries = new ArrayList<>();
		List<FoodLogEntry> myFoods = current.createQuery("select f from FoodLogEntry f where f.food.creator.id = :id and f.numberOfServings= :numServings", FoodLogEntry.class)
									.setParameter("id", currentUser.getId())
									.setParameter("numServings", 1.0)
									.list();
		/*List<FoodLogEntry> privateFoods = current.createQuery("select f from FoodLogEntry f where f.food.creator.id = :id and f.food.publicFlag= :publicflag and f.numberOfServings= :numServings", FoodLogEntry.class)
									.setParameter("id", currentUser.getId())
									.setParameter("publicflag", false)
									.setParameter("numServings", 1.0)
									.list();
		List<FoodLogEntry> publicFoods = current.createQuery("select f from FoodLogEntry f where f.food.publicFlag= :publicflag and f.numberOfServings= :numServings", FoodLogEntry.class)
									.setParameter("publicflag", true)
									.setParameter("numServings", 1.0)
									.list();
		foodLogEntries.addAll(privateFoods);
		foodLogEntries.addAll(publicFoods);*/
		foodLogEntries.addAll(myFoods);
		
		return foodLogEntries;
	}

	@Override
	public void addFoodsToLog(List<FoodLogEntry> entries, int meal, LocalDate date, User currentUser) {
		Session current = sessionFactory.getCurrentSession();
		
		FoodLog log = current.createQuery("from FoodLog where date=:date and user_id=:id", FoodLog.class)
				.setParameter("date", date)
				.setParameter("id", currentUser.getId())
				.uniqueResult();
		
		// for each entry, check if FoodLogEntry exists for
		// Food id and numberOfServings
		for (FoodLogEntry entry : entries) {
			FoodLogEntry completeEntry = (FoodLogEntry) current.get(FoodLogEntry.class, entry.getId());
			Food f = completeEntry.getFood();
			
			FoodLogEntry relevantEntry = current.createQuery("from FoodLogEntry where food_id=:foodId and num_servings=:quantity", FoodLogEntry.class)
					.setParameter("foodId", f.getId())
					.setParameter("quantity", entry.getNumberOfServings())
					.uniqueResult();
			
			// if entry does not exist, create new entry
			// otherwise, use existing entry
			if (relevantEntry == null) {
				relevantEntry = new FoodLogEntry(f, entry.getNumberOfServings());
			}
			
			// add entry to log for given meal
			if (meal == 0) {
				log.addToBreakfast(relevantEntry);
			} else if (meal == 1) {
				log.addToLunch(relevantEntry);
			} else if (meal == 2) {
				log.addToDinner(relevantEntry);
			} else if (meal == 3) {
				log.addToSnackOne(relevantEntry);
			} else if (meal == 4) {
				log.addToSnackTwo(relevantEntry);
			} else {
				log.addToSnackThree(relevantEntry);
			}
			
			current.persist(log);
		}
	}

	@Override
	public void removeFoodFromLog(int entryIndex, int meal, LocalDate date, User currentUser) {
		Session current = sessionFactory.getCurrentSession();
		
		FoodLog log = current.createQuery("from FoodLog where date=:date and user_id=:id", FoodLog.class)
				.setParameter("date", date)
				.setParameter("id", currentUser.getId())
				.uniqueResult();
		//FoodLogEntry entry = (FoodLogEntry) current.get(FoodLogEntry.class, entryId);
		
		// remove entry from log for given meal
		if (meal == 0) {
			log.removeFromBreakfast(entryIndex);
		} else if (meal == 1) {
			log.removeFromLunch(entryIndex);
		} else if (meal == 2) {
			log.removeFromDinner(entryIndex);
		} else if (meal == 3) {
			log.removeFromSnackOne(entryIndex);
		} else if (meal == 4) {
			log.removeFromSnackTwo(entryIndex);
		} else {
			log.removeFromSnackThree(entryIndex);
		}
		
		current.update(log);
	}

	@Override
	public User deleteFood(int id) {
		Session current = sessionFactory.getCurrentSession();
		Food f = (Food) current.get(Food.class, id);
		User u = f.getCreator();
		u.deleteFood(f);
		current.persist(u);
		return u;
	}

	@Override
	public Food getFood(int id) {
		Session current = sessionFactory.getCurrentSession();
		Food f = (Food) current.get(Food.class, id);
		return f;
	}

	@Override
	public Food updateFood(Food food, User currentUser) {
		Session current = sessionFactory.getCurrentSession();
		Food f = (Food) current.get(Food.class, food.getId());
		
		if (f.equals(food))
			return null;
		

		Food newFood = new Food(f.getBrand(), f.getName(), food.getServingSize(), food.getUnitOfMeasurement(), 
								food.getCalories(), food.getCarbsGrams(), food.getFatGrams(), food.getProteinGrams(), 
								food.getFiberGrams(), f.isPublicFlag(), f.getCreator());
		
		f.removeCreator(f.getCreator());
		
		// update all entries 
//		List<FoodLogEntry> entries = current.createQuery("select f from FoodLogEntry f where f.food.id = :id", FoodLogEntry.class)
//				.setParameter("id", f.getId())
//				.list();
//		
//		for (FoodLogEntry entry : entries) {
//			entry.setFood(f);
//			current.persist(entry);
//		}
		
		current.persist(f);
		
		return newFood;
	}

}
