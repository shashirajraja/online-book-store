package com.shashirajraja.onlinebookstore.service;

import java.util.Set;

import com.shashirajraja.onlinebookstore.entity.Book;
import com.shashirajraja.onlinebookstore.entity.Customer;

public interface BookUserService {

	Set<Book> getBooksPurchasedBy(Customer customer);

}
