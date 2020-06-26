package com.shashirajraja.onlinebookstore.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shashirajraja.onlinebookstore.dao.CustomerRepository;
import com.shashirajraja.onlinebookstore.dao.UserRepository;
import com.shashirajraja.onlinebookstore.entity.Authority;
import com.shashirajraja.onlinebookstore.entity.CurrentSession;
import com.shashirajraja.onlinebookstore.entity.Customer;
import com.shashirajraja.onlinebookstore.forms.entity.ChangePassword;
import com.shashirajraja.onlinebookstore.forms.entity.CustomerData;
import com.shashirajraja.onlinebookstore.entity.User;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CurrentSession currentSession;
	
	@Autowired
	CustomerRepository theCustomerRepository;
	
	@Autowired
	PasswordEncoder thePasswordEncoder;
	
	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		
		return theCustomerRepository.findAll();
	}

	@Override
	@Transactional
	public Customer getCustomer(String username) {
		
		Optional<Customer> custOpt = theCustomerRepository.findById(username);
		
		Customer theCustomer = null;
		
		if(custOpt.isPresent())
			theCustomer = custOpt.get();
		
		return theCustomer;
	}

	@Override
	@Transactional
	public String saveCustomer(Customer theCustomer) {
		
		theCustomerRepository.save(theCustomer);
		
		return "Data Saved Successfully!";
	}

	@Override
	public String updateCustomer(Customer theCustomer) {
		
		saveCustomer(theCustomer);
		
		return "Data Updated Successfully!";
	}

	@Override
	@Transactional
	public String removeCustomer(Customer theCustomer) {
		
		theCustomerRepository.delete(theCustomer);
		
		return "Customer Data Deleted Successfully!";
	}

	@Override
	@Transactional
	public String registerCustomer(CustomerData data) {
		String message = "Registration Successful, Login Now!";

		if(!data.getPassword().equals(data.getConfirmPassword())) 
			return "Password and Confirm-Password do not matches!";
		
		//encode the password
		data.setPassword(thePasswordEncoder.encode(data.getPassword()));
		data.setConfirmPassword(data.getPassword());
		
		long mobile = data.getMobile();
		String mobStr = mobile + "";
		if(mobStr.length() > 12 || mobStr.length() < 10)
			return "Invalid Mobile Number!";
		if(mobStr.length() == 11 && mobStr.charAt(0) == '0') {
			mobStr = mobStr.substring(1, mobStr.length());
			mobile = Long.parseLong(mobStr);
		}else if(mobStr.length()==12 && mobStr.charAt(0)=='9' && mobStr.charAt(1) == '1'){
			mobStr = mobStr.substring(2,mobStr.length());
			mobile = Long.parseLong(mobStr);
		}
		else if(mobStr.length() != 10 || mobStr.charAt(0)=='0') {
			return "Please Enter a valid Mobile Number!";
		}
		data.setMobile(mobile);
		
		//create user with username, password and authority
		User theUsers = new User(data.getUsername(),data.getPassword());
		theUsers.addAuthority(new Authority(theUsers,data.getRole()));
		
		//create the customer and map it to the user
		Customer customer = new Customer(data.getUsername(),data.getFirstName(),data.getLastName(),data.getEmail(),data.getMobile(),data.getAddress());
		customer.setUsers(theUsers);		
		
		Optional<Customer> theCust = theCustomerRepository.findById(data.getUsername());
		if(theCust.isPresent()) {
			return "Username is already Registered, try new !";
		}
		
		List<Customer> availCust = theCustomerRepository.findByEmail(data.getEmail());
		
		if(availCust != null) {
			theCustomerRepository.save(customer);	
		}
		else {
			return "Email Id Already Registered!";
		}
		
		return message;
	}

	@Override
	@Transactional
	public CustomerData getCustomerData(String username) {
		Customer customer = getCustomer(username);
		
		CustomerData data = new CustomerData(customer);
	
		return data;
	}

	@Override
	@Transactional
	public String updateCustomer(CustomerData data) {
		
		Optional<Customer> custOpt = theCustomerRepository.findById(data.getUsername());
		Customer theCustomer = new Customer();
		
		if(custOpt.isPresent())
			theCustomer = custOpt.get();
		else
			return "Invalid Customer Data!";
		if(data.getPassword() == null) {
			
			return "Please Enter Password first!";
		}
		
		if(!thePasswordEncoder.matches(data.getPassword(), theCustomer.getUser().getPassword())) 
			return "Invalid Password!";
		
		
		long mobile = data.getMobile();
		String mobStr = mobile + "";
		if(mobStr.length() > 12 || mobStr.length() < 10)
			return "Invalid Mobile Number!";
		if(mobStr.length() == 11 && mobStr.charAt(0) == '0') {
			mobStr = mobStr.substring(1, mobStr.length());
			mobile = Long.parseLong(mobStr);
		}else if(mobStr.length()==12 && mobStr.charAt(0)=='9' && mobStr.charAt(1) == '1'){
			mobStr = mobStr.substring(2,mobStr.length());
			mobile = Long.parseLong(mobStr);
		}
		else if(mobStr.length() != 10 || mobStr.charAt(0)=='0') {
			return "Please Enter a valid Mobile Number!";
		}
		data.setMobile(mobile);
		
		theCustomer.setFirstName(data.getFirstName());
		theCustomer.setLastName(data.getLastName());
		theCustomer.setAddress(data.getAddress());
		theCustomer.setEmail(data.getEmail());
		theCustomer.setMobile(data.getMobile());
		theCustomer.setUsername(data.getUsername());
		
		theCustomer = theCustomerRepository.save(theCustomer);
		
		User theUser = theCustomer.getUser();
		
		currentSession.setUser(theUser);
		
		return "Your profile data has been updated successfully!";
	}

	@Override
	@Transactional
	public String updatePassword(ChangePassword changePassword) {
		if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword()))
			return "new Password don't matches";
		String newPassword = changePassword.getNewPassword();
		if(newPassword.length() <= 4)
			return "Password Length must be greater than 4";
		
		Optional<Customer> theCust = theCustomerRepository.findById(changePassword.getUsername());
		Customer cust = null;
		if(theCust.isPresent())
			cust = theCust.get();
		if(!thePasswordEncoder.matches(changePassword.getOldPassword(), cust.getUser().getPassword()))
			return "Wrong Old Password!";
		
		newPassword = thePasswordEncoder.encode(newPassword);
		
		cust.getUser().setPassword(newPassword);
		
		theCustomerRepository.save(cust);
		
		return "Password Updated Successfully!";
	}

	
}
