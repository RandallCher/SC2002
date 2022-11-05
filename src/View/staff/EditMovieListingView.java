package View.staff;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.*;
import View.View;
import Model.*;
import Model.Parameters.AgeRestriction;
import Model.Parameters.Cineplex;
import Model.Parameters.MovieStatus;

public class EditMovieListingView extends View {

	public void start() {
		while (true) {
			System.out.print("------------- EDIT MOVIE LISTING MENU -------------\n"
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
					this.displayMovieListings();
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
	protected void displayMovieListings() {
		ArrayList<Movie> movieListing = CineplexController.getMovieListing();
		for (Movie movie : movieListing) {
			// IOController.generateSpaces(40 - movie.getTitle().length()) DOESNT WORKK
			System.out.println(movie.getTitle() + "\t\t" + movie.getMovieStatus().toString());
		}
	}

	/**
	 * This method displays full movie details for all movies
	 */
	protected void displayMovieDetails() {
		// TODO
	}

	private void addMovieListing() {
		String title, director, sypnopsis, castMember;
		ArrayList<String> cast = new ArrayList<>();
		MovieStatus movieStatus;
		AgeRestriction ageRestriction;
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter new movie title: ");
		title = scan.nextLine();
		System.out.print("Enter movie director: ");
		director = scan.nextLine();
		System.out.print("Enter movie sypnopsis: ");
		sypnopsis = scan.nextLine();
		System.out.print("Enter movie cast (separate names with a comma): ");
		Scanner commaScan = new Scanner(System.in);
		commaScan.useDelimiter(",");
		while (scan.hasNext()) {
			castMember = commaScan.next().trim();
			cast.add(castMember);
		}
		System.out.print("Enter movie status ('coming soon', 'now showing' or 'end of showing'): ");
		movieStatus = IOController.readMovieStatus(scan.nextLine().toUpperCase());
		System.out.print("Enter age restriction (G, PG, PG13, NC16, M18, R21): ");
		ageRestriction = IOController.readAgeRestriction(scan.nextLine().toUpperCase());

		Movie movie = new Movie();
		movie.setAgeRestrictions(ageRestriction);
		movie.setCast(cast);
		movie.setTitle(title);
		movie.setDirector(director);
		movie.setMovieStatus(movieStatus);
		movie.setSypnosis(sypnopsis);
		try {
			CineplexController.addMovieListing(movie);
			System.out.println("Successfully added new movie: " + movie.getTitle() + ".");
		} catch (Exception e) {
			System.out.println("Failed to add new movie.");
		}
	}

	/**
	 * This method updates a movie listing
	 */
	private void updateMovieDetail() {
		System.out.print("Enter title of movie to be updated: ");
		Scanner scan = new Scanner(System.in);
		String oriTitle = scan.nextLine();
		ArrayList<Movie> movieList = CineplexController.getMovieListing();

		Movie movie;
		System.out.println("No movie by that title exists.");
		boolean isDone = false;
		while (!isDone) {
			System.out.print("**** Updating Movie Details ****\n"
					+ "1: Update movie title\n"
					+ "2: Update movie director\n"
					+ "3: Update movie sypnopsis\n"
					+ "4: Update movie cast\n"
					+ "5: Update movie status\n"
					+ "6: Update age restriction\n"
					+ "7: Exit\n\n"
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
					System.out.println("Change successful.");
					break;
				case 5:
					System.out.print("Enter movie status ('coming soon', 'now showing' or 'end of showing'): ");
					MovieStatus movieStatus = IOController.readMovieStatus(scan.nextLine().toUpperCase());
					movie.setMovieStatus(movieStatus);
					System.out.println("Change successful.");
					break;
				case 6:
					System.out.print("Enter age restriction (G, PG, PG13, NC16, M18, R21): ");
					AgeRestriction ageRestriction = IOController.readAgeRestriction(scan.nextLine().toUpperCase());
					movie.setAgeRestrictions(ageRestriction);
					System.out.println("Change successful.");
					break;
				case 7:
					try {
						CineplexController.updateMovieListing();
						System.out.println("Successfully updated movie listing.");
					} catch (Exception e) {
						System.out.println("Failed to update movie listing.");
					} finally {
						isDone = true;
						break;
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
	private void removeMovieListing(Movie movie) {
		// TODO - implement MovieListing.removeListingMenu
		System.out.println("Confirm the removal of " + movie.getTitle() + "? (Y to confirm)");
		Scanner scan = new Scanner(System.in);
		if (!scan.next().toUpperCase().equals("Y")) {
			System.out.println("Listing is not removed.");
			return;
		}
		try {
			// TODO cant find methods
			CineplexController.removeListing(movie);
			CineplexController.removeAllShowtime(movie);
			System.out.println("Successfully removed movie listing and all show times.");
		} catch (Exception ex) {
			System.out.println("Failed to remove listing.");
		}
	}

}