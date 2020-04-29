package com.revature.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.revature.beans.Account;
import com.revature.beans.User;

public class Bank {
// Lists to store the objects for banking app
	public static Map<User,ArrayList<Account>> userAccounts = new HashMap<>();
}
