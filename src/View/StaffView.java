package View;

import java.util.Scanner;

// cant find rn
// import Controller.CineplexController.verification;

public class StaffView extends View {

	private boolean loggedIn;

	public void start() {
		System.out.println("\nStaff Login");
		while (!loggedIn) {
			this.login();
		}
		while (true) {
			System.out.println("\n------------- STAFF MENU -------------\n"
					+ "1: Configure Settings\n"
					+ "2: Create/Update/Edit movie listing\n"
					+ "3: Create/Update/Remove cinema showtimes and the movies to be shown\n"
					+ "4: Exit\n\n"
					+ "Enter your choice: ");
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			switch (choice) {
				case 1:
					break;
				case 2:
					break;
				case 3:
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