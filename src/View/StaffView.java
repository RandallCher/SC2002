package View;

import java.util.Scanner;

import View.staff.*;
import Controller.CineplexController;
import Controller.IOController;

/**
 * This is the main staff view
 */
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
		// TODO remove this true
		// loggedIn = true;
		while (!loggedIn) {
			this.login();
		}
		while (true) {
			System.out.print("\n------------- STAFF MENU -------------\n"
					+ "1: Configure settings(ticket price, holidays, top 5 movies)\n"
					+ "2: Create/Update/Remove movie listing\n"
					+ "3: Create/Update/Remove cinema shows and showtimes\n"
					+ "4: Create new staff account\n"
					+ "5: Exit\n\n"
					+ "Enter your choice: ");
			Scanner scan = new Scanner(System.in);
			int choice = IOController.readUserChoice(5, 1);
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
					createNewStaff(scan);
					break;
				case 5:
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
		System.out.println("**** Staff Login ****");
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scan.next();
		System.out.print("Enter password: ");
		String password = scan.next();

		if (CineplexController.verification(username, password)) {
			loggedIn = true;
			System.out.println("Login successful.");
		} else {
			System.out.print("Login failed. Try again? (Y/N)");
			String reLogin = scan.next();
			if (!reLogin.toUpperCase().equals("Y")) {
				this.end();
				this.loggedIn = false;
			}
		}
		return;
	}

	/**
	 * This method creates a new staff account that can be used to login
	 * 
	 * @param scan
	 */
	public void createNewStaff(Scanner scan) {
		System.out.print("Enter new staff account username: ");
		String username = scan.nextLine();
		System.out.print("Enter new staff account password: ");
		String password = scan.nextLine();
		try {
			CineplexController.addStaffAccount(username, password);
			System.out.println("Succesfully added new staff account.");
		} catch (Exception e) {
			System.out.println("Failed to add new staff account.");
		}
	}
}