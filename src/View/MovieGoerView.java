package View;

import static Controller.InputController.readUserChoice;
import View.MovieGoer.*;

/**
 * This class is the main movie goer view. It provides a movie goer with all
 * possible options
 */
public class MovieGoerView extends View {
    public void start() {
        while (true) {
            System.out.print("------------- MOVIE GOER MENU -------------\n"
                    + "1: Search movie\n"
                    + "2: List movies\n"
                    + "3: List top 5 ranking movies\n"
                    + "4: View booking history\n"
                    + "5: Back to previous page\n\n"
                    + "Enter your choice: ");

            int choice = readUserChoice(5, 1);

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
                default:
                    this.end();
                    break;

            }
        }
    }
}
