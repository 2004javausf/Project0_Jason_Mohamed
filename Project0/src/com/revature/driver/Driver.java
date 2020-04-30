package com.revature.driver;

import java.util.Scanner;

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
	static Menu subMenu;
	// lists
 
	// user input
	static Scanner sc = new Scanner(System.in);
	// login info
	static String username = "";
	static String password = "";
	// User object
	static User user = new User();
	
	
	public static void main(String[] args) {
		// Pull from text files and store
		IO.readFiles();
		
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
		mainMenu = new Menu("Main Menu", "Checkings", "Savings", "Request new account", "Exit");
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
			subMenu = new Menu("Checking", "Deposit", "Withdraw", "Transfer money", "Exit");
			subMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			System.out.println("\n\n\n\n");
			break;
			
		case 2: //sub-menus to Savings options
			subMenu.newTitle("Savings");
			subMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			System.out.println("\n\n\n\n");
			break;
			
		case 3: //prompts user and creates a new account
			
			break;
			
		case 4: //saves the bank info to their respective files and exits the program
			IO.outputToFiles(Bank.usersList, Bank.accountsList);
			System.out.println();
			break;
			
		default: // if user chooses option not listed throw back menu to user
			System.out.println("\n\n\n\nPlease type in a correct option.");
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
			System.out.println("\n\n\n\n");
			Bank.Deposit(subMenu.getTitle(), username);
			break;
			
		case 2: // prompts user for withdraw info
			System.out.println("\n\n\n\n");
			Bank.Withdraw(subMenu.getTitle(), username);
			break;
			
		case 3: // prompts user for transfer info
			System.out.println("\n\n\n\n");

			break;
			
		case 4: // exits back to main
			System.out.println("\n\n\n\n");
			mainMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(choice);
			break;

		default: // if user chooses option not listed throw back menu to user
			System.out.println("\n\n\n\nPlease type in a correct option.");
			subMenu.Display();
			choice = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			SubSelection(choice);
			break;
		}
		}

//==================================================================================================

}
