package com.shashirajraja.onlinebookstore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="book_user")
@IdClass(BookUserId.class)
public class BookUser {

	@Id
	@JoinColumn(name="book_id", referencedColumnName="id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH})
	private Book book;
	
	@Id
	@JoinColumn(name="customer_id", referencedColumnName="id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH})
	private Customer customer;

	public BookUser() {
	}

	public BookUser(Book book, Customer customer) {
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

	
	
	
}
