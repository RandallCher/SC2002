package View;

import java.util.Scanner;

import Controller.CineplexController.verification;

public class StaffView extends View {

	private boolean loggedIn;

	protected void start() {
		while (!loggedIn) {
			this.login();
		}

	}

	/**
	 * This method is used to log a staff member in using username and password
	 */
	public void login() {
		// Get user input
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scan.next();
		System.out.print("Enter password: ");
		String password = scan.next();

		// verification
		if (verification())
	}

	/**
	 * 
	 * @param option
	 */
	public void editMovieListings(int option) {
		// TODO - implement StaffView.editMovieListings
		throw new UnsupportedOperationException();
	}

}