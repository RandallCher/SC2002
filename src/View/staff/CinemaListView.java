package View.staff;

import View.View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.IOController;
import Model.Cinema;
import Model.Movie;
import Model.Showtime;
import Model.Parameters.Cineplex;

public class CinemaListView extends View {

	private boolean help;

	public void start() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			// TODO - remove "incomplete once done"
			System.out.print("------------- CINEMA LIST MENU (INCOMPLETE) -------------\n"
					+ "1: View all cineplexes\n"
					+ "2: View cinemas in a cineplex\n"
					+ "3: Add a cinema"
					+ "4: Exit\n\n"
					+ "Please enter your choice: ");
			int choice = IOController.readUserChoice(3, 1);
			switch (choice) {
				case 1:
					displayCineplexList(scan);
					break;
				case 2:
					displayCinemasByCineplex(scan);
					break;
				case 3:
					addCinema(scan);
					break;
				case 4:
					this.end();
					break;
			}
		}
	}

	/**
	 * This method displays all cineplexes
	 * 
	 * @param scan is the Scanner
	 */
	public void displayCineplexList(Scanner scan) {
		// TODO - implement displayCineplexList
	}

	/**
	 * This method displays all cinemas in a cineplex.
	 * 
	 * @param scan
	 */
	public void displayCinemasByCineplex(Scanner scan) {
		System.out.print("Enter cineplex name: ");
		String cineplexStr = scan.nextLine();

		// Convert input, check if cineplex exists
		Cineplex cineplex;

		// Display cinemas in cineplex
		ArrayList<Cinema> cinemaList = CineplexController.getCinemaList(cineplex);
		if (cinemaList == null) {
			System.out.println("No cinema at the cineplex.");
		} else {
			for (Cinema cinema : cinemaList) {
				System.out.print(cinema + "(" + (cinema.is3D() ? "3D" : "Digital") + ") ");
			}
			System.out.println();
		}
	}

	/**
	 * This method allows the staff to add a new cinema.
	 * 
	 * @param scan
	 */
	public void addCinema(Scanner scan) {
		// TODO - implement addCinema

		System.out.print("Enter cineplex of new cinema: ");
		String cineplex = scan.next();
		// might want to imlement a readCineplex method (use in
		// displayCinemasByCineplex) also
		System.out.print("Enter name of cinema: ");

		// Cinema attributes
		// private boolean isPlatinum;
		// private boolean is3D;
		// private String code;
		// private double basePrice;
		// private Cineplex cineplex;
	}
}
