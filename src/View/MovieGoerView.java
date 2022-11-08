package View;

import java.util.Scanner;

import View.MovieGoer.*;

/**
 * This is the main movie goer view.
 */
public class MovieGoerView extends View {
    public void start() {
        while (true) {
            System.out.print("------------- MOVIE GOER MENU -------------\n"
                    + "1: Search movie\n"
                    + "2: List movies\n"
                    + "3: List top 5 ranking movies\n"
                    + "4: View booking history\n"
                    + "5: Exit\n\n"
                    + "Enter your choice: ");
            Scanner scan = new Scanner(System.in);
            int choice;
            try {
                choice = scan.nextInt();

            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                break;
            }
            switch (choice) {
                case 1:
                    MovieListingsView movielistings = new MovieListingsView();
                    movielistings.searchMovie();

                    break;
                case 2:
                    movielistings = new MovieListingsView();
                    movielistings.displayMovieListings(false);
                    break;
                case 3:
                    movielistings = new MovieListingsView();
                    movielistings.displayMovieListings(true);
                    break;
                case 4:
                    navigateNextView(this, new BookingHistoryView());
                    break;
                case 5:
                    this.end();
                    break;

                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}
