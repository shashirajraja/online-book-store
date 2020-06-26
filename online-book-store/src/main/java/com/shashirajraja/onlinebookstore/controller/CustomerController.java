package com.shashirajraja.onlinebookstore.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shashirajraja.onlinebookstore.entity.Book;
import com.shashirajraja.onlinebookstore.entity.CurrentSession;
import com.shashirajraja.onlinebookstore.entity.Customer;
import com.shashirajraja.onlinebookstore.entity.ShoppingCart;
import com.shashirajraja.onlinebookstore.forms.entity.ChangePassword;
import com.shashirajraja.onlinebookstore.forms.entity.CustomerData;
import com.shashirajraja.onlinebookstore.service.BookService;
import com.shashirajraja.onlinebookstore.service.BookUserService;
import com.shashirajraja.onlinebookstore.service.CustomerService;
import com.shashirajraja.onlinebookstore.service.ShoppingCartService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private BookService theBookService;
	
	@Autowired
	private BookUserService theBookUserService;
	
	@Autowired
	private CustomerService theCustomerService;
	
	@Autowired
	private CurrentSession currentSession;
	
	@Autowired
	private ShoppingCartService theShoppingCartService;
	
	@GetMapping("")
	public String customerHome(Model theModel) {
		
		return viewBooks(theModel);
	}
	
	@GetMapping("/profile")
	public String profileView(Model theModel, HttpSession session) {
		CustomerData theData = new CustomerData(currentSession.getUser().getCustomer());
		theModel.addAttribute("customerData", theData);
		return "customer-profile-view";
	}

	@GetMapping("/profile/update")
	public String profileEdit(Model theModel) {
		CustomerData theData = new CustomerData(currentSession.getUser().getCustomer());
		theModel.addAttribute("customerData", theData);
		String message = null;
		theModel.addAttribute("message", message);
		return "customer-profile-update";
	}
	
	@PostMapping("/profile/update/process")
	public String processProfileEdit(@ModelAttribute("customerData") CustomerData customerData, Model theModel) {
		String message = theCustomerService.updateCustomer(customerData);
		theModel.addAttribute("message", message);
		CustomerData theData = new CustomerData(currentSession.getUser().getCustomer());
		theModel.addAttribute("customerData", theData);
		return "customer-profile-update";
	}
	
	@GetMapping("/password")
	public String changePassword(Model theModel) {
		String username = currentSession.getUser().getUsername();
		
		ChangePassword changePassword = new ChangePassword(username);
		
		theModel.addAttribute("changePassword",changePassword);
		
		String message = null;
		theModel.addAttribute("message", message);
		
		return "customer-password-update";
	}
	
	
	@PostMapping("/password/change")
	public String processChangePassword(@ModelAttribute("changePassword") ChangePassword changePassword, Model theModel) {
		String message = theCustomerService.updatePassword(changePassword);
		theModel.addAttribute("message", message);		
		return "customer-password-update";
	}
	
	/*public String getUsername() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		return username;
	}*/
	
	@GetMapping("/books")
	public String viewBooks(Model theModel) {
		
		Customer customer = currentSession.getUser().getCustomer();
		
		Set<Book> books = theBookUserService.getBooksPurchasedBy(customer);
		
		theModel.addAttribute("message", "Read button construction is under progress..");
		theModel.addAttribute("books", books);
		return "customer-purchased-books";
	}
	
	
	
	@GetMapping("/cart/add")
	public String addToCart(@RequestParam("bookId") int bookId, Model theModel) {
				
		Customer customer = currentSession.getUser().getCustomer();
		
		String message = theShoppingCartService.addItem(customer, theBookService.getBookById(bookId));
						
		Set<Book> books = theBookService.getAllBooks();
		
		theModel.addAttribute("books", books);
		
		theModel.addAttribute("shoppingItems", currentSession.getUser().getCustomer().getShoppingCart());
		
		theModel.addAttribute("message", message);
		
		return "customer-books-list";
	}
	
	/*@GetMapping("/cart/remove")
	public String viewCustomerCart(@RequestParam("bookId") int bookId, Model theModel) {
		String message = null;
		Customer customer = currentSession.getUser().getCustomer();
		message = theShoppingCartService.removeItem(customer, theBookService.getBookById(bookId));
		Set<ShoppingCart> shoppingItems = theShoppingCartService.getAllItems(customer);
		customer.setShoppingCart(shoppingItems);
		theModel.addAttribute("message", message);
		theModel.addAttribute("shoppingItems", shoppingItems);
		return "customer-cart";
	}*/
	
	
	@GetMapping("/cart")
	public String viewCustomerCart(Model theModel) {
		String message = null;
		Customer customer = currentSession.getUser().getCustomer();
		Set<ShoppingCart> shoppingItems = customer.getShoppingCart();
		theModel.addAttribute("message", message);
		theModel.addAttribute("shoppingItems", shoppingItems);
		return "customer-cart";
	}
	
	@GetMapping("cart/remove")
	public String removeAndViewList(@RequestParam("bookId") int bookId, Model theModel) {
		String message = null;
		Customer customer = currentSession.getUser().getCustomer();
		message = theShoppingCartService.removeItem(customer, theBookService.getBookById(bookId));
		Set<ShoppingCart> shoppingItems = theShoppingCartService.getAllItems(customer);
		customer.setShoppingCart(shoppingItems);
		theModel.addAttribute("message", message);
		Set<Book> books = theBookService.getAllBooks();
		theModel.addAttribute("books", books);
		theModel.addAttribute("shoppingItems", shoppingItems);
		return "customer-books-list";
	}
	
	@GetMapping("/books/search")
	public String searchBooks(@RequestParam("name") String search, Model theModel) {
		Set<Book> books = null;
		books = theBookService.searchBooks(search);
		theModel.addAttribute("books",books);
		System.out.println(search);
		return "customer-books-list";
	}
}
