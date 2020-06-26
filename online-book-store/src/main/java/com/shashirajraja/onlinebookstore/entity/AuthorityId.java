package com.shashirajraja.onlinebookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private String role;
	
	public AuthorityId(User user, String role) {
		this.user = user;
		this.role = role;
	}
	
	public AuthorityId() {
	
	}

	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user.getUsername(),role);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if(!(obj instanceof Authority))
			return false;
		Authority auth = (Authority)obj;
		
		return (auth.getRole().equals(this.getRole()) && auth.getUser().getUsername().equals(this.getuser().getUsername()));
	}
	
	
	
}
