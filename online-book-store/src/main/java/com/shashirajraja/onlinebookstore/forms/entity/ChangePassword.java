package com.shashirajraja.onlinebookstore.forms.entity;

public class ChangePassword {

	private String username;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String confirmPassword;
	
	public ChangePassword() {}
	
	public ChangePassword(String username) {this.username = username;}
	public ChangePassword(String oldPassword, String newPassword, String confirmPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
