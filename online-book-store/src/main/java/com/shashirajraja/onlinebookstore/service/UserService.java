package com.shashirajraja.onlinebookstore.service;

import java.util.List;

import com.shashirajraja.onlinebookstore.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User getUserByUsername(String username);
}
