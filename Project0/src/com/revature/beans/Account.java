
package com.revature.beans;

import java.io.Serializable;

public class Account implements Serializable {
	
// Class variables
	private String username;  // Id to share across all user accounts
	private String accOwner;  // Stores owners name
	private int accNum;	      // Stores owners account number
	private double accBal;    // Stores owners account balance
	private String accType;   // Either a Checking or Savings
	public boolean accActive; // checks to see if account is active
	
	public Account() {
		
	}

// Getters & Setters
	public String getAccOwner() {
		return accOwner;
	}

	public void setAccOwner(String accOwner) {
		this.accOwner = accOwner;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public double getAccBal() {
		return accBal;
	}

	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

