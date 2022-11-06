package View.staff;

import View.View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Model.Movie;
import Model.Showtime;

// might remove file (add cinema not necessary)
public class EditCinemaListView extends View {

	private boolean help;

	public void start() {
		System.out.println("Which movie's showtimes do you want to modify?");
		Scanner scan = new Scanner(System.in);
		String movieTitle = scan.nextLine();
		ArrayList<Movie> movieList = CineplexController.getMovieListing();

		Movie movie = null;
		for (Movie aMovie : movieList) {
			if (movieTitle.toUpperCase().equals(aMovie.getTitle().toUpperCase())) {
				movie = aMovie;
			}
		}
		if (movie == null) {
			System.out.println("No movie by that title exists.");
			this.end();
		}

		while (true) {
			System.out.print("------------- EDIT SHOWTIME MENU -------------\n"
					+ "1: View all showtimes\n"
					+ "2: Add a showtime\n"
					+ "3: Remove a showtime\n"
					+ "4: Modify a showtime\n"
					+ "5: Exit\n\n"
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
					addShowtime(movie);
					break;
				case 3:
					removeShowtime(movie);
					break;
				case 4:
					modifyShowtime(movie);
					break;
				case 5:
					this.end();
					break;
				default:
					System.out.println("Invalid input. Try again.\n");
			}
		}
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
		// TODO - implement CinemaList.addCinema

		// private Date time;
		// private Movie movie;
		// private Cinema cinema;
		// private Seat[][] seats;
		System.out.print("Enter cinema name: ");

	}

	private void removeShowtime(Movie movie) {
		// TODO - implement removeShowtime
	}

	private void modifyShowtime(Movie movie) {
		// TODO - implement modifyShowtime

	}

}