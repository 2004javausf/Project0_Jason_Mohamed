package com.revature.beans;

public class Admin extends User{

	public Admin() {
		super();
	}
	
	public Admin(String username, String password, String name) {
		super(username, password, name);
	}
	
//	public void editAccount(int userId, ArrayList<User> userList) {
//		User selection;
//		for(User user : userList)
//			if(user.getUserId() == userId)
//				selection = user;
//		
//		
//	}
}
