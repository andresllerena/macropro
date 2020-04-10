package com.macropro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_macro_plan")
public class UserMacroPlan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="target_calories")
	private int targetCalories;

	@Column(name="carbs_g")
	private int carbsGrams;

	@Column(name="fat_g")
	private int fatGrams;

	@Column(name="protein_g")
	private int proteinGrams;

	public UserMacroPlan() {
		
	}
	
	public UserMacroPlan(int targetCalories, int carbsGrams, int fatGrams, int proteinGrams) {
		super();
		this.targetCalories = targetCalories;
		this.carbsGrams = carbsGrams;
		this.fatGrams = fatGrams;
		this.proteinGrams = proteinGrams;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTargetCalories() {
		return targetCalories;
	}

	public void setTargetCalories(int targetCalories) {
		this.targetCalories = targetCalories;
	}

	public int getCarbsGrams() {
		return carbsGrams;
	}

	public void setCarbsGrams(int carbsGrams) {
		this.carbsGrams = carbsGrams;
	}

	public int getFatGrams() {
		return fatGrams;
	}

	public void setFatGrams(int fatGrams) {
		this.fatGrams = fatGrams;
	}

	public int getProteinGrams() {
		return proteinGrams;
	}

	public void setProteinGrams(int proteinGrams) {
		this.proteinGrams = proteinGrams;
	}

	@Override
	public String toString() {
		return "UserMacroPlan [targetCalories=" + targetCalories + ", carbsGrams=" + carbsGrams + ", fatGrams="
				+ fatGrams + ", proteinGrams=" + proteinGrams + "]";
	}
	
}
