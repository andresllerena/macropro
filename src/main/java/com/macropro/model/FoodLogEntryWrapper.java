package com.macropro.model;

import java.util.List;

public class FoodLogEntryWrapper {
	private List<FoodLogEntry> entries;
	//private List<Double> quantityList;

	public List<FoodLogEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<FoodLogEntry> entries) {
		this.entries = entries;
	}

	/*
	 * public List<Double> getQuantityList() { return quantityList; }
	 * 
	 * public void setQuantityList(List<Double> quantityList) { this.quantityList =
	 * quantityList; }
	 */
}
