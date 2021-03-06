package com.revature.driver;

import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Admin;
import com.revature.beans.User;
import com.revature.util.Bank;
import com.revature.util.IO;
import com.revature.util.Menu;
import com.revature.util.Validate;

public class Driver {
	// ----------Global variables----------
	// menu objects
	static final Menu loginMenu = new Menu("Login"); // wont change
	static Menu mainMenu;
	static Menu subMenu = new Menu("temp title", "Deposit", "Withdraw", "Exit");
	// user input
	static Scanner sc = new Scanner(System.in);
	// User object
	static User user = new User();
	// login info
	static String username = user.getUsername();
	static String password = user.getPassword();
	
	
	public static void main(String[] args) {
		// Pull from text files and store
		IO.readFiles();
		int input;

		// starting menu
		mainMenu = new Menu("Welcome", "Login", "Register", "Exit");
		mainMenu.Display();
	
		// get and validate user input
		input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
		InitialSelection(input);
		
		// get and validate user/admin input
		if(user instanceof Admin) {
			// call admin menu
			System.out.println("\n\n\n");
			mainMenu = new Menu("Main Menu", "Deposit", "Withdraw", "Transfer Money", "Approve/Deny", "Exit");
			mainMenu.Display();
			
			// get and validate user input
			input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			AdminSelection(input);
		}
		else {
			// call main menu
			System.out.println("\n\n\n");
			mainMenu = new Menu("Main Menu", "Checkings", "Savings", "Transfer Money", "Request new account", "Exit");
			mainMenu.Display();
			
			// get and validate user input
			input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(input);
		}		
	}
		

//-----------------------------------------Selection Methods----------------------------------------
	static void InitialSelection(int userInput ) {
		int choice; // stores the users choices
		switch (userInput) {
		case 1:
			System.out.println("\n\n\n");
			LoginSelection();
			break;
			
        case 2:
        	System.out.println("\n\n\n");
        	RegisterSelection();
			break;
			
        case 3:
        	System.exit(0);
	        break;

		default:
			System.out.println("\n\n\nPlease type in a correct option.");
			mainMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			InitialSelection(choice);
			break;
		}
	}
	
	static void LoginSelection() {
		// Login
		do {
			loginMenu.Display();
			
			System.out.println("What is your username?");
			username = sc.nextLine();
			
			System.out.println("\nWhat is your password?");
			password = sc.nextLine();
			
			user = Validate.login(username, password, Bank.usersList);
			
			if(user == null)
				System.out.println("\n\n\nUsername/password not found");
		}while(user == null);
	}
	
	static void RegisterSelection() {

		do {
			loginMenu.Display();
			
			System.out.println("Enter a new username");
			username = sc.nextLine();
			
			System.out.println("Enter a new password");
			password = sc.nextLine();
			
			System.out.println("Enter a name");
			String name = sc.nextLine();
			
			user = Validate.register(username, password, name, Bank.usersList, Bank.accountsList);
			
			if(user == null)
				System.out.println("\n\n\nUser already exists");
		}while(user == null);
	}
	
	static void AdminSelection(int userInput) {
		int choice; // stores the users choices		
		switch(userInput) {
		case 1: // prompts admin for deposit info
			System.out.println("\n\n\n");
			
			// checks to see if accounts exist
			if (AccountsChecker("") == true) {
				Bank.AdminDeposit();
				
				// prompts user to continue or exit program
				TransactionPrompter();
			}
			else {
				System.out.println("There are no active accounts to edit.");
				mainMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				MainSelection(choice);
			}			
			break;
		case 2: // prompts admin for withdraw info
			System.out.println("\n\n\n");

			// checks to see if accounts exist
			if (AccountsChecker("") == true) {
				Bank.AdminWithdraw();
				
				// prompts user to continue or exit program
				TransactionPrompter();
			}
			else {
				System.out.println("There are no active accounts to edit.");
				mainMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				MainSelection(choice);
			}			
			break;
		case 3: // prompts admin for transfer info
			System.out.println("\n\n\n");

			// checks to see if accounts exist
//			if (AccountsChecker("Transfer") == true) {
				Bank.AdminTransfer();
				
				// prompts user to continue or exit program
				TransactionPrompter();
//			}
//			else {
//				System.out.println("There are no, or less than 2, accounts to edit.");
//				mainMenu.Display();
//				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
//				MainSelection(choice);
//			}			
//			break;
		case 4: //approve/deny
			System.out.println("\n\n\n");
			

			// checks to see if accounts exist
			if (AccountsChecker("Admin") == true) {
				Bank.AdminApprover();
				
				// shoot the admin back to the main
				mainMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				MainSelection(choice);
			}
			else {
				System.out.println("There are no inactive accounts to edit.");
				mainMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				MainSelection(choice);
			}		
			break;
		case 5: //saves the bank info to their respective files and exits the program
			IO.outputToFiles(Bank.usersList, Bank.accountsList);
			System.exit(0);
			break;
			
		default: // if user chooses option not listed throw back menu to user
			System.out.println("\n\n\nPlease type in a correct option.");
			mainMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(choice);
			break;			
		}
	}

	static void MainSelection(int userInput) {
		int choice; // stores the users choices
		switch (userInput) {
		case 1: // sub-menus to Checking options
			System.out.println("\n\n\n");
			subMenu.newTitle("Checking");
			
			if (AccountsChecker("Checking") == true) {
				subMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				SubSelection(choice);
			}
			else {
				System.out.println("There are no " + subMenu.getTitle() + "accounts to edit.");
				mainMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				MainSelection(choice);
			}			
			break;
			
		case 2: //sub-menus to Savings options
			System.out.println("\n\n\n");
			subMenu.newTitle("Savings");
			
			if (AccountsChecker("Savings") == true) {
				subMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");	
				SubSelection(choice);
			}
			else {
				System.out.println("There are no " + subMenu.getTitle() + "accounts to edit.");
				mainMenu.Display();
				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
				MainSelection(choice);
			}			
			break;
			
		case 3: // prompts user for transfer info
			System.out.println("\n\n\n");
			
			// checks to see if accounts exist
//			if (AccountsChecker("Transfer") == true) {
				Bank.Transfer(username);
				
				// prompts user to continue or exit program
				TransactionPrompter();
//			}
//			else {
//				System.out.println("There are no, or less than 2, accounts to edit.");
//				mainMenu.Display();
//				choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
//				MainSelection(choice);
//			}
			break;
			
		case 4: //prompts user and creates a new account
			System.out.println("\n");
			Bank.CreateAccount(user);
			
			// prompts user to continue or exit program
			TransactionPrompter();
			break;
			
		case 5: //saves the bank info to their respective files and exits the program
			IO.outputToFiles(Bank.usersList, Bank.accountsList);
			System.exit(0);
			break;
			
		default: // if user chooses option not listed throw back menu to user
			System.out.println("\n\n\nPlease type in a correct option.");
			mainMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(choice);
			break;
		}
	}

	static void SubSelection(int userInput) {
		int choice; // stores the users choices
		switch (userInput) {
		case 1: // prompts user for deposit info
			System.out.println("\n\n\n");
			Bank.Deposit(subMenu.getTitle(), username);
			
			// prompts user to continue or exit program
			TransactionPrompter();
			break;
			
		case 2: // prompts user for withdraw info
			System.out.println("\n\n\n");
            Bank.Withdraw(subMenu.getTitle(), username);
			
			// prompts user to continue or exit program
			TransactionPrompter();
			break;
			
		case 3: // exits back to main
			System.out.println("\n\n\n");
			mainMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(choice);
			break;

		default: // if user chooses option not listed throw back menu to user
			System.out.println("\n\n\nPlease type in a correct option.");
			subMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			SubSelection(choice);
			break;
		}
	}

//==================================================================================================

//-------------------------------------------Other Methods------------------------------------------
	// prompts the user to make another transaction or exit program
	static void TransactionPrompter() {
		// save the new account information to the file
		IO.outputToFiles(Bank.usersList, Bank.accountsList);
		
		// prompt the user to continue and execute
		if (Validate.CheckYesNo("Would you like to make another transaction? (Y/N)", "Please reply in the correct format") == true) {
			System.out.println("\n\n\n\n");
			mainMenu.Display();
			int choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			if (user instanceof Admin) {
				AdminSelection(choice);
			}
			else {MainSelection(choice);}
		}
		else			
			System.exit(0);;
	}
    
	// checks to see if accounts exist
	static boolean AccountsChecker(String checkType) {
		// if there are no checking accounts
		if (checkType.equalsIgnoreCase("Checking")) {
			for (Account acc : Bank.accountsList) {
				if (acc.getAccType().equalsIgnoreCase(checkType) && acc.accActive == true) {
					return true;
				} 
			}
			return false;
		}
		// if there are no savings accounts
		else if (checkType.equalsIgnoreCase("Savings")) {
			for (Account acc : Bank.accountsList) {
				if (acc.getAccType().equalsIgnoreCase(checkType) && acc.accActive == true) {
					return true;
				} 
			}
			return false;
		}
		// if there are no active accounts
		else if (checkType.equalsIgnoreCase("Transfer")) {
			for (Account acc : Bank.accountsList) {
				int counter = 0;
				if (acc.accActive == true) {
					counter++;
					if (counter > 1) {
						return true;
					}
				}				 
			}
			return false;
		}		
		// if there are no inactive accounts
		else if (checkType.equalsIgnoreCase("Admin")) { 
			for (Account acc : Bank.accountsList) {
				if (acc.accActive == false) {
					return true;
				}
				else if (Bank.accountsList.size() == 0) {
					return false;
				}
			}
		}
		// if there are no active accounts
		else {
			for (Account acc : Bank.accountsList) {
				if (acc.accActive == true) {
					return true;
				}
				else if (Bank.accountsList.size() == 0) {
					return false;
				} else {return false;}
			}
		}
		
		return false;
	}
//==================================================================================================
}
