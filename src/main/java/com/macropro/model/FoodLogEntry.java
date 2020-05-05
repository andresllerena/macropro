package com.macropro.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.macropro.util.DoubleUtil;

@Entity
@Table(name="food_log_entries")
public class FoodLogEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id")
	public Food food;

	@Column(name="num_servings")
    public Double numberOfServings;

	@Column(name="total_servings")
    public Double totalServings;
	
	@Column(name="total_calories")
	public Double totalCalories;

	@Column(name="total_carbs_g")
	public Double totalCarbsGrams;

	@Column(name="total_fat_g")
	public Double totalFatGrams;

	@Column(name="total_protein_g")
	public Double totalProteinGrams;
	
	@Transient
	public boolean selected = false;
	
	public FoodLogEntry() {
		
	}

	public FoodLogEntry(Food food) {
		this.food = food;
	}

	public FoodLogEntry(Food food, Double numberOfServings) {
		this.food = food;
		this.numberOfServings = numberOfServings;
		setTotalMacros();
		setTotalServings();
	}

	public FoodLogEntry(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Double getNumberOfServings() {
		return numberOfServings;
	}

	public void setNumberOfServings(Double numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	public Double getTotalServings() {
		return totalServings;
	}

	public void setTotalServings(Double totalServings) {
		this.totalServings = totalServings;
	}
	
	public void setTotalServings() {
		this.totalServings = Double.valueOf(DoubleUtil.round(this.numberOfServings.doubleValue() * this.food.getServingSize().doubleValue()));
	}

	public Double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(Double totalCalories) {
		this.totalCalories = totalCalories;
	}

	public Double getTotalCarbsGrams() {
		return totalCarbsGrams;
	}

	public void setTotalCarbsGrams(Double totalCarbsGrams) {
		this.totalCarbsGrams = totalCarbsGrams;
	}

	public Double getTotalFatGrams() {
		return totalFatGrams;
	}

	public void setTotalFatGrams(Double totalFatGrams) {
		this.totalFatGrams = totalFatGrams;
	}

	public Double getTotalProteinGrams() {
		return totalProteinGrams;
	}

	public void setTotalProteinGrams(Double totalProteinGrams) {
		this.totalProteinGrams = totalProteinGrams;
	}
	
	public void setTotalMacros() {
		this.totalCalories = Double.valueOf(DoubleUtil.round(this.food.getCalories().doubleValue() * this.numberOfServings.doubleValue()));
		this.totalCarbsGrams = Double.valueOf(DoubleUtil.round(this.food.getCarbsGrams().doubleValue() * this.numberOfServings.doubleValue()));
		this.totalFatGrams = Double.valueOf(DoubleUtil.round(this.food.getFatGrams().doubleValue() * this.numberOfServings.doubleValue()));
		this.totalProteinGrams = Double.valueOf(DoubleUtil.round(this.food.getProteinGrams().doubleValue() * this.numberOfServings.doubleValue()));
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((numberOfServings == null) ? 0 : numberOfServings.hashCode());
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
		FoodLogEntry other = (FoodLogEntry) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (numberOfServings == null) {
			if (other.numberOfServings != null)
				return false;
		} else if (!numberOfServings.equals(other.numberOfServings))
			return false;
		return true;
	}
}
