
package com.revature.beans;

import java.io.Serializable;
import java.text.NumberFormat;

public class Account implements Serializable {
	
/**
	 * 
	 */
// Class variables
	private static final long serialVersionUID = 8786701809320089922L;
	private String username;  // Id to share across all user accounts
	private String accOwner;  // Stores owners name
	private int accNum;	      // Stores owners account number
	private double accBal;    // Stores owners account balance
	private String accType;   // Either a Checking or Savings
	public boolean accActive; // checks to see if account is active
	
	
	public Account() {
		
	}
	
	public Account(String username, String accOwner, int accNum, double accBal, String accType, boolean accActive) {
		this.username = username;
		this.accOwner = accOwner;
		this.accNum = accNum;
		this.accBal = accBal;
		this.accType = accType;
		this.accActive = accActive;
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
	
// non-overriding to.String
	public String accString() {
		// formats a double in dollar format
		NumberFormat balance = NumberFormat.getCurrencyInstance();
		
		return accType + ": " + accNum + "\t\t" + balance.format(accBal);
	}
}

