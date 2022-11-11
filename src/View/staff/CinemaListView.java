package View.staff;

import View.View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.InputController;
import Model.Cinema;
import Model.Movie;
import Model.Showtime;
import Model.Parameters.Cineplex;

/**
 * This
 */
public class CinemaListView extends View {

	private boolean help;

	public void start() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("------------- CINEMA LIST MENU -------------\n"
					+ "1: View all cineplexes\n"
					+ "2: View cinemas in a cineplex\n"
					+ "3: Add a cinema\n"
					+ "4: Exit\n\n"
					+ "Please enter your choice: ");
			int choice = InputController.readUserChoice(4, 1);
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
		System.out.println();
		int index = 0;
		System.out.println("**** Cineplex List ****");
		for (Cineplex c : Cineplex.values()) {
			index++;
			System.out.println(index + ": " + c);
		}
		System.out.println();
	}

	/**
	 * This method displays all cinemas in a cineplex.
	 * 
	 * @param scan is the scanner
	 */
	public void displayCinemasByCineplex(Scanner scan) {
		// Get cineplex
		displayCineplexList(scan);
		System.out.print("Enter cineplex id to view cinemas: ");
		int cineplexID = InputController.readUserChoice(Cineplex.values().length, 1);
		Cineplex cineplex = Cineplex.values()[cineplexID - 1];

		// Display cinemas in cineplex
		ArrayList<Cinema> cinemaList = CineplexController.getCinemaList(cineplex);
		if (cinemaList == null) {
			System.out.println("No cinema at this cineplex.");
		} else {
			for (Cinema cinema : cinemaList) {
				System.out.print(cinema + "(" + (cinema.isPlatinum() ? "Platinum, " : "")
						+ (cinema.is3D() ? "3D" : "Digital") + ") ");
			}
			System.out.println();
		}
	}

	/**
	 * This method allows staff to add a new cinema.
	 * 
	 * @param scan
	 */
	public void addCinema(Scanner scan) {
		displayCineplexList(scan);

		System.out.print("Enter index of cineplex to add cinema: ");
		int index = InputController.readUserChoice(Cineplex.values().length, 1);
		Cineplex cineplex = Cineplex.values()[index-1];

		System.out.print("Enter cinema code of new cinema: ");
		String code = scan.nextLine();

		String answer;
		boolean isPlat;
		while (true) {
			System.out.print("Is this a platinum cinema? (Y/N) ");
			answer = scan.nextLine();
			if (!answer.equals("Y") && !answer.equals("N")) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			isPlat = answer.equals("Y");
			break;
		}
		boolean is3D;
		while (true) {
			System.out.print("Is this a 3D cinema? (Y/N) ");
			answer = scan.nextLine();
			if (!answer.equals("Y") && !answer.equals("N")) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			is3D = answer.equals("Y");
			break;
		}

		System.out.print("Enter base ticket price for this cinema: ");
		double basePrice = InputController.readDouble();

		try {
			CineplexController.addCinema(new Cinema(cineplex, isPlat, is3D, code, basePrice));
			System.out.println("Successfully added new cinema.");
		} catch (Exception e) {
			System.out.println("Failed to add new cinema.\n");
		}
	}
}
