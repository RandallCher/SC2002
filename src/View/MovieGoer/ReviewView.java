package View.MovieGoer;

import Controller.CineplexController;
import Model.Movie;
import Model.Review;
import View.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import static Controller.CineplexController.*;
import static Controller.IOController.*;
import static Model.Constant.MovieStatus.*;

/**
 * This class represents the review view.
 */
public class ReviewView extends View {

	private Movie movie;

	/**
	 *
	 * @param movie
	 */
	public ReviewView(Movie movie)
	{
		this.movie=movie;
	}

	protected void start() {
		displayMenu();
	}

	private void displayMenu() {
		if (movie.getMovieStatus() == COMING_SOON) {
			System.out.println("Movie is coming soon");
			destroy();
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Write a review");
		System.out.println("2. View all reviews");
		System.out.println("3. Go back")
		;
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				addReview();
				break;
			case 2:
				listReview();
				break;
			case 3:
				destroy();
				break;
		}
	}
	//Method for users to add a review to the movie
	private void addReview() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Write Your Review:");
		System.out.println("Please enter your name:");
		String name = sc.nextLine();
		System.out.println("Please enter your rating between 1 and 5:");
		int rating = sc.nextInt();
		System.out.println("Please enter your review:");
		name = sc.nextLine();
		String content = sc.nextLine();
		Review review = new Review(this.movie, rating, content, name);
		addNewReview(movie,review);
	}

	//Method to show a list of reviews
	private void listReview() {
		System.out.println(movie.getTitle() +" reviews.");
		ArrayList<Review> reviewList = CineplexController.getReviewList(movie);
		if (reviewList != null){
			for (int i = 0; i< reviewList.size();i++) {
				System.out.println("  Date:     " + reviewList.get(i).getDate());
				System.out.println(i+1 + " Name:     " + reviewList.get(i).getName());
				System.out.println("  Rating:   " + reviewList.get(i).getRating());
				System.out.println("  Comments: " + reviewList.get(i).getContent());
				System.out.println();
				i++;
			}
		}
	}

	protected void destroy() {
		((MovieListing)(getPrevView())).start(movie);
	}
}