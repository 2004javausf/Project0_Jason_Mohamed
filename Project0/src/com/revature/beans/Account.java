
package com.revature.beans;

public class Account {
	
// Class variables
	private int userId;
	private String accOwner; // Stores owners name
	private int accNum;	     // Stores owners account number
	private double accBal;   // Stores owners account balance
	public boolean accActive;// checks to see if account is active
	
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
}

