package View.staff;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import View.*;
import Controller.*;
import Model.*;


/**
 * This class represents the view for modifying the system settings 
 */
public class ModifySettingsView extends View {

	public void start() {
		while (true) {
			System.out.print("------------- SETTINGS MENU -------------\n"
					+ "1: Configure ticket prices\n"
					+ "2: Configure holidays\n"
					+ "3: Configure top five movies\n"
					+ "4: Back to previous page\n\n"
					+ "Enter your choice: ");
			int choice = InputController.readUserChoice(4,1);
			
			switch (choice) {
				case 1:
					this.configureTicketPrice();
					break;
				case 2:
					this.configureHolidays();
					break;
				case 3:
					this.configureTopFiveMovies();
					break;
				default:
					this.end();
			}
		}
	}

	/**
	 * This method allows the user to modify the top five movie ranking order
	 */
	protected void configureTopFiveMovies() {
		System.out.println("**** Configuring Top Five Movies ****");
	

		// if file exists but empty file default value for movieOrder will be true
		if (CineplexController.getSystem().get("movieOrder") == null) {
			CineplexController.getSystem().put("movieOrder", true);
			try {
			CineplexController.updateSystem();
			} catch (Exception e) {
			e.printStackTrace(); 
		}
		}

		

		boolean isRatingRanked = CineplexController.getSystem().get("movieOrder");
		if (isRatingRanked) {
			System.out.print(
					"Top Five movies are currently ranked by ratings.\nChange to ranking by sales? (Y to confirm) ");
		} else {
			System.out.print(
					"Top Five movies are currently ranked by sales.\nChange to ranking by ratings? (Y to confirm) ");
		}

		boolean choice = InputController.confirmation();
		if (!choice) {
			System.out.println("No changes made.\n");
			return;
		}

		try {
			CineplexController.getSystem().put("movieOrder", !isRatingRanked);
			CineplexController.updateSystem();
			System.out.println("Rankings changed successfully.\n");
		} catch (Exception e) {
			System.out.println("Failed to change rankings.\n");
		}
		return;
	}

	/**
	 * This method allows the user to choose how to configure holidays
	 */
	protected void configureHolidays() {
		while (true) {
			System.out.println("**** Configuring Holidays ****");
			System.out.print("1: View current holidays\n"
					+ "2: Add a holiday\n"
					+ "3: Remove a holiday\n"
					+ "4: Exit\n\n"
					+ "Enter your choice: ");
			int choice = InputController.readUserChoice(4, 1);
			switch (choice) {
				case 1:
					this.displayHolidays();
					break;
				case 2:
					this.addHoliday();
					break;
				case 3:
					this.removeHoliday();
					break;

				default: 
					this.end();
			}
		}
	}

	/**
	 * This method allows the user to modify the base ticket price of a cinema
	 */
	protected void configureTicketPrice() {
		// this DOES NOT WORK yet bc there is no data
		System.out.println("**** Configuring Ticket Price ****");
	

		String cineCode = InputController.readString("Enter cinema code: ");
		Cinema cinema = CineplexController.getCinemaByCode(cineCode);
		if (cinema == null) {
			System.out.println("Invalid cinema code.\n");
			return;
		}
		System.out.println("The current base ticket price for cinema " + cineCode + " is " + cinema.getBasePrice());

		double newPrice = InputController.readDouble("Enter new base ticket price: ");
		cinema.setBasePrice(newPrice);
		try {
			CineplexController.updateCinemaList();
			System.out.println("Ticket price changed successfully.\n");
		} catch (Exception e) {
			System.out.println("Failed to change ticket price.\n");
		}
		return;
	}

	/**
	 * This method displays all the current holidays in the system
	 */
	protected void displayHolidays() {
		HashMap<String, Holiday> holidaysList = CineplexController.getHolidayList();
		if (holidaysList.isEmpty()) {
			System.out.println("No holidays are registered in the system yet.\n");
			return;
		}
		System.out.println("\n**** Holiday List ****");
		int i = 1;
		for (String holidayDate : holidaysList.keySet()) {
			System.out.println(i++ + ": " + holidaysList.get(holidayDate));
		}
		System.out.println();
		return;
	}

	/**
	 * This method adds a holiday to the system
	 */
	protected void addHoliday() {
		Date date = InputController.readDateddMMyyyy("Enter new holiday date in format 'dd/MM/yyyy': ");

		String name = InputController.readString("Enter holiday name: ");

		String dateString = new SimpleDateFormat().format(date);
		Holiday holiday = new Holiday(name, date);

		try {
			CineplexController.addHoliday(dateString, holiday);
			System.out.println("Successfully added the holiday.\n");
		} catch (Exception e) {
			System.out.println("Failed to add the holiday.\n");
		}
		return;
	}

	/**
	 * This method removes a holiday from the system
	 */
	protected void removeHoliday() {
		this.displayHolidays();
		HashMap<String, Holiday> holidaysList = CineplexController.getHolidayList();

		
		Date date = InputController.readDateddMMyyyy("Enter holiday dates to remove in format 'dd/MM/yyyy': ");
		String dateString = new SimpleDateFormat().format(date);
		holidaysList.remove(dateString);
		try {
			CineplexController.updateHolidayList();
			System.out.println("Successfully deleted the holiday.\n");
		} catch (Exception e) {
			System.out.println("Failed to delete the holiday.\n");
		}
		return;
	}

}