package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

public class IO {
    // Text files
	public static final String userFile="user.txt";
	public static final String accountFile="account.txt";
	
	// write method
	public static void outputToFiles(List<User> users, List<Account> accounts) {
		try {
			// OOS for each text file
			ObjectOutputStream usersOut = new ObjectOutputStream(new FileOutputStream(userFile));
			ObjectOutputStream accountsOut = new ObjectOutputStream(new FileOutputStream(accountFile));
			
			// write the lists to the text files
			usersOut.writeObject(users);
			accountsOut.writeObject(accounts);
			
			// close the streams
			usersOut.close();
			accountsOut.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// read method
	@SuppressWarnings("unchecked")
	public static void readFiles() {
		try {
			// OIS for each text file
			ObjectInputStream usersIn= new ObjectInputStream(new FileInputStream(userFile));
			ObjectInputStream accountsIn= new ObjectInputStream(new FileInputStream(accountFile));
					
			// store the info from the file to the program
			Bank.usersList = (ArrayList<User>)usersIn.readObject();
			Bank.accountsList = (ArrayList<Account>)accountsIn.readObject();
			
			// close the streams
			usersIn.close();
			accountsIn.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Missing files");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: No data to pull from project files");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
