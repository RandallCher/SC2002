package View.staff;

import View.View;

import java.util.ArrayList;

import Controller.CineplexController;
import Controller.InputController;
import Model.Cinema;
import Model.Parameters.Cineplex;

/**
 * This class shows the information about the cineplex and its cinemas.
 */
public class EditCinemaListView extends View {


	public void start() {
		while (true) {
			System.out.print("------------- CINEMA LIST MENU -------------\n"
					+ "1: View all cineplexes\n"
					+ "2: View cinemas in a cineplex\n"
					+ "3: Add a cinema\n"
					+ "4: Go back\n\n"
					+ "Please enter your choice: ");
			int choice = InputController.readUserChoice(4, 1);
			switch (choice) {
				case 1:
					displayCineplexList();
					break;
				case 2:
					displayCinemasByCineplex();
					break;
				case 3:
					addCinema();
					break;
				case 4:
					this.end();
					break;
			}
		}
	}

	

	/**
	 * This method displays all cineplexes
	 */
	public void displayCineplexList() {
		System.out.println();
		int index = 1;
		System.out.println("**** Cineplex List ****");
		for (Cineplex c : Cineplex.values()) {
			System.out.println(index++ + ": " + c);
		}
		System.out.println();
	}

	/**
	 * This method displays all cinemas in a cineplex.
	 * 
	 */
	public void displayCinemasByCineplex() {
		displayCineplexList();

		System.out.print("Enter cineplex id to view cinemas: ");
		int cineplexID = InputController.readUserChoice(Cineplex.values().length, 1);
		Cineplex cineplex = Cineplex.values()[cineplexID - 1];

		ArrayList<Cinema> cinemaList = CineplexController.getCinemaList(cineplex);
		if (cinemaList == null) {
			System.out.println("No cinemas at this cineplex yet.");
		} else {
			for (Cinema cinema : cinemaList) {
				System.out.print(cinema + "(" + (cinema.isPlatinum() ? "Platinum, " : "")
						+ (cinema.is3D() ? "3D" : "Digital") + ") ");
			}
			System.out.println();
		}
	}

	/**
	 * This method adds a new cinema to a cineplex.
	 */
	public void addCinema() {
		displayCineplexList();

		System.out.print("Enter index of cineplex to add cinema: ");
		int index = InputController.readUserChoice(Cineplex.values().length, 1);
		Cineplex cineplex = Cineplex.values()[index-1];

		String code = InputController.readString("Enter cinema code of new cinema: ");
		boolean isPlat = InputController.confirmation("Is this a platinum cinema? (Y/N) ");
		boolean is3D = InputController.confirmation("Is this a 3D cinema? (Y/N) ");
		double basePrice = InputController.readDouble("Enter base ticket price for this cinema: ");

		try {
			CineplexController.addCinema(new Cinema(cineplex, isPlat, is3D, code, basePrice));
			System.out.println("Successfully added new cinema.");
		} catch (Exception e) {
			System.out.println("Failed to add new cinema.\n");
		}
	}
}
