package com.shashirajraja.onlinebookstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shashirajraja.onlinebookstore.entity.CurrentSession;
import com.shashirajraja.onlinebookstore.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService theUserService;
	
	@Autowired
	CurrentSession currentSession;
	
	@GetMapping("/login")
	public String showLoginForm(HttpSession session) {
		session.setAttribute("var", "My Variable");
		return "login-form";
	}
	
	@GetMapping({"/",""})
	public String showHome(Model theModel) {
		//theModel.addAttribute("username", currentSession.getUser().getUsername());
		return "customer-home";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	
	
	//load user to the session after getting logged in
	
	/*@After("execution(* * showHome(..))")
	public void loadUserAfterLoggedIn(HttpSession session) {
		System.out.println(">>>>>>>>>>>Loadin the user data from the database...");
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User theUser = theUserService.getUserByUsername(username);
		session.setAttribute("activeUser", theUser);
	}*/
}
