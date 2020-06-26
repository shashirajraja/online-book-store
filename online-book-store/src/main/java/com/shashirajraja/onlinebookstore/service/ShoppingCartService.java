package com.shashirajraja.onlinebookstore.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.shashirajraja.onlinebookstore.entity.Book;
import com.shashirajraja.onlinebookstore.entity.Customer;
import com.shashirajraja.onlinebookstore.entity.ShoppingCart;

public interface ShoppingCartService {

	public Set<ShoppingCart> getAllItems(Customer customer);
		
	public String addItem(Customer customer, Book book);

	public String increaseItem(Customer customer, Book book, int count);
	
	public String removeItem(Customer customer, Book book);

	String decreaseItem(Customer customer, Book book, int count);

	public Set<ShoppingCart> getByUsername(Customer customer);
	
}
