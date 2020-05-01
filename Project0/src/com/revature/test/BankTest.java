package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.util.Bank;

class BankTest {
	static User user;
	static Account acc;
	static Account acc2;
	
	@Test
	void testWithdraw() {
		user = new User("test", "test", "test");
		Bank.usersList.add(user);
		acc = new Account("test", "test", 99999, 100.00, "Checking", true);
		Bank.accountsList.add(acc);
		Bank.Withdraw("Checking", "test");
		//After withdrawing $100
		assertEquals(acc.getAccBal(), 0.00, .001);
	}
	
	@Test
	void testDeposit() {
		user = new User("test", "test", "test");
		Bank.usersList.add(user);
		acc = new Account("test", "test", 88888, 100.00, "Checking", true);
		Bank.accountsList.add(acc);
		Bank.Deposit("Checking", "test");
		//After depositing $100
		assertEquals(acc.getAccBal(), 200.00, .001);
	}
	
	@Test
	void testTransfer() {
		user = new User("test", "test", "test");
		Bank.usersList.add(user);
		acc = new Account("test", "test", 77777, 100.00, "Checking", true);
		acc2 = new Account("test", "test", 66666, 100.00, "Savings", true);
		Bank.accountsList.add(acc);
		Bank.accountsList.add(acc2);
		Bank.Transfer("test");
		//After transferring $50 from acc 1 to acc 2
		assertEquals(acc.getAccBal(), 50.00, .001);
		assertEquals(acc2.getAccBal(), 150.00, .001);
	}
	
	@Test
	void testAdminWithdraw() {
		acc = new Account("test", "test", 55555, 100.00, "Checking", true);
		Bank.accountsList.add(acc);
		Bank.AdminWithdraw();
		//After withdrawing $50
		assertEquals(acc.getAccBal(), 50.00, .001);
	}
	
	@Test
	void testAdmiDeposit() {
		acc = new Account("test", "test", 44444, 50.00, "Checking", true);
		Bank.accountsList.add(acc);
		Bank.AdminDeposit();
		//After depositing $50
		assertEquals(acc.getAccBal(), 100.00, .001);
	}
	
	@Test
	void testAdminTransfer() {
		acc = new Account("test", "test", 33333, 100.00, "Checking", true);
		acc2 = new Account("test", "test", 22222, 100.00, "Savings", true);
		Bank.accountsList.add(acc);
		Bank.accountsList.add(acc2);
		Bank.Transfer("test");
		//After transferring $50 from acc 1 to acc 2
		assertEquals(acc.getAccBal(), 50.00, .001);
		assertEquals(acc2.getAccBal(), 150.00, .001);
	}
}
