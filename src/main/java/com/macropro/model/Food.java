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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="food")
public class Food {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;

	@Column(name="brand")
	@Size(max=50, message="only allowed 50 characters")
	public String brand;

	@Column(name="name")
	@NotNull
	@Size(min=1, max=50, message="must be between 1 and 50 characters")
	public String name;

	@Column(name="serving_size")
	@NotNull
	@Min(value=0, message="cannot be negative")
	public Double servingSize;

	@Column(name="unit_of_measurement")
	@NotNull
	public String unitOfMeasurement;

	@Column(name="calories")
	@NotNull
	@Min(value=0, message="cannot be negative")
	public Double calories;

	@Column(name="carbs_g")
	@NotNull
	@Min(value=0, message="cannot be negative")
	public Double carbsGrams;

	@Column(name="fat_g")
	@NotNull
	@Min(value=0, message="cannot be negative")
	public Double fatGrams;

	@Column(name="protein_g")
	@NotNull
	@Min(value=0, message="cannot be negative")
	public Double proteinGrams;

	@Column(name="fiber_g", insertable=false)
	@Min(value=0, message="cannot be negative")
	public Double fiberGrams;

	@Column(name="public_flag")
	@NotNull
	public boolean publicFlag;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
	public User creator;

	public Food() {
		
	}
	
	public Food(String name, double servingSize, String unitOfMeasurement, double calories, double carbsGrams,
			double fatGrams, double proteinGrams, boolean publicFlag, User creator) {
		this.name = name;
		this.servingSize = servingSize;
		this.unitOfMeasurement = unitOfMeasurement;
		this.calories = calories;
		this.carbsGrams = carbsGrams;
		this.fatGrams = fatGrams;
		this.proteinGrams = proteinGrams;
		this.publicFlag = publicFlag;
		this.creator = creator;
	}

	public Food(String brand, String name, double servingSize, String unitOfMeasurement, double calories,
			double carbsGrams, double fatGrams, double proteinGrams, double fiberGrams, boolean publicFlag, User creator) {
		this.brand = brand;
		this.name = name;
		this.servingSize = servingSize;
		this.unitOfMeasurement = unitOfMeasurement;
		this.calories = calories;
		this.carbsGrams = carbsGrams;
		this.fatGrams = fatGrams;
		this.proteinGrams = proteinGrams;
		this.fiberGrams = fiberGrams;
		this.publicFlag = publicFlag;
		this.creator = creator;
	}
	
	public Food(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getServingSize() {
		return servingSize;
	}

	public void setServingSize(Double servingSize) {
		this.servingSize = servingSize;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public Double getCarbsGrams() {
		return carbsGrams;
	}

	public void setCarbsGrams(Double carbsGrams) {
		this.carbsGrams = carbsGrams;
	}

	public Double getFatGrams() {
		return fatGrams;
	}

	public void setFatGrams(Double fatGrams) {
		this.fatGrams = fatGrams;
	}

	public Double getProteinGrams() {
		return proteinGrams;
	}

	public void setProteinGrams(Double proteinGrams) {
		this.proteinGrams = proteinGrams;
	}

	public Double getFiberGrams() {
		return fiberGrams;
	}

	public void setFiberGrams(Double fiberGrams) {
		this.fiberGrams = fiberGrams;
	}

	public boolean isPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(boolean publicFlag) {
		this.publicFlag = publicFlag;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
		creator.getMyFoods().add(this);
	}
	
	public static Double generateTotalCalories(Double carbs, Double fat, Double protein) {
		return Double.valueOf((carbs.doubleValue()*4.0) +
								(fat.doubleValue()*9.0) + 
								(protein.doubleValue()*4.0));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((carbsGrams == null) ? 0 : carbsGrams.hashCode());
		result = prime * result + ((fatGrams == null) ? 0 : fatGrams.hashCode());
		result = prime * result + ((fiberGrams == null) ? 0 : fiberGrams.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((proteinGrams == null) ? 0 : proteinGrams.hashCode());
		result = prime * result + ((servingSize == null) ? 0 : servingSize.hashCode());
		result = prime * result + ((unitOfMeasurement == null) ? 0 : unitOfMeasurement.hashCode());
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
		Food other = (Food) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (carbsGrams == null) {
			if (other.carbsGrams != null)
				return false;
		} else if (!carbsGrams.equals(other.carbsGrams))
			return false;
		if (fatGrams == null) {
			if (other.fatGrams != null)
				return false;
		} else if (!fatGrams.equals(other.fatGrams))
			return false;
		if (fiberGrams == null) {
			if (other.fiberGrams != null)
				return false;
		} else if (!fiberGrams.equals(other.fiberGrams))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (proteinGrams == null) {
			if (other.proteinGrams != null)
				return false;
		} else if (!proteinGrams.equals(other.proteinGrams))
			return false;
		if (servingSize == null) {
			if (other.servingSize != null)
				return false;
		} else if (!servingSize.equals(other.servingSize))
			return false;
		if (unitOfMeasurement == null) {
			if (other.unitOfMeasurement != null)
				return false;
		} else if (!unitOfMeasurement.equals(other.unitOfMeasurement))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", brand=" + brand + ", name=" + name + ", servingSize=" + servingSize
				+ ", unitOfMeasurement=" + unitOfMeasurement + ", calories=" + calories + ", carbsGrams=" + carbsGrams
				+ ", fatGrams=" + fatGrams + ", proteinGrams=" + proteinGrams + ", fiberGrams=" + fiberGrams
				+ ", publicFlag=" + publicFlag + "]";
	}
	
}
