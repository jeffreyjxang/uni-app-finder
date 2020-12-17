package main;

import objects.UniversitiesInformation;

public class Launcher {

	public static void main(String[] args) {

		// Create and launch first screen
		Welcome.CreateWelcome();
		Welcome.GUI.setVisible(true);
		System.out.println("git check");

		UniversitiesInformation.setUniversities();

	}

}
