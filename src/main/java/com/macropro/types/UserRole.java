package com.macropro.types;

public enum UserRole {
	ADMIN("ADMIN"),
	USER_GOAL_NOT_SUBMITTED("USER_GNS"),
	USER_GOAL_SUBMITTED("USER_GS");
	
	private final String role;
	
	UserRole(String role){
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
