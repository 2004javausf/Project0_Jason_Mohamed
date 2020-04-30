package com.revature.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.revature.beans.Account;
import com.revature.util.Menu;
import com.revature.util.Validate;

class AccountPullTest {
	static Menu accounts;
	static Map<Integer, String> accountNames;   // List variable to store the accounts type, number, and their amounts
	static Scanner sc = new Scanner(System.in); // variable to store all user input
	public static List<Account> accountsList = new ArrayList<Account>();

	@Test
	void test() {
		//fail("Not yet implemented");
		// Accounts
		Account acc1 = new Account("username", "Bob", 12345, 200.50, "Checking", true);
		Account acc2 = new Account("username", "Bob", 12345, 200.50, "Checking", false);
		Account acc3 = new Account("username", "Bob", 54321, 100, "Checking", true);
		Account acc4 = new Account("name", "Bob", 12345, 200.50, "Checking", true);
		Account acc5 = new Account("username", "Bob", 12321, .5, "Checking", true);
		
		// Add accounts to list
		accountsList.add(acc1);
		accountsList.add(acc2);
		accountsList.add(acc3);
		accountsList.add(acc4);
		accountsList.add(acc5);
		
		// code to be tested
		accountNames = AccountStrFormatter("Checking", "username");
		String s = accountNames.values().toString();
		String subS = (" " + s.substring(1, s.length() - 2));
		String[] sa = subS.split(",");
		
		accounts = new Menu("Checking Accounts", sa);
		accounts.Display();
		System.out.println("\nWhat account would you like to deposit into?");
		
		// retrieves selected account
		int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
		Account selectedAcc = AccSelector(userInput);
		
		System.out.println(selectedAcc.accString());
		
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
				
				System.out.println(acc.accString());
			}
		}			
	}
	
//--------------------------------------------Bank Methods------------------------------------------
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
