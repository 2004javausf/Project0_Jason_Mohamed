package com.revature.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948588996540717580L;
	private int userId;
	private String username;
	private String password;
	private String name;
	private Account bankAcct;
	
	public User() {
		
	}
	
	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public int getUserId() {
		return userId;
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
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", bankAcct=" + bankAcct + "]";
	}

	//Registers user and adds them to account list
	public static User register(String username, String password, String name) {
		return new User(username, password, name);
	}
	
	public static User login(String username, String password, ArrayList<User> userList) {
		Iterator<User> it = userList.iterator();
		
		//return the user that matches login if it exists
		while(it.hasNext()) {
			User u = it.next();
			if(u.username.equalsIgnoreCase(username) && u.password.equals(password))
				return u;
		}
		
		//if not found:
		return null;
	}

	public void saveData(ArrayList<User> userList) throws IOException {
		OutputStream os = new FileOutputStream("users.txt");
		
		//output user data to file in the format defined in toString()
		for(User u : userList) {
			String userString = u.toString() + "\n";
			os.write(userString.getBytes());
		}
		os.close();
	}
}
