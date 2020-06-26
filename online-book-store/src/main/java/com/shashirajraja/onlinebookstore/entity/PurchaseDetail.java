package com.shashirajraja.onlinebookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_detail")
@IdClass(PurchaseDetailId.class)
public class PurchaseDetail{

	@Id
	@JoinColumn(name="purchase_history_id")
	@ManyToOne
	PurchaseHistory purchaseHistory;
	
	@Id
	@JoinColumn(name="book_id")
	@OneToOne
	Book book;
	
	
	@Column(name = "price")
	double price;
	
	@Column(name = "quanitity")
	int quantity;
	
	public PurchaseDetail() {
		
	}

	public PurchaseDetail(PurchaseHistory purchaseHistory,Book book, double price, int quantity) {
		this.purchaseHistory = purchaseHistory;
		this.price = price;
		this.book = book;
		this.quantity = quantity;
	}

	public PurchaseDetail(double price, int quantity) {
		this.price = price;
		this.quantity = quantity;
	}

	public PurchaseHistory getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
