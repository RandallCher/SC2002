package View.staff;

import java.util.Scanner;

import View.*;
import Controller.*;
import Model.*;

public class ModifySettingsView extends View {

	public void start() {
		while (true) {
			System.out.print("------------- SETTINGS MENU -------------\n"
					+ "1: Configure ticket prices\n"
					+ "2: Configure holidays\n"
					+ "3: Configure top five movies\n"
					+ "4: Exit\n\n"
					+ "Enter your choice: ");
			Scanner scan = new Scanner(System.in);
			int choice = 0;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
			}
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
				case 4:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.");
			}
		}
	}

	protected void configureTopFiveMovies() {
		// TODO - implement Settings.configureTopFiveMovies
		throw new UnsupportedOperationException();
	}

	protected void configureHolidays() {
		while (true) {
			System.out.println("**** Configuring Holidays ****");
			System.out.println("1: View current holidays\n"
					+ "2: Add a holiday"
					+ "3: Remove a holiday"
					+ "4: Cancel");
			Scanner scan = new Scanner(System.in);
			int choice;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				break;
			}
			// TODO
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
				case 4:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.");

			}
		}
	}

	/**
	 * This method allows the user to modify the base ticket price of a cinema
	 */
	protected void configureTicketPrice() {
		// this DOES NOT WORK yet bc there is no data
		System.out.println("**** Configuring Ticket Price ****");
		System.out.print("Enter cinema code: ");
		Scanner scan = new Scanner(System.in);
		String cineCode = scan.nextLine();

		Cinema cinema = CineplexController.getCinemaByCode(cineCode);
		if (cinema == null) {
			System.out.println("Invalid cinema code. Returning to menu...");
			return;
		}
		System.out.println("The current base ticket price for cinema " + cineCode + " is " + cinema.getBasePrice());

		System.out.print("Enter new base ticket price: ");
		double newPrice = scan.nextDouble();
		cinema.setBasePrice(newPrice);
		try {
			CineplexController.updateCinemaList();
			System.out.println("Ticket price changed successfully.");
		} catch (Exception e) {
			System.out.println("Failed to change ticket price.");
		}
		return;
	}

	protected void displayHolidays() {
		// TODO
	}

	protected void addHoliday() {
		// TODO

	}

	protected void removeHoliday() {
		// TODO

	}

}