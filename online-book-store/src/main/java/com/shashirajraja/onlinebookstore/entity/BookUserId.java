package com.shashirajraja.onlinebookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class BookUserId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Book book;
	private Customer customer;
	
	
	public BookUserId() {
	}

	public BookUserId(Book book, Customer customer) {
		this.book = book;
		this.customer = customer;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		
		return Objects.hash(this.getBook().getId(), this.getCustomer().getUsername());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof BookUser))
			return false;
		BookUser user = (BookUser) obj;
		return this.getBook().getId() == user.getBook().getId() && this.getCustomer().getUsername().equals(user.getCustomer().getUsername());
	}
	
	
}
