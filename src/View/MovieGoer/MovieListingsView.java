package View.MovieGoer;

import Controller.CineplexController;
import Model.Parameters;
import Model.Parameters.MovieStatus;
import Model.Movie;
import View.*;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

import static Controller.CineplexController.*;
import static Controller.CineplexController.getMovieRating;
import static Controller.CineplexController.getTop5MovieListing;

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
		System.out.println("Enter Movie Title: ");
		String movieTitle = sc.nextLine();
		String lowerCaseTitle = movieTitle.toLowerCase();
		ArrayList<Movie> foundMovies = getMovieByTitle(lowerCaseTitle);

		if (foundMovies.size() == 0) {
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
		if(movieList == null){
			System.out.println("No movies. ");
			System.out.println("1. Go Back");
			sc.nextLine();
			start();
		}

		if (!topFive || CineplexController.getSystem().get("movieOrder")) {// rating

			for (int i = 0; i < movieList.size(); i++) {
					System.out.printf("%-1s. %-40s %-15s [%-2s]\n", i + 1 , movieList.get(i).getTitle(),
							movieList.get(i).getMovieStatus(), getMovieRating(movieList.get(i)));


			}
		} else {
			// sales
			for (int i = 0; i < movieList.size(); i++) {
				System.out.printf("%-1s %-40s %-15s [%-2s]\n", i + 1 - back, movieList.get(i).getTitle(),
						movieList.get(i).getMovieStatus(), movieList.get(i).getSales());

			}
		}
		System.out.println((movieList.size()+1-back)  +". Go Back");
		int input = sc.nextInt();
		//Option to go Back
		if( input > movieList.size()){
			navigateNextView(this, new MovieGoerView());
		}
		Movie movie = movieList.get(input -1);

		displayMovieDetails(movie);


	}

	/**
	 * This method is to display the details of a movie
	 * 
	 * @param movie the movie selected
	 */

	public void displayMovieDetails(Movie movie) {
		System.out.println("Movie details for: "+movie.getTitle());
		System.out.println("1. Display showtime");
		System.out.println("2. View or write reviews");
		System.out.println("3. Restart from main menu");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		switch (input) {
			case 1:
				if(movie.getMovieStatus()==MovieStatus.END_OF_SHOWING||movie.getMovieStatus()==MovieStatus.COMING_SOON){
					System.out.println("----Movie is not currently showing----");
					System.out.println("Restarting from MOVIE GOER MENU");
					break;
				}else{
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