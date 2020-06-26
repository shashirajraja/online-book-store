package com.shashirajraja.onlinebookstore.entity;


import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
@IdClass(AuthorityId.class)
public class Authority{

	
	@Id
	@JoinColumn(name="username", referencedColumnName="username")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;
	
	@Id
	@Column(name="authority")
	private String role;
	
	public Authority() {}
	
	public Authority(User user, String role) {
		this.user = user;
		this.role = role;
	}

	public Authority(String role) {
		this.role = role;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}
	
	public void setuser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Authorities [ role: " + role + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(user.getUsername(),role);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Authority))
			return false;
		Authority authority = (Authority) obj;
		
		return this.user.getUsername().equals(authority.getUser().getUsername()) && this.role.equals(authority.getRole());
	}
	
}
