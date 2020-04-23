package com.revature.beans;

public class User {
	private String username;
	private String password;
	private String name;
	private Account bankAcct;
	
	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getBankAcct() {
		return bankAcct;
	}
	public void setBankAcct(Account bankAcct) {
		this.bankAcct = bankAcct;
	}
	
	public static User register(String username, String password, String name) {
		return new User(username, password, name);
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", bankAcct=" + bankAcct
				+ "]";
	}

	public void saveData() {
		;
	}
	
	
}
