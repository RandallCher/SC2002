package View;

import java.util.Scanner;

import View.staff.*;

// cant find rn
// import Controller.CineplexController.verification;

public class StaffView extends View {

	private boolean loggedIn;

	/**
	 * StaffView constructor initialises class attributes
	 */
	public StaffView() {
		this.loggedIn = false;
	}

	/**
	 * This method displays the menu and processes user input on what
	 * the staff member wants to do
	 */
	public void start() {
		System.out.println("\nStaff Login");
		while (!loggedIn) {
			this.login();
		}
		while (true) {
			System.out.println("\n------------- STAFF MENU -------------\n"
					+ "1: Configure settings(ticket price, holidays, top 5 movies)\n"
					+ "2: Create/Update/Remove movie listing\n"
					+ "3: Create/Update/Remove cinema shows and showtimes\n"
					+ "4: Exit\n\n"
					+ "Enter your choice: ");
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			switch (choice) {
				case 1:
					navigateNextView(this, new ModifySettingsView());
					break;
				case 2:
					navigateNextView(this, new EditMovieListingView());
					break;
				case 3:
					navigateNextView(this, new EditShowtimeView());
					break;
				case 4:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.");
			}
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
		// if (verification(username, password)) {
		// loggedIn = true;
		// System.out.println("Login successful.");
		// } else {
		// System.out.println("Login failed. Try again? (Y/N)");
		// String reLogin = scan.next();
		// if (!reLogin.toUpperCase().equals("Y")) {
		// this.end();
		// }
		// }
		this.loggedIn = true;
		return;
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