package com.revature.beans;

import java.util.ArrayList;

public class Admin extends User{
	public void editAccount(int userId, ArrayList<User> userList) {
		User selection;
		for(User user : userList)
			if(user.getUserId() == userId)
				selection = user;
		
		
	}
}