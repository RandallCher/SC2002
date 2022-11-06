package View.staff;

import View.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.IOController;
import Model.Cinema;
import Model.Movie;
import Model.Showtime;
import Model.Parameters.Cineplex;

// might remove file (add cinema not necessary)
public class EditShowtimeView extends View {

	// TODO - remove. added for testing
	public static void main(String[] args) {
		EditShowtimeView view = new EditShowtimeView();
		view.start();
	}

	public void start() {
		Scanner scan = new Scanner(System.in);
		// TODO - edit. added for testing
		Movie movie = new Movie();
		movie.setTitle("TEST MOVIE");
		// Movie movie = getMovieChoice(scan);
		while (true) {
			System.out.println("Editing showtimes for '" + movie.getTitle() + "'");
			System.out.print("------------- EDIT SHOWTIME MENU -------------\n"
					+ "1: View all showtimes\n"
					+ "2: View cinema list\n"
					+ "3: Add a showtime\n"
					+ "4: Remove a showtime\n"
					+ "5: Modify a showtime\n"
					+ "6: Change movie\n"
					+ "7: Exit\n\n"
					+ "Please enter your choice: ");
			int choice = 0;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.\n");
				continue;
			}
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
				default:
					System.out.println("Invalid input. Try again.\n");
			}
		}
	}

	private Movie getMovieChoice(Scanner scan) {
		System.out.println("Which movie's showtimes do you want to view/modify?");
		String movieTitle = scan.nextLine();
		ArrayList<Movie> movieList = CineplexController.getMovieListing();

		Movie movie = null;
		for (Movie aMovie : movieList) {
			if (movieTitle.toUpperCase().equals(aMovie.getTitle().toUpperCase())) {
				movie = aMovie;
				return movie;
			}
		}
		if (movie == null) {
			System.out.println("No movie by that title exists.");
			this.end();
		}
		return movie;
	}

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