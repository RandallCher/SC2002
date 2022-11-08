package View.staff;

import View.View;
import View.MovieGoer.MovieListingsView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.IOController;
import Model.Cinema;
import Model.Movie;
import Model.Showtime;
import Model.Parameters.Cineplex;

/**
 * This class represents the View for editing the showtimes for a movie
 */
public class EditShowtimeView extends View {

	public void start() {
		// get movie choice
		Scanner scan = new Scanner(System.in);
		Movie movie = getMovieChoice(scan);
		while (movie == null) {
			System.out.print("No movie by that title exists. Try again? (Y to confirm) ");
			if (scan.nextLine().toUpperCase().equals("Y")) {
				System.out.print("Need a list of all movie listings? (Y/N) ");
				String needList = scan.nextLine().toUpperCase();
				if (needList.equals("Y")) {
					EditMovieListingView listingsView = new EditMovieListingView();
					listingsView.displayMovieListings(false);
					System.out.println();
				} else if (!needList.equals("N"))
					System.out.println("Invalid input. Try again.");
				movie = getMovieChoice(scan);
			} else
				this.end();
		}

		// Showtime menu
		while (true) {
			System.out.println("Editing showtimes for '" + movie.getTitle() + "'");
			System.out.print("------------- EDIT SHOWTIME MENU -------------\n"
					+ "1: View all showtimes\n"
					+ "2: View/modify cinema list\n"
					+ "3: Add a showtime\n"
					+ "4: Remove a showtime\n"
					+ "5: Modify a showtime\n"
					+ "6: Change movie\n"
					+ "7: Exit\n\n"
					+ "Please enter your choice: ");
			int choice;
			choice = IOController.readUserChoice(7, 1);
			switch (choice) {
				case 1:
					displayShowtimes(movie);
					break;
				case 2:
					System.out.println();
					navigateNextView(this, new CinemaListView());
					break;
				case 3:
					addShowtime(movie);
					break;
				case 4:
					removeShowtime(movie);
					break;
				case 5:
					modifyShowtime(movie);
					break;
				case 6:
					getMovieChoice(scan);
					break;
				case 7:
					this.end();
					break;
			}
		}
	}

	/**
	 * This method reads user input for a movie choice
	 * 
	 * @param scan
	 * @return movie chosen
	 */
	private Movie getMovieChoice(Scanner scan) {
		System.out.print("Enter movie title to view/modify showtimes: ");
		String movieTitle = scan.nextLine();
		ArrayList<Movie> movieList = CineplexController.getMovieListing();

		Movie movie = null;
		for (Movie aMovie : movieList) {
			if (movieTitle.toUpperCase().equals(aMovie.getTitle().toUpperCase())) {
				movie = aMovie;
				return movie;
			}
		}
		return movie;
	}

	/**
	 * This method displays all showtimes for a given movie in a given cinema.
	 * 
	 * @param movie
	 */
	private void displayShowtimes(Movie movie) {
		System.out.println("**** Showtimes for " + movie.getTitle().trim() + " ****");
		ArrayList<Showtime> showtimeList = CineplexController.getMovieShowtime(movie);
		if (showtimeList.isEmpty()) {
			System.out.println("There are no existing showtimes.");
			return;
		}
		for (int i = 1; i <= showtimeList.size(); i++) {
			System.out.println(i + ": " + showtimeList.get(i).getDetails());
		}
		return;
	}

	/**
	 * This method adds a new showtime.
	 * 
	 * @param movie
	 */
	private void addShowtime(Movie movie) {
		Scanner scan = new Scanner(System.in);
		System.out.println("**** Adding Showtime ****");
		System.out.print("Enter cinema code: ");
		String code = scan.nextLine();
		Cinema cinema = CineplexController.getCinemaByCode(code);
		if (cinema == null) {
			System.out.println("Invalid cinema code.");
			return;
		}
		Date time = IOController.readDateMMddkkmm("Enter time in format 'MM-dd HH:mm': ");

		Showtime newShowtime = new Showtime();
		newShowtime.setMovie(movie);
		newShowtime.setTime(time);
		newShowtime.setCinema(cinema);
		try {
			CineplexController.addShowtime(newShowtime);
			System.out.println("Successfully added showtime.");
		} catch (Exception e) {
			System.out.println("Failed to add showtime.");
		}
	}

	/**
	 * This method removes a showtime.
	 * 
	 * @param movie
	 */
	private void removeShowtime(Movie movie) {
		// TODO - implement removeShowtime
		Scanner scan = new Scanner(System.in);
		ArrayList<Showtime> showtimeList = CineplexController.getMovieShowtime(movie);
		displayShowtimes(movie);
		System.out.print("Enter index of showtime to delete: ");
		int index;
		try {
			index = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input.");
			return;
		}
		Showtime showtime = showtimeList.get(index - 1);
		System.out.print("Are you sure you want to delete showtime: " + showtime.getDetails() + "? (Y to confirm) ");
		if (scan.next().equals("Y")) {
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
	 * @param movie
	 */
	private void modifyShowtime(Movie movie) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Showtime> showtimeList = CineplexController.getMovieShowtime(movie);

		System.out.println("**** Modifying showtime ****");
		displayShowtimes(movie);
		System.out.print("Enter index of showtime to modify: ");
		int index;
		try {
			index = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input.");
			return;
		}
		Showtime showtime = showtimeList.get(index - 1);
		boolean isDone = false;
		while (!isDone) {
			System.out.print("What would you like to modify?\n"
					+ "1: Time of showtime\n"
					+ "2: Cinema\n"
					+ "3: Quit\n\n"
					+ "Enter your choice: ");
			int choice;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input.");
				return;
			}
			switch (choice) {
				case 1:
					Date time = IOController.readDateMMddkkmm("Enter time in format 'MM-dd HH:mm': ");
					showtime.setTime(time);
					System.out.println("Change successful.");
					break;
				case 2:
					System.out.print("Enter cinema code: ");
					String code = scan.nextLine();
					Cinema cinema = CineplexController.getCinemaByCode(code);
					if (cinema == null) {
						System.out.println("Invalid cinema code.");
					}
					showtime.setCinema(cinema);
					System.out.println("Change successful.");
					break;
				case 3:
					isDone = true;
					try {
						CineplexController.updateMovieShowtime();
						System.out.println("Successfully modified showtime.");
					} catch (Exception e) {
						System.out.println("Failed to modify showtime.");
					}
					break;
				default:
					System.out.println("Invalid input. Try again.");
			}
		}
	}

}