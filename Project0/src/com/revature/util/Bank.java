package com.revature.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;

public class Bank {
    // Lists to store the objects for banking app
	public static List<User> usersList = new ArrayList<User>();
	public static List<Account> accountsList = new ArrayList<Account>();
	
    // Global variables
	static Menu accounts;
	static Map<Integer, String> accountNames;   // List variable to store the accounts type, number, and their amounts
	static Scanner sc = new Scanner(System.in); // variable to store all user input
	
//---------------------------------------Account Edit Methods---------------------------------------
		public static void Deposit(String accType, String username) {
			// builds menu
			accountNames = AccountStrFormatter(accType, username);
			accounts = new Menu("temp title", MenuItemConverter());
			
			// checks account type to update the menu
			if (accType.equalsIgnoreCase("Checking")) {
				accounts.newTitle("Checking Accounts");
			}
			else {accounts.newTitle("Savings Accounts");}

				// displays menu	
				accounts.Display();
				System.out.println("\nWhat account would you like to deposit into?");
				
				// retrieves selected account
				int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				Account selectedAcc = AccSelector(userInput);
				
				// prompt the user and deposit amount into the selected account
				System.out.println("\n How much would you like to deposit?");
				
				double deposit = -1;
				while (deposit < 0) {
					deposit = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1");
				}
				// add deposit to the account
				selectedAcc.setAccBal(selectedAcc.getAccBal() + deposit);
				
				// update the referenced account in the accountsList
				for (Account acc : accountsList) {
					if (acc.getAccNum() == selectedAcc.getAccNum()) {
						acc = selectedAcc;
						
						System.out.println(" " + acc.accString());
					}
				}			
		}
		
		public static void Withdraw(String accType, String username) {
			// builds menu
			accountNames = AccountStrFormatter(accType, username);
			accounts = new Menu("temp title", MenuItemConverter());
			
			// checks account type to update the menu
			if (accType.equalsIgnoreCase("Checking")) {
				accounts.newTitle("Checking Accounts");
			}
			else {accounts.newTitle("Savings Accounts");}

				// displays menu	
				accounts.Display();
				System.out.println("\nWhat account would you like to withdraw from?");
				
				// retrieves selected account
				int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				Account selectedAcc = AccSelector(userInput);
				
				// prompt the user and withdraw amount from the selected account
				System.out.println("\n How much would you like to withdraw?");
				
				double withdraw = -1;
				while (withdraw < 0) {
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1");
				}
				// subtract withdraw from the account if withdraw is equal to or greater than account balance
				if (withdraw >= selectedAcc.getAccBal()) {
					selectedAcc.setAccBal(selectedAcc.getAccBal() - withdraw);
				}
				else { // pushes withdraw method again
					System.out.println("\n\n\n\nWithdraw amount exceeds the account balance.");
					Withdraw(accType, username);
				}
				
				// update the referenced account in the accountsList
				for (Account acc : accountsList) {
					if (acc.getAccNum() == selectedAcc.getAccNum()) {
						acc = selectedAcc;
						
						System.out.println(" " + acc.accString());
					}
				}						
		}
		
	    public static void Transfer() {
	  
		}
	    
	    // gets the names of accounts
	    private static Map<Integer, String> AccountStrFormatter(String accType, String username) {

	    	// List to return
	    	Map<Integer, String> returnLst = new LinkedHashMap<Integer, String>();
	    	
	    	for (int i = 0; i < accountsList.size(); i++) {
				/* 
				 * Checks to first see if the account belongs to the user
	    		 * Then if the account type is correct
	    		 * Then checks if the account is active
				 */ 
				if (accountsList.get(i).getUsername().equalsIgnoreCase(username) 
				    && accountsList.get(i).getAccType().equalsIgnoreCase(accType) 
					&& accountsList.get(i).accActive == true) {
					// 
					String lstItem = accountsList.get(i).accString();
							//accType + ": " + accountsList.get(i).getAccNum() + "\t" + balance.format(accountsList.get(i).getAccBal());
					returnLst.put(i, lstItem);
				}
			}
	    	
	    	return returnLst;
	    }
	    
	    // converts the accountNames map into an array of items for the menu
	    private static String[] MenuItemConverter() {
	    	String s = accountNames.values().toString();
			String subS = (" " + s.substring(1, s.length() - 2));
			String[] sa = subS.split(",");
			return sa;
	    }
	    
//==================================================================================================
	  
//-----------------------------------------Selection Methods----------------------------------------
	    static Account AccSelector(int userInput) {
	    	if (userInput >= 1 && userInput <= accountNames.size() - 1) {
	    		/* 
	    		 * This line finds the account the user selected by comparing the userInput(int) to the 
	    		 * index of the LinkedHashTable, which stores the accountsList(data from file) index and a
	    		 * String formatted from the object Account.
	    		 * 
	    		 * This matches both the selected menu item and desired Account from the accountsList and returns 
	    		 * it to then edit
	    		 */
				Account selectedAcc = accountsList.get((int) accountNames.keySet().toArray()[userInput - 1]);
				return selectedAcc;
			} else {
				System.out.println("\n\n\n\nPlease type in a correct option.");
				accounts.Display();
				int choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				AccSelector(choice);
				}
	    	
	    	return null;
	    }
//==================================================================================================
}