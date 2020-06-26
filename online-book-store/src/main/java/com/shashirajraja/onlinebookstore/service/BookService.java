package com.shashirajraja.onlinebookstore.service;

import java.util.List;
import java.util.Set;

import com.shashirajraja.onlinebookstore.entity.Book;


public interface BookService {

	public Set<Book> getAllBooks();
	
	public Book getBookById(int bookId);
	
	public String updateBook(Book book);
	
	public String removeBookById(int bookId);

	public String removeBook(Book book);
	
	public String addBook(Book book);
	
	public Set<Book> searchBooks(String search);
}
