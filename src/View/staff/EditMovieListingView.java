package View.staff;

import java.util.Scanner;
import View.View;
import Model.*;

public class EditMovieListingView extends View {

	public void start() {
		while (true) {
			System.out.print("------------- MOVIE GOER MENU -------------\n"
					+ "1: Add new movie\n"
					+ "2: Modify existing movie's details\n"
					+ "3: Exit\n\n"
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
					this.addMovieListing();
					break;
				case 2:
					this.updateMovieDetail();
					break;
				case 3:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.");
			}
		}
	}

	private void addMovieListing() {
		// TODO - implement MovieListing.addMovieListing
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	private void displayMovieDetailMenu(Movie movie) {
		// TODO - implement MovieListing.displayMovieDetailMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	private void updateMovieDetail() {
		// TODO - implement MovieListing.updateMovieDetailMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	private void removeListingMenu(Movie movie) {
		// TODO - implement MovieListing.removeListingMenu
		throw new UnsupportedOperationException();
	}

}