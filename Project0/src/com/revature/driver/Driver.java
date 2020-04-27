package com.revature.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.menu.Menu;
import com.revature.validation.Validate;

public class Driver {
	// ----------Global variables----------
	// menu objects
	static final Menu loginMenu = new Menu("Login"); // wont change
	static Menu mainMenu;
	// lists
//	static List<User> users = new 
	// user input
	static Scanner sc = new Scanner(System.in);
	// login info
	static String username;
	static String password;
	// User object
	static User user = new User();
	
	
	public static void main(String[] args) {
		
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
		mainMenu = new Menu("Main Menu", "Checkings", "Savings");
		mainMenu.Display();
		
		// get and validate user input
		int input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
		MainSelection(input);
	}
		
	static void MainSelection(int userInput) {
		switch (userInput) {
		case 1:
			
			break;
			
		case 2:
			
			break;
			
		default: // if user chooses option not listed throw back menu to user
			System.out.println("\n\n\n\nPlease type in a correct option.");
			int input = Validate.CheckInt(sc.nextLine(), "Please enter a whole number for selection.");
			MainSelection(input);
			break;
		}
	}
		
	
	@SuppressWarnings("unchecked")
	public static void readUserFile() {
		try {
			ObjectInputStream objectIn= new ObjectInputStream(new FileInputStream("user.txt"));
			
			User checkedUser = (User)objectIn.readObject();
			if (checkedUser.getUsername() == username) {
				user = checkedUser;
			}
			
			//humanList= (ArrayList<Human>)objectIn.readObject();
			objectIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
