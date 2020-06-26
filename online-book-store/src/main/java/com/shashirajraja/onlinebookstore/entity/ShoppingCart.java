package com.shashirajraja.onlinebookstore.entity;

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
@Table(name="shopping_cart")
@IdClass(ShoppingCartId.class)
public class ShoppingCart{

	@Id
	@JoinColumn(name="customer_id", referencedColumnName="id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH})
	private Customer customer;
	
	@Id
	@JoinColumn(name="book_id", referencedColumnName="id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH})
	private Book book;
	
	@Column(name = "count")
	private int quantity;

	public ShoppingCart() {
	}

	public ShoppingCart(Customer customer, Book book, int quantity) {
		this.customer = customer;
		this.book = book;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ShoppingCart [customer=" + customer.getUsername() + ", book=" + book.getId() + ", count=" + quantity + "]";
	}
	
}
