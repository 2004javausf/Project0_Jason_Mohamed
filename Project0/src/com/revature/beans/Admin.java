package com.revature.beans;

public class Admin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5769948804439005552L;

	public Admin() {
		super();
	}
	
	public Admin(String username, String password, String name) {
		super(username, password, name);
	}
}
