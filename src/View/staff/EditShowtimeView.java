package View.staff;

import View.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import Controller.CineplexController;
import Controller.InputController;
import Model.Cinema;
import Model.Movie;
import Model.Showtime;
import Model.Parameters.MovieStatus;

/**
 * This class represents the View for editing the showtimes for a movie
 */
public class EditShowtimeView extends View {

	public void start() {

		// get movie choice
		Movie movie = getMovieChoice();
		
		while (movie == null) {
			boolean reTry = InputController.confirmation("No movie by that title exists. Try again? (Y/N) ");

			if (reTry) {
				boolean showList = InputController.confirmation("Need a list of all movie listings? (Y/N) "); 
				if (showList) {
					EditMovieListingView listingsView = new EditMovieListingView();
					listingsView.displayMovieListings(false);
					System.out.println();
				} 
				movie = getMovieChoice();

			} else
				this.end();
		}

		if (movie.getMovieStatus() == MovieStatus.END_OF_SHOWING){
			System.out.println("This movie is not showing in cinemas anymore"); 
			this.end(); 
		}
		
		if (movie.getMovieStatus() == MovieStatus.COMING_SOON){
			System.out.println("This movie will be coming soon. It is currently not available yet."); 
			this.end(); 
		}

		// Showtime menu
		while (true) {
			System.out.println("\nEditing showtimes for '" + movie.getTitle() + "'");
			System.out.print("------------- EDIT SHOWTIME MENU -------------\n"
					+ "1: View all showtimes\n"
					+ "2: Add a showtime\n"
					+ "3: Remove a showtime\n"
					+ "4: Modify a showtime\n"
					+ "5: Change movie\n"
					+ "6: Back to previous page\n\n"
					+ "Please enter your choice: ");
			int choice;
			choice = InputController.readUserChoice(6, 1);
			switch (choice) {
				case 1:
					displayShowtimes(movie);
					break; 
				case 2:
					addShowtime(movie);
					break;
				case 3:
					removeShowtime(movie);
					break;
				case 4:
					modifyShowtime(movie);
					break;
				case 5:
					this.start();
					break;
				default:
					this.end();
			}
		}
	}

	/**
	 * This method reads user input for movie choice in order to update showtimes. 
	 * 
	 * @return movie that is chosen, or null if no movie with the entered title exists. 
	 */
	 private Movie getMovieChoice() {
		String movieTitle = InputController.readString("Enter movie title to view/modify showtimes: ");
		ArrayList<Movie> movieList =CineplexController.getMovieByTitle(movieTitle);

		if (movieList.isEmpty()) {
			return null;
		} 
		return movieList.get(0); //return the first movie with the input name
	}

	/**
	 * This method displays all showtimes for a given movie in a given cinema.
	 * 
	 * @param movie movie to display show time for
	 * @returns {@code ArrayList<Showtime>} array of showtimes
	 */
	private ArrayList<Showtime> displayShowtimes(Movie movie) {
		System.out.println("**** Showtimes for " + movie.getTitle().trim() + " ****");
		ArrayList<Showtime> showtimeList = CineplexController.getMovieShowtime(movie);
		if (showtimeList == null) {
			System.out.println("There are no existing showtimes.");
			return null; 
		}
		for (int i = 1; i <= showtimeList.size(); i++) {
			System.out.println(i + ": " + showtimeList.get(i-1).getDetails());
		}
		return showtimeList;
	}


	/**
	 * This method adds a new showtime.
	 * 
	 * @param movie the movie to add showtime for
	 */
	private void addShowtime(Movie movie) {
		System.out.println("**** Adding Showtime ****");
		String code = InputController.readString("Enter cinema code: ");
		Cinema cinema = CineplexController.getCinemaByCode(code);
		if (cinema == null) {
			System.out.println("Invalid cinema code.");
			return;
		}

		Date time = InputController.readDateMMddkkmm("Enter time in format 'MM-dd HH:mm': ");
		System.out.println(time); 
		Showtime newShowtime = new Showtime();
		newShowtime.setMovie(movie);
		newShowtime.setTime(time);
		newShowtime.setCinema(cinema);
		try {
			CineplexController.addShowtime(newShowtime, movie);
			System.out.println("Successfully added showtime.");
		} catch (IOException e) {
			System.out.println("Failed to add showtime.");
			e.printStackTrace();
		}
	}



	/**
	 * This method removes a showtime.
	 * 
	 * @param movie the movie to remove showtime for 
	 */
	private void removeShowtime(Movie movie) {
		System.out.println("**** Removing Showtime ****");

		ArrayList<Showtime> showtimeList = displayShowtimes(movie);;
		
		if (showtimeList == null) return; //do nothing and return to caller if showtimes for movie do not exist

		System.out.print("Enter index of a showtime to delete: ");
		int index = InputController.readUserChoice(showtimeList.size(),1);
		

		Showtime showtime = showtimeList.get(index - 1);
		boolean confirm = InputController.confirmation("Are you sure you want to delete showtime: " + showtime.getDetails() + "? (Y/N) "); 
		if (confirm) {
			try {
				CineplexController.removeShowtime(showtime);
				System.out.println("Successfully removed showtime.");
			} catch (Exception e) {
				System.out.println("Failed to remove showtime.");
			}
		} else
			System.out.println("Did not delete showtime.");
	}

	/**
	 * This method modifies an existing showtime.
	 * 
	 * @param movie the movie to modify the showtime for 
	 */
	private void modifyShowtime(Movie movie) {
		System.out.println("**** Modifying showtime ****"); 

		ArrayList<Showtime> showtimeList = displayShowtimes(movie);
		if (showtimeList == null) return; //do nothing and return to caller if showtimes for movie do not exist

		System.out.print("Enter index of showtime to modify: ");
		int index = InputController.readUserChoice(showtimeList.size(),1);
		
		Showtime showtime = showtimeList.get(index - 1);
		boolean isDone = false;
		while (!isDone) {
			System.out.print("What would you like to modify?\n"
					+ "1: Time of showtime\n"
					+ "2: Cinema of showtime\n"
					+ "3: Go back\n\n"
					+ "Enter your choice: ");
			int choice = InputController.readUserChoice(3,1);
			
			switch (choice) {
				case 1:
					Date time = InputController.readDateMMddkkmm("Enter time in format 'MM-dd HH:mm': ");
					showtime.setTime(time);
					System.out.println("Change successful.");
					break;
				case 2:
					String code = InputController.readString("Enter cinema code: ");
					Cinema cinema = CineplexController.getCinemaByCode(code);
					if (cinema == null) {
						System.out.println("Invalid cinema code.");
					}
					else {
					showtime.setCinema(cinema);
					System.out.println("Change successful.");
					}
					break;
				default:
					isDone = true;
					try {
						CineplexController.updateMovieShowtime();
						System.out.println("Successfully modified showtime.");
					} catch (Exception e) {
						System.out.println("Failed to modify showtime.");
					}
	
			}
		}
	}

}