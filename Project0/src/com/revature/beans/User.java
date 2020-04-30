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
	protected String username;
	protected String password;
	protected String name;
	//public Integer[] bankAcct = new Integer[];
	
	public User() {
		
	}
	
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


	
//	@Override
//	public String toString() {
//		return "User [username=" + username + ", password=" + password + ", name=" + name
//				+ ", bankAcct=" + bankAcct + "]";
//	}
}
