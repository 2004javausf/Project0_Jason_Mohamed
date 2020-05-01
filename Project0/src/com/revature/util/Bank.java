package com.revature.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Random;

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
	
	// Strings to change the color of other Strings printed to the screen
	private static final String cyanString = "\u001B[36m";
	private static final String resetString = "\u001B[0m";
	
//---------------------------------------Account Edit Methods---------------------------------------
	    // for USER-------
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
				System.out.println("\nHow much would you like to deposit?");
				
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
						
						System.out.println(cyanString + "\n" + acc.accString() + resetString);
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
				System.out.println("\nHow much would you like to withdraw?");
				
				// gets the desired amount from user to withdraw
				double withdraw = 0;
				do{
					if (withdraw < 0) {
						System.out.println("\nWithdraw amount is less than 0");
						withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
					}
					else if (withdraw > selectedAcc.getAccBal()) {
						System.out.println("\n\n\n\nWithdraw amount exceeds the account balance.");
						withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
					}
					else {withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1");}
				} while (withdraw < 0 && withdraw > selectedAcc.getAccBal());
							
				// subtract withdraw from the account if withdraw is equal to or greater than account balance
				selectedAcc.setAccBal(selectedAcc.getAccBal() - withdraw);
		}
		
	    public static void Transfer(String username) {
	    	// builds menu
	    	accountNames = AccountStrFormatter(username);
	    	accounts = new Menu("User Accounts", MenuItemConverter());
	    	
	    	//------------------------WITHDRAW PORTION------------------------
	    	// displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to transfer from?");
	    	
	    	// retrieves selected account
			int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account firstAcc = AccSelector(userInput);
			
			// prompt the user and withdraw amount from the selected account
			System.out.println("\nHow much would you like to transfer?");
			
			// gets the desired amount from user to withdraw
			double withdraw = 0;
			do{
				if (withdraw < 0) {
					System.out.println("\nWithdraw amount is less than 0");
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
				}
				else if (withdraw > firstAcc.getAccBal()) {
					System.out.println("\n\n\n\nWithdraw amount exceeds the account balance.");
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
				}
				else {withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1");}
			} while (withdraw < 0 && withdraw > firstAcc.getAccBal());
						
			// subtract withdraw from the account if withdraw is equal to or greater than account balance
			String originalFirst = firstAcc.accString(); // string to hold the original choice to exclude from future menu
			firstAcc.setAccBal(firstAcc.getAccBal() - withdraw);
			//----------------------------------------------------------------
			
			// iterator to iterate through the map
			Iterator<Entry<Integer, String>> iterator = accountNames.entrySet().iterator();
			
			// deletes menu item based on the first selection from menu
			while (iterator.hasNext()) {
				Map.Entry<Integer, String> entry = iterator.next();
			    if (originalFirst.equals(entry.getValue())) {
			        iterator.remove();
			    }
			}
			
			//------------------------DEPOSIT PORTION-------------------------
		    // builds menu
		    accounts = new Menu("User Accounts", MenuItemConverter());
		    // displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to transfer to?");
			
			// retrieves selected account
			userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account secondAcc = AccSelector(userInput);
			
			// add the withdraw amount to the second account
			secondAcc.setAccBal(secondAcc.getAccBal() + withdraw);
			//----------------------------------------------------------------
		}
        
	    public static void CreateAccount(User user) {
	    	// random variable to create a unique account number
	    	Random random = new Random();
	    	
	    	// preload any info the user doesnt need to input or have access to	
	    	Account newAcc = new Account();
	    	newAcc.accActive = false;
	    	newAcc.setUsername(user.getUsername());
	    	newAcc.setAccOwner(user.getName());
	    	// gets a unique account number
	    	for (Account acc : accountsList) {
	    		do {
		    		newAcc.setAccNum(random.nextInt(89999) + 10000);
		    	} while (newAcc.getAccNum() == acc.getAccNum());		    	
			}
	    	newAcc.setAccBal(0);
	    	
	    	
        	System.out.println("\nWhat type of account would you like to create?");
        	
        	// retrieves selected account type and store
			String userInput = Validate.CheckAccType(sc.nextLine(), "Please use correct spelling. (Checking/Savings)");
			newAcc.setAccType(userInput);
			
			// add account to the accountsList
			accountsList.add(newAcc);
			
			// print to user account created then push back to menu
			System.out.println("\nHere is your new acount! (Account will await aproval by admin before editing can happen)\n" + newAcc.adminAccString());		
        }
	    
	    // for ADMIN------
	    public static void AdminDeposit() {
	    	// builds menu
	    	accountNames = AccountStrFormatter(true);
		    accounts = new Menu("Users Accounts", MenuItemConverter());

			// displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to deposit into?");
			
			// retrieves selected account
			int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account selectedAcc = AccSelector(userInput);
			
			// prompt the user and deposit amount into the selected account
			System.out.println("\nHow much would you like to deposit?");
			
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
					
					System.out.println(cyanString + "\n" + acc.accString() + resetString);
				}
			}						    
	    }
	    
        public static void AdminWithdraw() {
        	// string to output to screen
        	String transaction = "";
			// builds menu
			accountNames = AccountStrFormatter(true);
			accounts = new Menu("temp title", MenuItemConverter());
			
			// displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to withdraw from?");
			
			// retrieves selected account
			int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account selectedAcc = AccSelector(userInput);
			
			// prompt the user and withdraw amount from the selected account
			System.out.println("\nHow much would you like to withdraw?");
			
			// gets the desired amount from user to withdraw
			double withdraw = 0;
			do{
				if (withdraw < 0) {
					System.out.println("\nWithdraw amount is less than 0");
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
				}
				else if (withdraw > selectedAcc.getAccBal()) {
					System.out.println("\n\n\n\nWithdraw amount exceeds the account balance.");
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
				}
				else {withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1");}
			} while (withdraw < 0 && withdraw > selectedAcc.getAccBal());
						
			// subtract withdraw from the account if withdraw is equal to or greater than account balance
			selectedAcc.setAccBal(selectedAcc.getAccBal() - withdraw);					
    }
    
        public static void AdminTransfer() {
        	// builds menu
	    	accountNames = AccountStrFormatter(true);
	    	accounts = new Menu("User Accounts", MenuItemConverter());
	    	
	    	//------------------------WITHDRAW PORTION------------------------
	    	// displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to transfer from?");
	    	
	    	// retrieves selected account
			int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account firstAcc = AccSelector(userInput);
			
			// prompt the user and withdraw amount from the selected account
			System.out.println("\nHow much would you like to transfer?");
			
			// gets the desired amount from user to withdraw
			double withdraw = 0;
			do{
				if (withdraw < 0) {
					System.out.println("\nWithdraw amount is less than 0");
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
				}
				else if (withdraw > firstAcc.getAccBal()) {
					System.out.println("\n\n\n\nWithdraw amount exceeds the account balance.");
					withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1 and less than th account total");
				}
				else {withdraw = Validate.CheckDouble(sc.nextLine(), "Please enter a dollar amount in number format greater than -1");}
			} while (withdraw < 0 && withdraw > firstAcc.getAccBal());
						
			// subtract withdraw from the account if withdraw is equal to or greater than account balance
			//String originalFirst = firstAcc.accString(); // string to hold the original choice to exclude from future menu
			firstAcc.setAccBal(firstAcc.getAccBal() - withdraw);
			//----------------------------------------------------------------
			
			//------------------------DEPOSIT PORTION-------------------------
		    // builds menu
		    accounts = new Menu("User Accounts", MenuItemConverter());
		    // displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to transfer to?");
			
			// retrieves selected account
			userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account secondAcc = AccSelector(userInput);
			
			// add the withdraw amount to the second account
			secondAcc.setAccBal(secondAcc.getAccBal() + withdraw);
			//----------------------------------------------------------------
    }	    
        
        public static void AdminApprover() {
        	// builds menu
	    	accountNames = AccountStrFormatter(false);
	    	accounts = new Menu("User Accounts", MenuItemConverter());
	    	
	    	// displays menu	
			accounts.Display();
			System.out.println("\nWhat account would you like to approve/deny?");
			
			// retrieves selected account
			int userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			Account selectedAcc = AccSelector(userInput);
			
			// prompt the admin to approve or deny account
			System.out.println();
			if (Validate.CheckApproveDeny("\nWould you like to approve or deny this account? (input \"approve\" or \"deny\")", "Please input in a correct format") == true) {
				selectedAcc.accActive = true;	
				
				// update the referenced account in the accountsList
				for (Account acc : accountsList) {
					if (acc.getAccNum() == selectedAcc.getAccNum()) {
						acc = selectedAcc;
					}
				}			
			}
			else {
				// delete the referenced account in the accountsList
				for (Account acc : accountsList) {
					if (acc.getAccNum() == selectedAcc.getAccNum()) {
						accountsList.remove(acc);
			     }
			}
		  }
			// update the text files
			IO.outputToFiles(usersList, accountsList);
        }        
//==================================================================================================
		  
	    
//-----------------------------------------Selection Methods----------------------------------------
	    static Account AccSelector(int userInput) {
	    	// account place holder to store info
	    	Account selectedAcc = new Account();
	    	if (userInput >= 1 && userInput <= accountNames.size()) {
	    		/* 
	    		 * This line finds the account the user selected by comparing the userInput(int) to the 
	    		 * index of the LinkedHashTable, which stores the accountsList(data from file) index and a
	    		 * String formatted from the object Account.
	    		 * 
	    		 * This matches both the selected menu item and desired Account from the accountsList and returns 
	    		 * it to then edit
	    		 */	  
				selectedAcc = accountsList.get((int) accountNames.keySet().toArray()[userInput - 1]);
			} else {
				System.out.println("\n\n\nPlease type in a correct option.");
				accounts.Display();
				System.out.println("\nWhat account would you like to edit?");
				userInput = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				selectedAcc = AccSelector(userInput);
				}
	    	return selectedAcc;
	    }
//==================================================================================================
	
	    
//-------------------------------------------Other Methods------------------------------------------
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
	    
	    private static Map<Integer, String> AccountStrFormatter(String username) {
	    	// List to return
	    	Map<Integer, String> returnLst = new LinkedHashMap<Integer, String>();
	    	
	    	for (int i = 0; i < accountsList.size(); i++) {
				/* 
				 * Checks to first see if the account belongs to the user
	    		 * Then if the account type is correct
	    		 * Then checks if the account is active
				 */ 
				if (accountsList.get(i).getUsername().equalsIgnoreCase(username)  
					&& accountsList.get(i).accActive == true) {
					// 
					String lstItem = accountsList.get(i).accString();
							//accType + ": " + accountsList.get(i).getAccNum() + "\t" + balance.format(accountsList.get(i).getAccBal());
					returnLst.put(i, lstItem);
				}
			}
	    	
	    	return returnLst;
	    }
	    
	    private static Map<Integer, String> AccountStrFormatter(boolean active) {
	    	// List to return
	    	Map<Integer, String> returnLst = new LinkedHashMap<Integer, String>();
	    	
	    	for (int i = 0; i < accountsList.size(); i++) {
				/* 
				 * Checks to first see if the account belongs to the user
	    		 * Then if the account type is correct
	    		 * Then checks if the account is active
				 */ 
				if (accountsList.get(i).accActive == active) {
					// 
					String lstItem = accountsList.get(i).adminAccString();
					returnLst.put(i, lstItem);
				}
			}
	    	
	    	return returnLst;
	    }
	    
	    // converts the accountNames map into an array of items for the menu
	    private static String[] MenuItemConverter() {
	    	NumberFormat balance = NumberFormat.getCurrencyInstance();
	    	
	    	String s = accountNames.values().toString();
			String subS = (" " + s.substring(1, s.length() - 1));
			String[] sa = subS.split(",");
			String[] returnArray = new String[sa.length];
			for (int i = 0; i < sa.length; i++) {
				returnArray[i] = sa[i] + balance.format(accountsList.get((int)accountNames.keySet().toArray()[i]).getAccBal());
			}
			return returnArray;
	    }
//==================================================================================================
}
