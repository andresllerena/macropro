package com.macropro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_goal")
public class UserGoal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotNull(message="invalid goal")
	@Column(name="goal")
	private String goal;

	@NotNull(message="invalid gender")
	@Column(name="gender")
	private String gender;
	
	//@Pattern(regexp="^(0|[1-9][0-9]*)$")
	@Min(value=10, message="must be at least 10 years old")  
	@NotNull
	@Column(name="age")
	private int age;

	@Min(value=1, message="invalid activity level") 
	@NotNull
	@Column(name="activity_level")
	private int activityLevel;

	@NotNull
	@Column(name="height_feet")
	private int heightFeet; // add support for other measurements

	@NotNull
	@Column(name="height_inches")
	private int heightInches; // add support for other measurements

	@NotNull
	@Column(name="weight")
	private double weightPounds; // add support for other measurements

	@Min(value=20, message="invalid diet preference") 
	@NotNull
	@Column(name="ffp")
	private int fatPercentagePreferrence;

	public UserGoal() {
		
	}
	
	public UserGoal(String goal, String gender, int age, int activityLevel, int heightFeet, int heightInches, double weightPounds, int fatPercentagePreferrence) {
		this.goal = goal;
		this.gender = gender;
		this.age = age;
		this.activityLevel = activityLevel;
		this.heightFeet = heightFeet;
		this.heightInches = heightInches;
		this.weightPounds = weightPounds;
		this.fatPercentagePreferrence = fatPercentagePreferrence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(int activityLevel) {
		this.activityLevel = activityLevel;
	}

	public int getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(int heightFeet) {
		this.heightFeet = heightFeet;
	}

	public int getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}

	public double getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(double weightPounds) {
		this.weightPounds = weightPounds;
	}

	public int getFatPercentagePreferrence() {
		return fatPercentagePreferrence;
	}

	public void setFatPercentagePreferrence(int fatPercentagePreferrence) {
		this.fatPercentagePreferrence = fatPercentagePreferrence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityLevel;
		result = prime * result + age;
		result = prime * result + fatPercentagePreferrence;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		result = prime * result + heightFeet;
		result = prime * result + heightInches;
		long temp;
		temp = Double.doubleToLongBits(weightPounds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		UserGoal other = (UserGoal) obj;
		if (activityLevel != other.activityLevel)
			return false;
		if (age != other.age)
			return false;
		if (fatPercentagePreferrence != other.fatPercentagePreferrence)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (goal == null) {
			if (other.goal != null)
				return false;
		} else if (!goal.equals(other.goal))
			return false;
		if (heightFeet != other.heightFeet)
			return false;
		if (heightInches != other.heightInches)
			return false;
		if (Double.doubleToLongBits(weightPounds) != Double.doubleToLongBits(other.weightPounds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGoal [goal=" + goal + ", gender=" + gender + ", age=" + age + ", activityLevel=" + activityLevel
				+ ", heightFeet=" + heightFeet + ", heightInches=" + heightInches + ", weightPounds=" + weightPounds
				+ ", fatPercentagePreferrence=" + fatPercentagePreferrence + "]";
	}
	
}
