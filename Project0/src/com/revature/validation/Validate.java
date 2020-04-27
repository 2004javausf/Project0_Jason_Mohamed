package com.revature.validation;

import java.util.Scanner;

public class Validate {
	
	// Scanner to take in user input
	public static Scanner userInput = new Scanner(System.in);

//Checks the user input and will try and parse it to an int
public static boolean CheckInt(String input, String errorMessage) {
	try {
		Integer.parseInt(input);
		return true;
	}
	catch (NumberFormatException e) {
		System.out.println(errorMessage);
		
		// New user input and reset prompts
		String newInput = userInput.nextLine();
		CheckInt(newInput, errorMessage);
	}	
	return false;
}

}
