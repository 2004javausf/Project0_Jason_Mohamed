package com.revature.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.revature.beans.User;

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
	
	//Registers user and adds them to account list
	public static boolean register(String username, String password, String name, ArrayList<User> usersList) {		
		for(User user : usersList)
			if(username.equalsIgnoreCase(user.getUsername())) {
				//System.out.println("username already exists");
				return false;
			}
		
		usersList.add(new User(username, password, name));
		return true;
	}
	
	public static User login(String username, String password, ArrayList<User> usersList) {
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

	public void saveData(ArrayList<User> userList) throws IOException {
		ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("users.txt"));
		
		objectOut.writeObject(userList);
		objectOut.close();
	}

}
