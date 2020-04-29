package com.macropro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

@Entity
@Table(name="food_log")
public class FoodLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;

	@Column(name="date")
    //@Convert(converter = LocalDatePersistenceConverter.class)
	public LocalDate date;
	
	@Column(name="datetime")
    //@Convert(converter = LocalDatePersistenceConverter.class)
	public LocalDateTime datetime;

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

	public FoodLog() {
		
	}
	
	public FoodLog(LocalDate date, User user) {
		this.date = date;
		this.user = user;
	}

	public FoodLog(LocalDate date, LocalDateTime datetime, User user) {
		this.date = date;
		this.datetime = datetime;
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addToBreakfast(FoodLogEntry f) {
		this.breakfast.add(f);
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
		this.breakfast.remove(index);
	}
	
	public List<FoodLogEntry> getBreakfast() {
		return this.breakfast;
	}
	
	public void addToLunch(FoodLogEntry f) {
		this.lunch.add(f);
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
		this.lunch.remove(index);
	}
	
	public List<FoodLogEntry> getLunch() {
		return this.lunch;
	}

	public void addToDinner(FoodLogEntry f) {
		this.dinner.add(f);
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
		this.dinner.remove(index);
	}
	
	public List<FoodLogEntry> getDinner() {
		return this.dinner;
	}

	public void addToSnackOne(FoodLogEntry f) {
		this.snackOne.add(f);
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
		this.snackOne.remove(index);
	}
	
	public List<FoodLogEntry> getSnackOne() {
		return this.snackOne;
	}

	public void addToSnackTwo(FoodLogEntry f) {
		this.snackTwo.add(f);
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
		this.snackTwo.remove(index);
	}
	
	public List<FoodLogEntry> getSnackTwo() {
		return this.snackTwo;
	}

	public void addToSnackThree(FoodLogEntry f) {
		this.snackThree.add(f);
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
		this.snackThree.remove(index);
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
