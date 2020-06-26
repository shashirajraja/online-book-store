package com.shashirajraja.onlinebookstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.shashirajraja.onlinebookstore.entity.Book;
import com.shashirajraja.onlinebookstore.entity.Customer;
import com.shashirajraja.onlinebookstore.entity.ShoppingCart;
import com.shashirajraja.onlinebookstore.entity.ShoppingCartId;

@RepositoryRestResource
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {

	
	@Query("from ShoppingCart where customer is :customer")
	public List<ShoppingCart> getItemsByCustomer(@Param("customer") Customer customer);
	
	
	
	@Query("from ShoppingCart where book is :book")
	public List<ShoppingCart> getItemsByBook(@Param("book") Book book);
	
	@Transactional
	@Modifying
	@Query(value = "Insert into shopping_cart(customer_id,book_id,count) values(?1, ?2, ?3)", nativeQuery = true)
	public int addByIds(String customerId, Integer bookId, Integer count);
	
	@Transactional
	@Modifying
	@Query(value = "delete from shopping_cart where customer_id = ?1 and book_id = ?2", nativeQuery = true)
	public int removeByIds(String customerId, Integer bookId);
	
	@Transactional
	@Modifying
	@Query(value = "update shopping_cart set count = ?3 where customer_id = ?1 and book_id = ?2", nativeQuery = true)
	public int updateByIds(String customerId, Integer bookId, Integer count);
}
