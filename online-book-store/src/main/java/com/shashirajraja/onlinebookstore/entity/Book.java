package com.shashirajraja.onlinebookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private double price;
	
	@JoinColumn(name="book_detail_id", referencedColumnName="id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BookDetail bookDetail;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="book_user", joinColumns = @JoinColumn(name="book_id"),
								inverseJoinColumns = @JoinColumn(name="customer_id"))
	private List<Customer> customers;
	
	@OneToMany(mappedBy="book", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH})
	private List<ShoppingCart> shoppingCart;
	
	public Book() {}

	public Book(String name, int quantity, double price, BookDetail bookDetail) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookDetail = bookDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(BookDetail bookDetail) {
		this.bookDetail = bookDetail;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookDetail="
				+ bookDetail + "]";
	}

	public List<ShoppingCart> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
}
