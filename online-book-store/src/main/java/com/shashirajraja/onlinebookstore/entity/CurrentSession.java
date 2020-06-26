package com.shashirajraja.onlinebookstore.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.shashirajraja.onlinebookstore.service.UserService;

@Component("currentSession")
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentSession {

	@Autowired
	UserService userService;
	
	private User user;
	
	
	public User getUser() {
		if (user == null) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
            user = userService.getUserByUsername(username);
        }
        return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
