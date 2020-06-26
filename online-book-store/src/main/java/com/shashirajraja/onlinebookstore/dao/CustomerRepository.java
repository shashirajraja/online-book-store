package com.shashirajraja.onlinebookstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.shashirajraja.onlinebookstore.entity.Customer;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, String> {

	
	@Query("from Customer where lower(email)=:email_id")
	public List<Customer> findByEmail(@Param("email_id") String emailId);
}
