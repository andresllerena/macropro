package com.macropro.types;

public enum Goal {
	BULKING("BULKING"),
	CUTTING("CUTTING");
	
	private final String goal;
	
	Goal(String goal){
		this.goal = goal;
	}
	
	public String getGoal() {
		return this.goal;
	}
}
