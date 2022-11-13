package View.staff;

import java.io.IOException;
import java.util.ArrayList;


import Controller.*;
import View.View;
import Model.*;
import Model.Parameters.AgeRestriction;
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
					+ "4: Back to previous page\n\n"
					+ "Enter your choice: ");
			int choice = InputController.readUserChoice(4, 1);
			
			switch (choice) {
				case 1:
					boolean displayAll =InputController.confirmation("Display full movie details? (Y/N) ");
					this.displayMovieListings(displayAll);
					break;
				case 2:
					this.addMovieListing();
					break;
				case 3:
					this.updateMovieDetail();
					break;
				default: 
					this.end();
			}
		}
	}

	/**
	 * This method displays all movie titles and statuses
	 * @param displayAll boolean value that indicates whether to display all details of a movie
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
	 * @param movie the movie to display the details 
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
	 * This method creates a new movie listing stores it inside the file 
	 */
	private void addMovieListing() {
		String title, director, synopsis, castString, movieStatusString, ageRestrictionString;
		String[] castArray;
		ArrayList<String> cast = new ArrayList<>();
		MovieStatus movieStatus;
		AgeRestriction ageRestriction;

		title = InputController.readString("Enter new movie title: ");
		director = InputController.readString("Enter movie director: ");
		synopsis = InputController.readString("Enter movie synopsis: ");
		castString = InputController.readString("Enter movie cast (separate names with a comma): ");
		castArray = castString.split(",");
		for (int i = 0; i < castArray.length; i++) {
			cast.add(castArray[i].strip());
		}
		while (true) {
			movieStatusString = InputController.readString("Enter movie status ('coming soon', 'now showing' or 'end of showing'): "); 
			movieStatus = InputController.readMovieStatus(movieStatusString);
			if (movieStatus == null) {
				System.out.println("Invalid input. Try again.");
			} else break;
		}

		while (true) {
			ageRestrictionString = InputController.readString("Enter age restriction (G, PG, PG13, NC16, M18, R21): "); 
			ageRestriction = InputController.readAgeRestriction(ageRestrictionString);
			if (ageRestriction == null) {
				System.out.println("Invalid input. Try again.");
			} else break;
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
			System.out.println("Failed to add new movie.");
			System.out.println(e.getMessage());
		}
	}


	/**
	 * This method updates a movie listing's details
	 */
	private void updateMovieDetail() {
		String oriTitle = InputController.readString("Enter title of movie to be updated: ");
		ArrayList<Movie> movieList = CineplexController.getMovieByTitle(oriTitle);

		if (movieList.isEmpty()){
			System.out.println("No movie by that title exists.\n");
			return;
		}
		
		Movie movie = movieList.get(0); 
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
					+ "8: Go back\n\n"
					+ "Enter your choice: ");
			int choice = InputController.readUserChoice(8, 1);
			switch (choice) {
				case 1:
					String newTitle = InputController.readString("Enter updated movie title: "); 
					movie.setTitle(newTitle);
					System.out.println("Change successful.");
					break;
				case 2:
					String director = InputController.readString("Enter updated movie director: ");
					movie.setDirector(director);
					System.out.println("Change successful.");
					break;
				case 3:
					String sypnopsis = InputController.readString("Enter updated movie sypnopsis: ");
					movie.setSypnosis(sypnopsis);
					System.out.println("Change successful.");
					break;
				case 4:
					ArrayList<String> cast = new ArrayList<>();
					String castString = InputController.readString("Enter movie cast (separate names with a comma): ");
					String[] castArray = castString.split(",");
					for (int i = 0; i < castArray.length; i++) {
						cast.add(castArray[i].strip());
					}
					movie.setCast(cast);
					System.out.println("Change successful.");
					break;

				case 5:
					MovieStatus movieStatus; 
					while (true) {
						String movieStatusString = InputController.readString("Enter movie status ('coming soon', 'now showing' or 'end of showing'): "); 
						movieStatus = InputController.readMovieStatus(movieStatusString);
						if (movieStatus == null) {
							System.out.println("Invalid input. Try again.");
						} else break;
					}
					movie.setMovieStatus(movieStatus);
					System.out.println("Change successful.");
					break;
				case 6:
					AgeRestriction ageRestriction; 
					while (true) {
						String ageRestrictionString = InputController.readString("Enter age restriction (G, PG, PG13, NC16, M18, R21): "); 
						ageRestriction = InputController.readAgeRestriction(ageRestrictionString);
						if (ageRestriction == null) {
							System.out.println("Invalid input. Try again.");
						} else break;
					}
					movie.setAgeRestrictions(ageRestriction);
					System.out.println("Change successful.");
					break;
			
				case 7:
					removeListing(movie);
					break;
				default:
					try {
						CineplexController.updateMovieListing();
						System.out.println("Successfully updated movie listing.");
						displayMovieDetails(movie);
					} catch (Exception e) {
						System.out.println("Failed to update movie listing.");
					} finally {
						isDone = true;
					}
			}
		}
		return;
	}

	/**
	 * This method removes a movie listing and all cinema show times
	 * 
	 * @param movie movie to be removed
	 */
	private void removeListing(Movie movie) {
		boolean confirmation = InputController.confirmation("Confirm the removal of " + movie.getTitle() + "? (Y/N)");
		if (!confirmation) {
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