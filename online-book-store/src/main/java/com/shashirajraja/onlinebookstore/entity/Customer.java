package com.shashirajraja.onlinebookstore.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@Column(name="id")
	private String username;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mob")
	private long mobile;
	
	@Column(name="address")
	private String address;

	@JoinColumn(name="id")
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
												CascadeType.MERGE, CascadeType.REFRESH})
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="book_user", joinColumns=@JoinColumn(name="customer_id"), 
								inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> books;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval= true)
	private Set<ShoppingCart> shoppingCart;
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseHistory> purchaseHistories;
	
	public Customer() {
	}
	
	public Customer(String username, String firstName, String lastName, String email, long mobile, String address) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books.clear();
		if(books != null)
			this.books.addAll(books);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ShoppingCart> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(Set<ShoppingCart> shoppingCart) {
		this.shoppingCart.clear();
		if(shoppingCart != null)
			this.shoppingCart.addAll(shoppingCart);
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + ", address=" + address + ", user=" + user + "]";
	}

	
	public Set<ShoppingCart> addShoppingCart(ShoppingCart shoppingCart) {
		if(this.shoppingCart == null)
			this.shoppingCart = new HashSet<ShoppingCart>();
		if(shoppingCart != null)
			this.shoppingCart.add(shoppingCart);
		return this.shoppingCart;
	}

	public Set<PurchaseHistory> getPurchaseHistories() {
		return purchaseHistories;
	}

	public void setPurchaseHistories(Set<PurchaseHistory> purchaseHistories) {
		if(this.purchaseHistories == null)
			this.purchaseHistories = new HashSet<PurchaseHistory>();
		if(purchaseHistories != null)
			this.purchaseHistories.addAll(purchaseHistories);
	}
	
	public Set<PurchaseHistory> addPurchaseHistories(PurchaseHistory purchaseHistory) {
		if(this.purchaseHistories == null)
			this.purchaseHistories = new HashSet<PurchaseHistory>();
		if(purchaseHistory != null)
			this.purchaseHistories.add(purchaseHistory);
		return purchaseHistories;
	}
}
