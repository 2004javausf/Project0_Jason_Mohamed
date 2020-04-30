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
	static Menu mainMenu = new Menu("Main Menu", "Checkings", "Savings", "Request new account", "Exit");
	static Menu subMenu = new Menu("temp title", "Deposit", "Withdraw", "Transfer money", "Exit");
	// lists
 
	// user input
	static Scanner sc = new Scanner(System.in);
	// login info
	static String username = "username";
	static String password = "";
	// User object
	static User user = new User();
	
	
	public static void main(String[] args) {
		// Pull from text files and store
		IO.readFiles();
		
		//users
//		User user1 = new User("username", "password", "name");
//		User user2 = new User("ClaryJ", "password0", "Jason W. Clary");
//		User admin1 = new Admin("admin", "admin", "admin");
		
//		// Accounts
//		Account acc1 = new Account("username", "name", 12345, 100.50, "Checking", true);
//		Account acc2 = new Account("username", "name", 12346, 200.66, "Checking", false);
//		Account acc3 = new Account("username", "name", 12347, 300, "Savings", true);
//		
//		Account acc4 = new Account("ClaryJ", "Jason W. Clary", 54321, 25, "Checking", true);
//		Account acc5 = new Account("ClaryJ", "Jason W. Clary", 54322, 50, "Checking", false);
//		Account acc6 = new Account("ClaryJ", "Jason W. Clary", 54323, 75, "Savings", true);

		
//		// Add accounts to list
//		Bank.accountsList.add(acc1);
//		Bank.accountsList.add(acc2);
//		Bank.accountsList.add(acc3);
//		Bank.accountsList.add(acc4);
//		Bank.accountsList.add(acc5);
//		Bank.accountsList.add(acc6);

		
		// Add accounts to list
//		Bank.usersList.add(user1);
//		Bank.usersList.add(user2);
//		Bank.usersList.add(admin1);	
		
		// print lists
		System.out.println(Bank.usersList.toString() + "\n");
		System.out.println(Bank.accountsList.size());
		
//		loginMenu.Display();
//		
//		System.out.println("What is your username?");
//		username = sc.nextLine();
//		readUserFile();
//		System.out.println(user.toString());
//		
//		System.out.println("What is your password?");
//		password = sc.nextLine();
		
		// call main menu
		mainMenu.Display();
		
		// get and validate user input
		int input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
		MainSelection(input);
	}
		
//-----------------------------------------Selection Methods----------------------------------------
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
			
		case 3: //prompts user and creates a new account
			
			break;
			
		case 4: //saves the bank info to their respective files and exits the program
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
			
		case 3: // prompts user for transfer info
			System.out.println("\n\n\n");

			break;
			
		case 4: // exits back to main
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
		if (Validate.CheckYesNo("Would you like to make another transaction? (Y/N)", "Please reply in the correct format") == true) {
			System.out.println("\n\n\n\n");
			mainMenu.Display();
			int choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(choice);
		}
		else
			IO.outputToFiles(Bank.usersList, Bank.accountsList);
			System.exit(0);;
	}
//==================================================================================================
}
