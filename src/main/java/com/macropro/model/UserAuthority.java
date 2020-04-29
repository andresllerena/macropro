package com.macropro.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_authorities")
public class UserAuthority {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="authority")
	private String authority;
	
	@ManyToMany(mappedBy = "userAuthorities")
	private Set<User> users = new HashSet<>();

	public UserAuthority() {
		
	}
	
	public UserAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User u) {
		this.users.add(u);
		u.getUserAuthorities().add(this);
	}
	
	public void removeUser(User u) {
		this.users.remove(u);
		u.getUserAuthorities().remove(this);
	}

	public boolean containsUser(User u) {
		return this.users.contains(u);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		UserAuthority ua = (UserAuthority) obj;
		
		return (this.authority.equals(ua.getAuthority()));
	}
	
	@Override
	public int hashCode() {
	    int prime = 31;
	    return prime + (authority == null ? 0 : authority.hashCode());
	}
	
}
