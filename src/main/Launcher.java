package main;

import objects.UniversitiesInformation;

/*
 * Launcher class is the application class that runs the program
 */
public class Launcher {

	public static void main(String[] args) {

		// Create and launch first screen
		Welcome.CreateWelcome();
		Welcome.GUI.setVisible(true);
		
		// Setup universities information

		UniversitiesInformation.setUniversities();
	}

}