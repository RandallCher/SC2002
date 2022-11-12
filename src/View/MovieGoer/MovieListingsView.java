package View.MovieGoer;

import Controller.CineplexController;
import Model.Parameters.MovieStatus;
import Model.Movie;
import View.*;

import java.util.*;
import java.util.ArrayList;

import static Controller.CineplexController.*;
import static Controller.InputController.*;

/**
 * Create class to help with displaying movie listing
 */
public class MovieListingsView extends View {

	/**
	 * Method searches movie based on user input and displays result
	 */
	protected void start(Movie movie) {
		displayMovieDetails(movie);
	}

	public void searchMovie() {
		Scanner sc = new Scanner(System.in);
		String movieTitle = readString("Enter Movie Title: ");
		String lowerCaseTitle = movieTitle.toLowerCase();
		ArrayList<Movie> foundMovies = getMovieByTitle(lowerCaseTitle);

		if (foundMovies.isEmpty()) {
			System.out.println("No such movie found!");
			return;
		} else {
			System.out.println(foundMovies.size() + " results have been found.");
			for (int i = 0; i < foundMovies.size(); i++) {
				System.out.println(i + 1 + ". " + foundMovies.get(i).getTitle());
			}
			int choice = sc.nextInt();
			displayMovieDetails(foundMovies.get(choice - 1));
		}

	}

	/**
	 * //Method displays all movies if false and displays top 5 movies if true
	 *
	 * @param topFive shows top5 movies if true else shows list of all movies
	 */

	public void displayMovieListings(boolean topFive) {
		ArrayList<Movie> movieList;
		int back = 0;
		Scanner sc = new Scanner(System.in);
		if (topFive == false) {
			movieList = getMovieListing();

		} else {
			System.out.println("Getting top 5 movies");
			movieList = getTop5MovieListing();
		}
		// If no movies go back
		if (movieList == null) {
			System.out.println("No movies. ");
			System.out.println("1. Go Back");
			sc.nextLine();
			start();
		}

		ArrayList<Movie> showingMoviesList = new ArrayList<>();
		for (int j = 0; j < movieList.size(); j++) {
			if (movieList.get(j).getMovieStatus() == MovieStatus.END_OF_SHOWING) {
				continue;
			} else
				showingMoviesList.add(movieList.get(j));
		}

		if (!topFive || CineplexController.getSystem().get("movieOrder")) {
			// rating
			for (int i = 0; i < showingMoviesList.size(); i++) {
				System.out.printf("%-1s. %-40s %-15s [%-2s]\n", i + 1, showingMoviesList.get(i).getTitle(),
						showingMoviesList.get(i).getMovieStatus(), getMovieRating(showingMoviesList.get(i)));

			}
		} else {
			// sales
			for (int i = 0; i < showingMoviesList.size(); i++) {
				System.out.printf("%-1s %-40s %-15s [%-2s]\n", i + 1 - back, showingMoviesList.get(i).getTitle(),
						showingMoviesList.get(i).getMovieStatus(), showingMoviesList.get(i).getSales());

			}
		}
		System.out.println((showingMoviesList.size() + 1 - back) + ". Go Back");
		System.out.print("Enter index of movie to view details: ");
		int input = readUserChoice(showingMoviesList.size() + 1, 1);
		// Option to go Back
		if (input > showingMoviesList.size()) {
			return;
		}
		Movie movie = showingMoviesList.get(input - 1);

		displayMovieDetails(movie);

	}

	/**
	 * This method is to display the details of a movie
	 *
	 * @param movie the movie selected
	 */

	public void displayMovieDetails(Movie movie) {
		System.out.println("Movie details for: " + movie.getTitle());
		System.out.println("1. Display showtime");
		System.out.println("2. View or write reviews");
		System.out.println("3. Restart from main menu");

		int input = readUserChoice(3, 1);
		switch (input) {
			case 1:
				if (movie.getMovieStatus() == MovieStatus.END_OF_SHOWING
						|| movie.getMovieStatus() == MovieStatus.COMING_SOON) {
					System.out.println("----Movie is not currently showing----");
					System.out.println("Restarting from MOVIE GOER MENU");
					break;
				} else {
					navigateNextView(this, new ShowtimeView(movie));
					break;
				}
			case 2:
				navigateNextView(this, new ReviewView(movie));
				break;
			case 3:
				destroy();
				break;
		}

	}

	/**
	 * Gets last view before current view
	 */
	protected void destroy() {
		getPrevView();
	}

}
