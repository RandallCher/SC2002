package View;

import java.io.IOException;

import View.staff.*;

import Controller.InputController;
import Controller.CineplexController;

/**
 * This is the main staff view
 * This basically prints all options for a staff member to select. 
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
		while (!loggedIn) {
			this.login();
		}
		while (true) {
			System.out.print("\n------------- STAFF MENU -------------\n"
					+ "1: Configure settings(ticket price, holidays, top 5 movies)\n"
					+ "2: Add/Remove cinemas\n"
					+ "3: Create/Update/Remove movie listings\n"
					+ "4: Create/Update/Remove showtimes\n"
					+ "5: Create a new staff account\n"
					+ "6: Remove a staff account from the database\n"
					+ "7: Back to previous page\n\n"
					+ "Enter your choice: ");
				
			int choice = InputController.readUserChoice(7, 1);
			switch (choice) {
				case 1:
					navigateNextView(this, new ModifySettingsView());
					break;

				case 2: 
					navigateNextView(this, new EditCinemaListView()); 
					break; 

				case 3:
					navigateNextView(this, new EditMovieListingView());
					break;

				case 4:
					navigateNextView(this, new EditShowtimeView());
					break;

				case 5:
					createNewStaff();
					break;

				case 6:
					removeStaff(); 
					break; 

				default: 
					this.end();
					break;
			}
		}
	}

	/**
	 * This method is used to log a staff member in using username and password
	 */
	public void login() {
		// Get user input
		System.out.println("**** Staff Login ****");

		String username = InputController.readString("Enter username: ");
		String password = InputController.readString("Enter password: ");

		if (CineplexController.verification(username, password)) {
			loggedIn = true;
			System.out.println("Login successful.");
		} else {
			boolean reLogin = InputController.confirmation("Login failed. Try again? (Y/N)");
			if (!reLogin) {
				this.end();
			}
		}
		return;
	}

	/**
	 * This method creates a new staff account that will be registered in the database
	 */
	public void createNewStaff() {
		String username, password, rePassword; 
		while (true){
			username = InputController.readString("Enter new staff account username: ");
			password = InputController.readString("Enter new staff account password: ");
			rePassword = InputController.readString("Please re-enter the password again: "); 

			if (password.equals(rePassword)) break; 
			else{
				System.out.println("The passwords do not match!"); 
				boolean reTry = InputController.confirmation("Failed to add new staff account. Try again? (Y/N)"); 
				if (!reTry) return; 
				else System.out.println(); 
		}

		}

		try {
			CineplexController.addStaffAccount(username, password);
			System.out.println("Succesfully added new staff account.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * This method allows a staff to remove other staff accounts from the database. 
	 */
	public void removeStaff(){
		String username, password; 
		username = InputController.readString("Enter username of the staff account to be removed: ");
		password = InputController.readString("Enter password of the staff account to be removed: ");
		if (!CineplexController.verification(username,password)) {
			System.out.println("Invalid Credentials."); 
			return; 
		}
		try{
	   		CineplexController.removeStaffAccount(username, password); 
			System.out.println("Removed the indicated staff account");

		} catch (IOException e){
			System.out.println("Error occured!. Failed to remove the indicated staff account");
			e.printStackTrace();
		}
	}

	
}