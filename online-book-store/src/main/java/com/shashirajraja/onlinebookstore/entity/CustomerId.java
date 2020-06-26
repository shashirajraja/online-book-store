package com.shashirajraja.onlinebookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class CustomerId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;

	public CustomerId() {
	}
	
	public CustomerId(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user.getUsername());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Customer))
			return false;
		Customer customer = (Customer) obj;
		
		return customer.getUsername().equals(this.getUser().getUsername());
	}
	
}
