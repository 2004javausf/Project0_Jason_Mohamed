package com.revature.util;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;

public class Validate {
	
	// Scanner to take in user input
	public static Scanner userInput = new Scanner(System.in);

	//Checks the user input and will try and parse it to an int
	public static int CheckInt(String input, String errorMessage) {
		int numReturn;
		try {
			return Integer.parseInt(input);		
		}
		catch (NumberFormatException e) {
			System.out.println(errorMessage);
			
			// New user input and reset prompts
			String newInput = userInput.nextLine();
			numReturn = CheckInt(newInput, errorMessage);
		}	
		
		return numReturn;
	}
  
  //Checks the user input and will try and parse it to an double
	public static double CheckDouble(String input, String errorMessage) {
		try {
			return Double.parseDouble(input);		
		}
		catch (NumberFormatException e) {
			System.out.println(errorMessage);
			
			// New user input and reset prompts
			String newInput = userInput.nextLine();
			CheckDouble(newInput, errorMessage);
		}	
		
		return 0;
	}
	
	//Registers user and adds them to account list
	public static User register(String username, String password, String name, List<User> usersList, List<Account> accountsList) {		
		for(User user : usersList)
			if(username.equalsIgnoreCase(user.getUsername()))
				return null;
		
		User newUser = new User(username, password, name);
		usersList.add(newUser);
		
		IO.outputToFiles(usersList, accountsList);
		return newUser;
	}
	
	//Grants access to the program
	public static User login(String username, String password, List<User> usersList) {
		Iterator<User> it = usersList.iterator();
		
		//return the user that matches login if it exists
		while(it.hasNext()) {
			User u = it.next();
			if(u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password))
				return u;
		}
		
		//if not found:
		return null;
	}
	
	public static void setActive(Account acc, boolean active) {
		acc.accActive = active;
	}
	
	public static void cancelAccount(int accId, List<Account> accountsList) {
		for(Account acc : accountsList)
			if(acc.getAccNum() == accId)
				accountsList.remove(acc);
	}

  //Checks if the user chose yes or no
	public static boolean CheckYesNo(String prompt, String errorMessage) {
		//prompts the user and gets their input
		System.out.println("\n" + prompt);
		String newInput = userInput.nextLine();
		
		//if the user gives incorrect input will loop till its correct
		while (!(newInput.equalsIgnoreCase("y") || newInput.equalsIgnoreCase("yes") || newInput.equalsIgnoreCase("n") || newInput.equalsIgnoreCase("no"))) {
			System.out.println("\n" + errorMessage + "\n" + prompt);
			newInput = userInput.nextLine();
		}
	
		//if yes will return true, if no will return false
		if (newInput.equalsIgnoreCase("y") || newInput.equalsIgnoreCase("yes"))
			return true;
		else
			return false;
	}

}
