package com.macropro.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.macropro.util.DoubleUtil;

@Entity
@Table(name="food_log")
public class FoodLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;

	@Column(name="date")
	public LocalDate date;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	public User user;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "foodlog_food_breakfast",
				joinColumns = {@JoinColumn(name = "food_log_id")},
				inverseJoinColumns = {@JoinColumn(name = "food_log_entry_id")})
	@OrderColumn(name="list_index")
	public List<FoodLogEntry> breakfast = new ArrayList<FoodLogEntry>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "foodlog_food_lunch",
				joinColumns = {@JoinColumn(name = "food_log_id")},
				inverseJoinColumns = {@JoinColumn(name = "food_log_entry_id")})
	@OrderColumn(name="list_index")
	public List<FoodLogEntry> lunch = new ArrayList<FoodLogEntry>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "foodlog_food_dinner",
				joinColumns = {@JoinColumn(name = "food_log_id")},
				inverseJoinColumns = {@JoinColumn(name = "food_log_entry_id")})
	@OrderColumn(name="list_index")
	public List<FoodLogEntry> dinner = new ArrayList<FoodLogEntry>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "foodlog_food_snackone",
				joinColumns = {@JoinColumn(name = "food_log_id")},
				inverseJoinColumns = {@JoinColumn(name = "food_log_entry_id")})
	@OrderColumn(name="list_index")
	public List<FoodLogEntry> snackOne = new ArrayList<FoodLogEntry>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "foodlog_food_snacktwo",
				joinColumns = {@JoinColumn(name = "food_log_id")},
				inverseJoinColumns = {@JoinColumn(name = "food_log_entry_id")})
	@OrderColumn(name="list_index")
	public List<FoodLogEntry> snackTwo = new ArrayList<FoodLogEntry>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "foodlog_food_snackthree",
				joinColumns = {@JoinColumn(name = "food_log_id")},
				inverseJoinColumns = {@JoinColumn(name = "food_log_entry_id")})
	@OrderColumn(name="list_index")
	public List<FoodLogEntry> snackThree = new ArrayList<FoodLogEntry>();

	@Column(name="breakfast_calories")
	public Double breakfastTotalCalories;
	@Column(name="breakfast_carbs")
	public Double breakfastTotalCarbs;
	@Column(name="breakfast_fat")
	public Double breakfastTotalFat;
	@Column(name="breakfast_protein")
	public Double breakfastTotalProtein;

	@Column(name="lunch_calories")
	public Double lunchTotalCalories;
	@Column(name="lunch_carbs")
	public Double lunchTotalCarbs;
	@Column(name="lunch_fat")
	public Double lunchTotalFat;
	@Column(name="lunch_protein")
	public Double lunchTotalProtein;

	@Column(name="dinner_calories")
	public Double dinnerTotalCalories;
	@Column(name="dinner_carbs")
	public Double dinnerTotalCarbs;
	@Column(name="dinner_fat")
	public Double dinnerTotalFat;
	@Column(name="dinner_protein")
	public Double dinnerTotalProtein;

	@Column(name="snackone_calories")
	public Double snackOneTotalCalories;
	@Column(name="snackone_carbs")
	public Double snackOneTotalCarbs;
	@Column(name="snackone_fat")
	public Double snackOneTotalFat;
	@Column(name="snackone_protein")
	public Double snackOneTotalProtein;

	@Column(name="snacktwo_calories")
	public Double snackTwoTotalCalories;
	@Column(name="snacktwo_carbs")
	public Double snackTwoTotalCarbs;
	@Column(name="snacktwo_fat")
	public Double snackTwoTotalFat;
	@Column(name="snacktwo_protein")
	public Double snackTwoTotalProtein;

	@Column(name="snackthree_calories")
	public Double snackThreeTotalCalories;
	@Column(name="snackthree_carbs")
	public Double snackThreeTotalCarbs;
	@Column(name="snackthree_fat")
	public Double snackThreeTotalFat;
	@Column(name="snackthree_protein")
	public Double snackThreeTotalProtein;

	public FoodLog() {
		
	}
	
	public FoodLog(LocalDate date, User user) {
		this.date = date;
		this.user = user;
		
		setDefaultTotalMacros();
	}

	private void setDefaultTotalMacros() {
		this.breakfastTotalCalories = Double.valueOf(0.0);
		this.breakfastTotalCarbs= Double.valueOf(0.0);
		this.breakfastTotalFat = Double.valueOf(0.0);
		this.breakfastTotalProtein = Double.valueOf(0.0);
		
		this.lunchTotalCalories = Double.valueOf(0.0);
		this.lunchTotalCarbs = Double.valueOf(0.0);
		this.lunchTotalFat = Double.valueOf(0.0);
		this.lunchTotalProtein = Double.valueOf(0.0);
		
		this.dinnerTotalCalories = Double.valueOf(0.0);
		this.dinnerTotalCarbs = Double.valueOf(0.0);
		this.dinnerTotalFat = Double.valueOf(0.0);
		this.dinnerTotalProtein = Double.valueOf(0.0);
		
		this.snackOneTotalCalories = Double.valueOf(0.0);
		this.snackOneTotalCarbs = Double.valueOf(0.0);
		this.snackOneTotalFat = Double.valueOf(0.0);
		this.snackOneTotalProtein = Double.valueOf(0.0);
		
		this.snackTwoTotalCalories = Double.valueOf(0.0);
		this.snackTwoTotalCarbs = Double.valueOf(0.0);
		this.snackTwoTotalFat = Double.valueOf(0.0);
		this.snackTwoTotalProtein = Double.valueOf(0.0);
		
		this.snackThreeTotalCalories = Double.valueOf(0.0);
		this.snackThreeTotalCarbs = Double.valueOf(0.0);
		this.snackThreeTotalFat = Double.valueOf(0.0);
		this.snackThreeTotalProtein = Double.valueOf(0.0);
		
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addToBreakfast(FoodLogEntry f) {
		this.breakfast.add(f);
		this.breakfastTotalCalories = Double.valueOf(DoubleUtil.round(this.breakfastTotalCalories.doubleValue() + f.getTotalCalories().doubleValue()));
		this.breakfastTotalCarbs = Double.valueOf(DoubleUtil.round(this.breakfastTotalCarbs.doubleValue() + f.getTotalCarbsGrams().doubleValue()));
		this.breakfastTotalFat = Double.valueOf(DoubleUtil.round(this.breakfastTotalFat.doubleValue() + f.getTotalFatGrams().doubleValue()));
		this.breakfastTotalProtein = Double.valueOf(DoubleUtil.round(this.breakfastTotalProtein.doubleValue() + f.getTotalProteinGrams().doubleValue()));
	}
	
	public void removeFromBreakfast(FoodLogEntry f) {
        Iterator<FoodLogEntry> itr = this.breakfast.iterator(); 
        while (itr.hasNext()) 
        { 
        	FoodLogEntry e = (FoodLogEntry) itr.next(); 
            if (e.equals(f)) {
                itr.remove(); 
                break;
            }
        }
	}
	
	public void removeFromBreakfast(int index) {
		FoodLogEntry f = this.breakfast.get(index);
		this.breakfast.remove(index);
		this.breakfastTotalCalories = Double.valueOf(DoubleUtil.round(this.breakfastTotalCalories.doubleValue() - f.getTotalCalories().doubleValue()));
		this.breakfastTotalCarbs = Double.valueOf(DoubleUtil.round(this.breakfastTotalCarbs.doubleValue() - f.getTotalCarbsGrams().doubleValue()));
		this.breakfastTotalFat = Double.valueOf(DoubleUtil.round(this.breakfastTotalFat.doubleValue() - f.getTotalFatGrams().doubleValue()));
		this.breakfastTotalProtein = Double.valueOf(DoubleUtil.round(this.breakfastTotalProtein.doubleValue() - f.getTotalProteinGrams().doubleValue()));
	}
	
	public List<FoodLogEntry> getBreakfast() {
		return this.breakfast;
	}
	
	public void addToLunch(FoodLogEntry f) {
		this.lunch.add(f);
		this.lunchTotalCalories = Double.valueOf(DoubleUtil.round(this.lunchTotalCalories.doubleValue() + f.getTotalCalories().doubleValue()));
		this.lunchTotalCarbs = Double.valueOf(DoubleUtil.round(this.lunchTotalCarbs.doubleValue() + f.getTotalCarbsGrams().doubleValue()));
		this.lunchTotalFat = Double.valueOf(DoubleUtil.round(this.lunchTotalFat.doubleValue() + f.getTotalFatGrams().doubleValue()));
		this.lunchTotalProtein = Double.valueOf(DoubleUtil.round(this.lunchTotalProtein.doubleValue() + f.getTotalProteinGrams().doubleValue()));
	}
	
	public void removeFromLunch(FoodLogEntry f) {
        Iterator<FoodLogEntry> itr = this.lunch.iterator(); 
        while (itr.hasNext()) 
        { 
        	FoodLogEntry e = (FoodLogEntry) itr.next(); 
            if (e.equals(f)) {
                itr.remove(); 
                break;
            }
        }
	}

	public void removeFromLunch(int index) {
		FoodLogEntry f = this.lunch.get(index);
		this.lunch.remove(index);
		this.lunchTotalCalories = Double.valueOf(DoubleUtil.round(this.lunchTotalCalories.doubleValue() - f.getTotalCalories().doubleValue()));
		this.lunchTotalCarbs = Double.valueOf(DoubleUtil.round(this.lunchTotalCarbs.doubleValue() - f.getTotalCarbsGrams().doubleValue()));
		this.lunchTotalFat = Double.valueOf(DoubleUtil.round(this.lunchTotalFat.doubleValue() - f.getTotalFatGrams().doubleValue()));
		this.lunchTotalProtein = Double.valueOf(DoubleUtil.round(this.lunchTotalProtein.doubleValue() - f.getTotalProteinGrams().doubleValue()));
	}
	
	public List<FoodLogEntry> getLunch() {
		return this.lunch;
	}

	public void addToDinner(FoodLogEntry f) {
		this.dinner.add(f);
		this.dinnerTotalCalories = Double.valueOf(DoubleUtil.round(this.dinnerTotalCalories.doubleValue() + f.getTotalCalories().doubleValue()));
		this.dinnerTotalCarbs = Double.valueOf(DoubleUtil.round(this.dinnerTotalCarbs.doubleValue() + f.getTotalCarbsGrams().doubleValue()));
		this.dinnerTotalFat = Double.valueOf(DoubleUtil.round(this.dinnerTotalFat.doubleValue() + f.getTotalFatGrams().doubleValue()));
		this.dinnerTotalProtein = Double.valueOf(DoubleUtil.round(this.dinnerTotalProtein.doubleValue() + f.getTotalProteinGrams().doubleValue()));
	}
	
	public void removeFromDinner(FoodLogEntry f) {
        Iterator<FoodLogEntry> itr = this.dinner.iterator(); 
        while (itr.hasNext()) 
        { 
        	FoodLogEntry e = (FoodLogEntry) itr.next(); 
            if (e.equals(f)) {
                itr.remove(); 
                break;
            }
        }
	}
	
	public void removeFromDinner(int index) {
		FoodLogEntry f = this.dinner.get(index);
		this.dinner.remove(index);
		this.dinnerTotalCalories = Double.valueOf(DoubleUtil.round(this.dinnerTotalCalories.doubleValue() - f.getTotalCalories().doubleValue()));
		this.dinnerTotalCarbs = Double.valueOf(DoubleUtil.round(this.dinnerTotalCarbs.doubleValue() - f.getTotalCarbsGrams().doubleValue()));
		this.dinnerTotalFat = Double.valueOf(DoubleUtil.round(this.dinnerTotalFat.doubleValue() - f.getTotalFatGrams().doubleValue()));
		this.dinnerTotalProtein = Double.valueOf(DoubleUtil.round(this.dinnerTotalProtein.doubleValue() - f.getTotalProteinGrams().doubleValue()));
	}
	
	public List<FoodLogEntry> getDinner() {
		return this.dinner;
	}

	public void addToSnackOne(FoodLogEntry f) {
		this.snackOne.add(f);
		this.snackOneTotalCalories = Double.valueOf(DoubleUtil.round(this.snackOneTotalCalories.doubleValue() + f.getTotalCalories().doubleValue()));
		this.snackOneTotalCarbs = Double.valueOf(DoubleUtil.round(this.snackOneTotalCarbs.doubleValue() + f.getTotalCarbsGrams().doubleValue()));
		this.snackOneTotalFat = Double.valueOf(DoubleUtil.round(this.snackOneTotalFat.doubleValue() + f.getTotalFatGrams().doubleValue()));
		this.snackOneTotalProtein = Double.valueOf(DoubleUtil.round(this.snackOneTotalProtein.doubleValue() + f.getTotalProteinGrams().doubleValue()));
	}
	
	public void removeFromSnackOne(FoodLogEntry f) {
        Iterator<FoodLogEntry> itr = this.snackOne.iterator(); 
        while (itr.hasNext()) 
        { 
        	FoodLogEntry e = (FoodLogEntry) itr.next(); 
            if (e.equals(f)) {
                itr.remove(); 
                break;
            }
        }
	}
	
	public void removeFromSnackOne(int index) {
		FoodLogEntry f = this.snackOne.get(index);
		this.snackOne.remove(index);
		this.snackOneTotalCalories = Double.valueOf(DoubleUtil.round(this.snackOneTotalCalories.doubleValue() - f.getTotalCalories().doubleValue()));
		this.snackOneTotalCarbs = Double.valueOf(DoubleUtil.round(this.snackOneTotalCarbs.doubleValue() - f.getTotalCarbsGrams().doubleValue()));
		this.snackOneTotalFat = Double.valueOf(DoubleUtil.round(this.snackOneTotalFat.doubleValue() - f.getTotalFatGrams().doubleValue()));
		this.snackOneTotalProtein = Double.valueOf(DoubleUtil.round(this.snackOneTotalProtein.doubleValue() - f.getTotalProteinGrams().doubleValue()));
	}
	
	public List<FoodLogEntry> getSnackOne() {
		return this.snackOne;
	}

	public void addToSnackTwo(FoodLogEntry f) {
		this.snackTwo.add(f);
		this.snackTwoTotalCalories = Double.valueOf(DoubleUtil.round(this.snackTwoTotalCalories.doubleValue() + f.getTotalCalories().doubleValue()));
		this.snackTwoTotalCarbs = Double.valueOf(DoubleUtil.round(this.snackTwoTotalCarbs.doubleValue() + f.getTotalCarbsGrams().doubleValue()));
		this.snackTwoTotalFat = Double.valueOf(DoubleUtil.round(this.snackTwoTotalFat.doubleValue() + f.getTotalFatGrams().doubleValue()));
		this.snackTwoTotalProtein = Double.valueOf(DoubleUtil.round(this.snackTwoTotalProtein.doubleValue() + f.getTotalProteinGrams().doubleValue()));
	}
	
	public void removeFromSnackTwo(FoodLogEntry f) {
        Iterator<FoodLogEntry> itr = this.snackTwo.iterator(); 
        while (itr.hasNext()) 
        { 
        	FoodLogEntry e = (FoodLogEntry) itr.next(); 
            if (e.equals(f)) {
                itr.remove(); 
                break;
            }
        }
	}
	
	public void removeFromSnackTwo(int index) {
		FoodLogEntry f = this.snackTwo.get(index);
		this.snackTwo.remove(index);
		this.snackTwoTotalCalories = Double.valueOf(DoubleUtil.round(this.snackTwoTotalCalories.doubleValue() - f.getTotalCalories().doubleValue()));
		this.snackTwoTotalCarbs = Double.valueOf(DoubleUtil.round(this.snackTwoTotalCarbs.doubleValue() - f.getTotalCarbsGrams().doubleValue()));
		this.snackTwoTotalFat = Double.valueOf(DoubleUtil.round(this.snackTwoTotalFat.doubleValue() - f.getTotalFatGrams().doubleValue()));
		this.snackTwoTotalProtein = Double.valueOf(DoubleUtil.round(this.snackTwoTotalProtein.doubleValue() - f.getTotalProteinGrams().doubleValue()));
	}
	
	public List<FoodLogEntry> getSnackTwo() {
		return this.snackTwo;
	}

	public void addToSnackThree(FoodLogEntry f) {
		this.snackThree.add(f);
		this.snackThreeTotalCalories = Double.valueOf(DoubleUtil.round(this.snackThreeTotalCalories.doubleValue() + f.getTotalCalories().doubleValue()));
		this.snackThreeTotalCarbs = Double.valueOf(DoubleUtil.round(this.snackThreeTotalCarbs.doubleValue() + f.getTotalCarbsGrams().doubleValue()));
		this.snackThreeTotalFat = Double.valueOf(DoubleUtil.round(this.snackThreeTotalFat.doubleValue() + f.getTotalFatGrams().doubleValue()));
		this.snackThreeTotalProtein = Double.valueOf(DoubleUtil.round(this.snackThreeTotalProtein.doubleValue() + f.getTotalProteinGrams().doubleValue()));
	}
	
	public void removeFromSnackThree(FoodLogEntry f) {
        Iterator<FoodLogEntry> itr = this.snackThree.iterator(); 
        while (itr.hasNext()) 
        { 
        	FoodLogEntry e = (FoodLogEntry) itr.next(); 
            if (e.equals(f)) {
                itr.remove(); 
                break;
            }
        }
	}
	
	public void removeFromSnackThree(int index) {
		FoodLogEntry f = this.snackThree.get(index);
		this.snackThree.remove(index);
		this.snackThreeTotalCalories = Double.valueOf(DoubleUtil.round(this.snackThreeTotalCalories.doubleValue() - f.getTotalCalories().doubleValue()));
		this.snackThreeTotalCarbs = Double.valueOf(DoubleUtil.round(this.snackThreeTotalCarbs.doubleValue() - f.getTotalCarbsGrams().doubleValue()));
		this.snackThreeTotalFat = Double.valueOf(DoubleUtil.round(this.snackThreeTotalFat.doubleValue() - f.getTotalFatGrams().doubleValue()));
		this.snackThreeTotalProtein = Double.valueOf(DoubleUtil.round(this.snackThreeTotalProtein.doubleValue() - f.getTotalProteinGrams().doubleValue()));
	}
	
	public List<FoodLogEntry> getSnackThree() {
		return this.snackThree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodLog other = (FoodLog) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
