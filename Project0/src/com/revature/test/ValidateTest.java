package com.revature.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.revature.beans.Account;
import com.revature.beans.Admin;
import com.revature.beans.User;
import com.revature.util.Bank;
import com.revature.util.Validate;

class ValidateTest {
	static User admin = new Admin("test","a", "admin");
	static Account acc;
	@Test
	void testActive() {
		acc = new Account("test", "test", 55555, 100.00, "Checking", false);
		Bank.accountsList.add(acc);
		Validate.setActive(acc, true);
		assertTrue(acc.accActive);
	}

}
