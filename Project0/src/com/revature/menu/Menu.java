package com.revature.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
// Menu variables
	private String title;
	private List<String> menuItems = new ArrayList<String>();
	
	// Strings to change the color of other Strings printed to the screen
	private static final String cyanString = "\u001B[36m";
	private static final String resetString = "\u001B[0m";
	
	// Con for a very basic menu
	public Menu() {
		title = "Menu";

	}
	
    // Con for custom menu taking in a title and items to display in menu
	public Menu(String title, String...menuItems) {
		this.title = title;
		
		// Cycles through the user inputs to add the list
		for (String item : menuItems) {
			this.menuItems.add(item);
		}
	}
	
	// Adds an item to the menu list 
	public void addMenuItem (String item) {
		menuItems.add(item);
	}
	
	// Displays the menu and all its items to the user
	public void display() {
		System.out.println("=============================");
		System.out.println(cyanString + "\t" + title + resetString);
		System.out.println("=============================");
		
		int counter = 1;
		for (String item : menuItems) {
			System.out.println(cyanString + counter++ + ") " + item + resetString);
		}
	}

}