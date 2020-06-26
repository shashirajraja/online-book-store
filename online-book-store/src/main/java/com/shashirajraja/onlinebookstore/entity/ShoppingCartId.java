package com.shashirajraja.onlinebookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingCartId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;
	
	private Book book;

	public ShoppingCartId(Customer customer, Book book) {
		this.customer = customer;
		this.book = book;
	}
	
	public ShoppingCartId() {
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer.getUsername(),book.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof ShoppingCart))
			return false;
		ShoppingCart shoppingCart = (ShoppingCart)obj;
		return ((this.getBook().getId() == shoppingCart.getBook().getId()) && (this.getCustomer().getUsername().equals(shoppingCart.getCustomer().getUsername())));
	}
	
	
}
