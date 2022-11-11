package View.MovieGoer;

import Controller.CineplexController.*;
import Model.Movie;
import Model.Review;
import View.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Controller.InputController.*;
import static Controller.CineplexController.addReview;
import static Controller.CineplexController.getReviewList;
import static Model.Parameters.MovieStatus.*;

/**
 * This class represents the review view.
 */
public class ReviewView extends View {

	private Movie movie;

	/**
	 *	Class is to help with reviews of the movie
	 * @param movie	the movie selected
	 */
	public ReviewView(Movie movie)
	{
		this.movie=movie;
	}
	/**
	 *	Displays the review menu
	 */
	public void start() {
		try {
			displayMenu();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	/**
	 *	Method is to show the review menu
	 */
	private void displayMenu() throws IOException {
		if (movie.getMovieStatus() == COMING_SOON) {
			System.out.println("----Movie is coming soon----");
			destroy();
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Write a review");
		System.out.println("2. View all reviews");
		System.out.println("3. Go back")
		;
		int input = readUserChoice(1,3);
		switch (input) {
			case 1:
				addNewReview();
				break;
			case 2:
				listReview();
				break;
			case 3:
				destroy();
				break;
		}
	}
	/**
	 *	The method is for users to add a review to the movie
	 */
	private void addNewReview() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Write Your Review:");
		String name = readString("Please enter your name:");
		System.out.println("Please enter your rating between 1 and 5:");
		int rating = readUserChoice(1,5);
		String content = readString("Please enter your review:");
		Review review = new Review(this.movie, name, rating, content);
		addReview(movie,review);
	}

	/**
	 *	Method is to display a list of reviews of the movie
	 */
	private void listReview() {


		System.out.println(movie.getTitle() +" reviews.");

		ArrayList<Review> reviewList = getReviewList(movie);
		if (reviewList != null){
			for (int i = 0; i< reviewList.size();i++) {
				System.out.println("  Date:     " + reviewList.get(i).getDate());
				System.out.println(i+1 + " Name:     " + reviewList.get(i).getName());
				System.out.println("  Rating:   " + reviewList.get(i).getRating());
				System.out.println("  Comments: " + reviewList.get(i).getContent());
				System.out.println();
			}
		}

		else {
			System.out.println("This movie does not have any reviews yet.");
		}

	}
	/**
	 *	Gets last view before current view
	 */
	protected void destroy() {
		getPrevView();
	}
}