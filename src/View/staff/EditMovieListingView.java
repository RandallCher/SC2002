package View.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.Content;

import Controller.*;
import View.View;
import Model.*;
import Model.Parameters.AgeRestriction;
import Model.Parameters.Cineplex;
import Model.Parameters.MovieStatus;

/**
 * This class represents the View for staff to edit movie listings
 */
public class EditMovieListingView extends View {

	public void start() {
		while (true) {
			System.out.print("\n------------- EDIT MOVIE LISTING MENU -------------\n"
					+ "1: List all current movies\n"
					+ "2: Add new movie\n"
					+ "3: Modify existing movie's details\n"
					+ "4: Exit\n\n"
					+ "Enter your choice: ");
			Scanner scan = new Scanner(System.in);
			int choice = 0;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.\n");
				continue;
			}
			switch (choice) {
				case 1:
					System.out.print("Display full movie details? (Y/N) ");
					boolean displayAll = scan.next().toUpperCase().equals("Y");
					this.displayMovieListings(displayAll);
					break;
				case 2:
					this.addMovieListing();
					break;
				case 3:
					this.updateMovieDetail();
					break;
				case 4:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.\n");
			}
		}
	}

	/**
	 * This method displays all movie titles and statuses
	 */
	protected void displayMovieListings(boolean displayAll) {
		ArrayList<Movie> movieListing = CineplexController.getMovieListing();
		System.out.println("\n**** All Movie Listings ****");
		if (displayAll) {
			for (Movie movie : movieListing) {
				displayMovieDetails(movie);
			}
		} else {
			for (Movie movie : movieListing) {
				System.out.println(movie.getTitle() + InputController.generateSpaces(40 - movie.getTitle().length())
						+ movie.getMovieStatus().toString());
			}
		}
	}

	/**
	 * This method displays full movie details for a movie
	 */
	protected void displayMovieDetails(Movie movie) {
		System.out.print("Title: " + movie.getTitle() + "\n"
				+ "Movie director: " + movie.getDirector() + "\n"
				+ "Movie sypnopsis: " + movie.getSypnosis() + "\n"
				+ "Movie cast: ");
		ArrayList<String> cast = movie.getCast();
		int i;
		for (i = 0; i < cast.size() - 1; i++) {
			System.out.print(cast.get(i) + ", ");
		}
		System.out.println(cast.get(i));
		System.out.print("Movie status: " + movie.getMovieStatus().toString() + "\n"
				+ "Movie age restriction: " + movie.getAgeRestriction().toString() + "\n"
				+ "Movie sales: " + movie.getSales() + "\n\n");
	}

	/**
	 * This method creates a new movie listing
	 */
	private void addMovieListing() {
		String title, director, synopsis, castString;
		String[] castArray;
		ArrayList<String> cast = new ArrayList<>();
		MovieStatus movieStatus;
		AgeRestriction ageRestriction;
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter new movie title: ");
		title = scan.nextLine();
		System.out.print("Enter movie director: ");
		director = scan.nextLine();
		System.out.print("Enter movie synopsis: ");
		synopsis = scan.nextLine();
		System.out.print("Enter movie cast (separate names with a comma): ");
		castString = scan.nextLine();
		castArray = castString.split(",");
		for (int i = 0; i < castArray.length; i++) {
			// System.out.println(castArray[i].trim());
			cast.add(castArray[i].trim());
		}
		while (true) {
			System.out.print("Enter movie status ('coming soon', 'now showing' or 'end of showing'): ");
			movieStatus = InputController.readMovieStatus(scan.nextLine());
			if (movieStatus == null) {
				System.out.println("Invalid input. Try again.");
				continue;
			} else
				break;
		}
		while (true) {
			System.out.print("Enter age restriction (G, PG, PG13, NC16, M18, R21): ");
			ageRestriction = InputController.readAgeRestriction(scan.nextLine());
			if (ageRestriction == null) {
				System.out.println("Invalid input. Try again.");
				continue;
			} else
				break;
		}
		Movie movie = new Movie();
		movie.setAgeRestrictions(ageRestriction);
		movie.setCast(cast);
		movie.setTitle(title);
		movie.setDirector(director);
		movie.setMovieStatus(movieStatus);
		movie.setSypnosis(synopsis);

		try {
			CineplexController.addMovieListing(movie);
			System.out.println("Successfully added new movie.");
			displayMovieDetails(movie);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to add new movie.");
		}
	}

	/**
	 * This method updates a movie listing's details
	 */
	private void updateMovieDetail() {
		System.out.print("Enter title of movie to be updated: ");
		Scanner scan = new Scanner(System.in);
		String oriTitle = scan.nextLine();
		ArrayList<Movie> movieList = CineplexController.getMovieListing();

		Movie movie = null;
		for (Movie aMovie : movieList) {
			if (oriTitle.toUpperCase().equals(aMovie.getTitle().toUpperCase())) {
				movie = aMovie;
			}
		}
		if (movie == null) {
			System.out.println("No movie by that title exists.\n");
			return;
		}
		boolean isDone = false;
		while (!isDone) {
			System.out.print("**** Updating Movie Details ****\n"
					+ "1: Update movie title\n"
					+ "2: Update movie director\n"
					+ "3: Update movie sypnopsis\n"
					+ "4: Update movie cast\n"
					+ "5: Update movie status\n"
					+ "6: Update age restriction\n"
					+ "7: Remove movie listing\n"
					+ "8: Exit\n\n"
					+ "Enter your choice: ");
			int choice = scan.nextInt();
			switch (choice) {
				case 1:
					System.out.print("Enter updated movie title: ");
					String newTitle = scan.nextLine();
					movie.setTitle(newTitle);
					System.out.println("Change successful.");
					break;
				case 2:
					System.out.print("Enter updated movie director: ");
					String director = scan.nextLine();
					movie.setDirector(director);
					System.out.println("Change successful.");
					break;
				case 3:
					System.out.print("Enter updated movie sypnopsis: ");
					String sypnopsis = scan.nextLine();
					movie.setSypnosis(sypnopsis);
					System.out.println("Change successful.");
					break;
				case 4:
					System.out.print("Enter updated cast list (separate names with a comma): ");
					ArrayList<String> cast = new ArrayList<>();
					movie.setCast(cast);
					Scanner commaScan = new Scanner(System.in);
					commaScan.useDelimiter(",");
					while (scan.hasNext()) {
						String castMember = commaScan.next().trim();
						cast.add(castMember);
					}
					movie.setCast(cast);
					System.out.println("Change successful.");
					break;
				case 5:
					System.out.print("Enter movie status ('coming soon', 'now showing' or 'end of showing'): ");
					MovieStatus movieStatus = InputController.readMovieStatus(scan.nextLine());
					if (movieStatus == null) {
						System.out.println("Invalid input. Try again.");
						break;
					} else if (movieStatus.equals(MovieStatus.END_OF_SHOWING)) {
						System.out.println("Setting to 'end of showing' will remove the listing.");
						removeListing(movie);
					} else {
						movie.setMovieStatus(movieStatus);
					}
					System.out.println("Change successful.");
					break;
				case 6:
					System.out.print("Enter age restriction (G, PG, PG13, NC16, M18, R21): ");
					AgeRestriction ageRestriction = InputController.readAgeRestriction(scan.nextLine().toUpperCase());
					movie.setAgeRestrictions(ageRestriction);
					System.out.println("Change successful.");
					break;
				case 7:
					removeListing(movie);
					break;
				case 8:
					try {
						CineplexController.updateMovieListing();
						System.out.println("Successfully updated movie listing.");
						displayMovieDetails(movie);
					} catch (Exception e) {
						System.out.println("Failed to update movie listing.");
					} finally {
						isDone = true;
					}
				default:
					System.out.println("Invalid input. Try again.");
			}
		}
		return;
	}

	/**
	 * This method removes a movie listing and all cinema show times
	 * 
	 * @param movie
	 */
	private void removeListing(Movie movie) {
		System.out.println("Confirm the removal of " + movie.getTitle() + "? (Y to confirm)");
		Scanner scan = new Scanner(System.in);
		if (!scan.next().toUpperCase().equals("Y")) {
			System.out.println("Listing is not removed.");
			return;
		}
		try {
			CineplexController.removeMovieListing(movie);
			CineplexController.removeAllShowtime(movie);
			System.out.println("Successfully removed movie listing and all show times.");
		} catch (Exception ex) {
			System.out.println("Failed to remove listing.");
		}
	}

}