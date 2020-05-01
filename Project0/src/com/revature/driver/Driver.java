package com.revature.driver;

import java.util.Scanner;

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
	// lists
 
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
		
		// print lists
//		System.out.println(Bank.usersList.toString() + "\n");
//		System.out.println(Bank.accountsList.size());
		
		// starting menu
		mainMenu = new Menu("Welcome", "Login", "Register", "Exit");
		mainMenu.Display();
	
		// get and validate user input
		input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
		InitialSelection(input);	
		
		// call main menu
		System.out.println("\n\n\n");
		mainMenu = new Menu("Main Menu", "Checkings", "Savings", "Transfer money", "Request new account", "Exit");
		mainMenu.Display();

		// get and validate user/admin input
		if(user instanceof Admin) {
			mainMenu = new Menu("Main Menu", "Deposit", "Withdraw", "Transfer", "Approve/Deny", "Exit");
			mainMenu.Display();
			input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			AdminSelection(input);
		}
		else {
			mainMenu = new Menu("Main Menu", "Checkings", "Savings", "Request new account", "Exit");
			mainMenu.Display();
			input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(input);
		}

	}
		

//-----------------------------------------Selection Methods----------------------------------------
	static void InitialSelection(int userInput ) {
		int choice; // stores the users choices
		switch (userInput) {
		case 1:
			LoginSelection();
			break;
			
        case 2:
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
			
			user = Validate.register(username, password, name, Bank.usersList);
			
			if(user == null)
				System.out.println("\n\n\nUser already exists");
		}while(user == null);
	}

	static void MainSelection(int userInput) {
		int choice; // stores the users choices
		switch (userInput) {
		case 1: // sub-menus to Checking options
			System.out.println("\n\n\n");
			subMenu.newTitle("Checking");
			subMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			SubSelection(choice);
			break;
			
		case 2: //sub-menus to Savings options
			System.out.println("\n\n\n");
			subMenu.newTitle("Savings");
			subMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");	
			SubSelection(choice);
			break;
			
		case 3: // prompts user for transfer info
			System.out.println("\n\n\n");
			Bank.Transfer(username);
			
			// prompts user to continue or exit program
			TransactionPrompter();
			break;
			
		case 4: //prompts user and creates a new account
			
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
	
	private static void AdminSelection(int userInput) {
		int choice;
		
		switch(userInput) {
		case 1:
			Bank.AdminDeposit();
			break;
		case 2:
			Bank.AdminWithdraw();
			break;
		case 3:
			Bank.AdminTransfer();
			break;
		case 4:
			//Validate.setActive(null, false);
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
			MainSelection(choice);
		}
		else			
			System.exit(0);;
	}
//==================================================================================================
}
