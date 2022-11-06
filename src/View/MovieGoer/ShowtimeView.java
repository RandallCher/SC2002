package View.MovieGoer;

import Controller.IOController;
import View.View;
import Model.Movie;
import Model.Seat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

import static Controller.IOController.*;

public class ShowtimeView extends View {

	private Movie movie;

	/**
	 * 
	 * @param movie
	 */
	private ShowtimeView(Movie movie) {
		this.movie = movie;
	}

	public void start() {
		displayMenu();
	}

	private void displayMenu() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date dayAfter = calendar.getTime();
		Date showDate;
		System.out.println("1. " + formatTimeMMdd(today));
		System.out.println("2. " + formatTimeMMdd(tomorrow));
		System.out.println("3. " + formatTimeMMdd(afterTomorrow));
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		switch (input) {
			case 1:
				showDate = today;
				break;
			case 2:
				showDate = tomorrow;
				break;
			case 3:
				showDate = dayAfter;
				break;
		}
	}

	/**
	 * 
	 * @param showtime
	 */
	private void displayShowtimeDetailMenu(ShowtimeView showtime) {
		System.out.println(showtime.toString());
		System.out.println("1. Display Seats");
		System.out.println("2. Select seat");
		System.out.println("3. Go back");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		switch (input) {
			case 1:
				displaySeat(showtime.getSeats());
				displayShowtimeDetailMenu(showtime);
				break;
			case 2:
				displaySeat(showtime.getSeats());
				displayBookSeatMenu(showtime);
				break;
			case 3:
				destroy();
				break;

		}
	}

	/**
	 * 
	 * @param showtime
	 */
	private void displayPrice(ShowtimeView showtime) {
		double price = showtime.getCinema().getBasePrice();
		Movie movie = showtime.getMovie();
		System.out.println(movie.getTitle());
		System.out.println("Weekdays       Weekends");
		System.out.println("Adults: " + price + "   " + price * 1.5);
		System.out.println("Senior Citizen: " + price * 0.75 + "   " + price * 1.5 * 0.75);

	}

	/**
	 * 
	 * @param seats
	 */
	private void displaySeat(Seat[][] seats) {
		System.out.println("                    -------Screen------");
		System.out.println("     1  2  3  4  5  6  7  8     9 10 11 12 13 14 15 16");
		for (int row = 0; row <= 8; row++) {
			System.out.print(row + 1 + "   ");
			for (int col = 0; col <= 16; col++) {
				if (seats[row][col] == null)
					System.out.print("   ");
				else
					System.out.print(seats[row][col]);
			}
			System.out.println();
		}
		System.out.println();
		readString("Press ENTER to continue:");
	}

	/**
	 * 
	 * @param showtime
	 */
	private void displayBookSeatMenu(ShowtimeView showtime) {

		System.out.println("Enter row number");
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		System.out.println("Enter conlumn number");
		int column = sc.nextInt();

		if (showtime.getSeatAt(row, col) == null) {
			System.out.println("No such seat.");
			displayBookSeatMenu(showtime);
		} else if (showtime.getSeatAt(row, col).isBooked()) {
			System.out.println("The seat has been booked.");
			displayBookSeatMenu(showtime);
		} else {
			System.out.println(showtime.getMovie().getSales());
			navigateNextView(this, new Booking(showtime.getSeatAt(row, col)));
		}
	}

	protected void destroy() {
		getPrevView();
	}

}