package View.staff;

import View.View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Model.Movie;
import Model.Showtime;

public class CinemaListView extends View {

	private boolean help;

	public void start() {
		// TODO
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("------------- CINEMA LIST MENU -------------\n"
					+ "1: View all cinemas\n"
					+ "2: View cinema list\n"
					+ "3: Exit\n\n"
					+ "Please enter your choice: ");
			int choice = 0;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.\n");
			}
			switch (choice) {
				case 1:
					System.out.println("case1");
				case 2:
					break;
				case 3:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.\n");

			}
		}
	}
}