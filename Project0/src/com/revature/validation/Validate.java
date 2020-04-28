package com.revature.validation;

import java.util.Scanner;

public class Validate {
	
	// Scanner to take in user input
	public static Scanner userInput = new Scanner(System.in);

//Checks the user input and will try and parse it to an int
public static int CheckInt(String input, String errorMessage) {
	try {
		return Integer.parseInt(input);		
	}
	catch (NumberFormatException e) {
		System.out.println(errorMessage);
		
		// New user input and reset prompts
		String newInput = userInput.nextLine();
		CheckInt(newInput, errorMessage);
	}	
	
	return 0;
}

}
