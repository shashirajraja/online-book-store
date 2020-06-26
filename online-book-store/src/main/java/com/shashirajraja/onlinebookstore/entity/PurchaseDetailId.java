package com.shashirajraja.onlinebookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseDetailId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PurchaseHistory purchaseHistory;
	
	private Book book;
	
	public PurchaseHistory getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(PurchaseHistory purchaseHistory,Book book) {
		this.purchaseHistory = purchaseHistory;
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchaseHistory.getId(), book.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(! (obj instanceof PurchaseDetail)) 
			return false;
		PurchaseDetail purchaseDetail = (PurchaseDetail) obj;
		return purchaseDetail.getBook().getId() == this.getBook().getId() && (this.getPurchaseHistory().getId().equals(purchaseDetail.getPurchaseHistory().getId()));
	}

}
