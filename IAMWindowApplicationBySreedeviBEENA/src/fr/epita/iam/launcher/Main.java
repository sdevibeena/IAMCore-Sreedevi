package fr.epita.iam.launcher;

import fr.epita.iam.windowApp.Login;


/**
 * Date: 22 February 2018
 * This is an Identity Access and Manaagement program built with a Desktop GUI.
 * This application stores 3 important details (UID, Name, email-ID) regarding a person.
 * Primary functions include Create, update, search and delete a details regarding a person.
 * @author Sreedevi BEENA
 *
 */
public class Main {
	/**
	 * This is the main function which calls in the Login class. (GUI window).
	 * @param args Command line argument
	 */
	public static void main(String [] args) {
		Login initiate =new Login();
		initiate.launch();
	}
}
