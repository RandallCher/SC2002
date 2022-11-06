package View.MovieGoer;


import Model.Movie;
import Model.Seat;
import Model.Showtime;
import View.View;
import Model.*;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

import static Controller.CineplexController.getMovieShowtime;
import static Controller.IOController.*;
public class ShowtimeView extends View {

	private Movie movie;

	/**
	 *
	 * @param movie
	 */
	public ShowtimeView(Movie movie) {
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
		Date showDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String todayStr = simpleDateFormat.format(today).toString();
		String tomorrowStr = simpleDateFormat.format(tomorrow).toString();
		String afterStr = simpleDateFormat.format(dayAfter).toString();

		System.out.println("1. " + todayStr);
		System.out.println("2. " + tomorrowStr);
		System.out.println("3. " + afterStr);
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
		ArrayList<Showtime> showList = new ArrayList<>();
		if (getMovieShowtime(movie) != null) {
			for(int i = 0; i<getMovieShowtime(movie).size();i++){
				if (showDate.compareTo(getMovieShowtime(movie).get(i).getTime()) != 0){
					showList.add(getMovieShowtime(movie).get(i));


				}
			}
		}



		if (showList.isEmpty()) {
			System.out.println("No screening on that day");
			System.out.println("Please choose another timeslot:");
			displayMenu();

		}
		else{
			System.out.println("Please choose a timeslot");
			input = sc.nextInt();
			for(int i = 0; i<showList.size();i++){
				System.out.println(i+1 + showList.get(i).toString());
			}
		}

		Showtime showtime = showList.get(input - 1);
		displayShowtimeDetailMenu(showtime);


	}



	/**
	 *
	 * @param showtime
	 */
	private void displayShowtimeDetailMenu(Showtime showtime) {
		System.out.println(showtime.toString());
		displaySeat(showtime.getSeats());
		System.out.println("1. Select seat");
		System.out.println("2. Go back");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		switch (input) {
			case 1:
				displaySeat(showtime.getSeats());
				displayBookSeatMenu(showtime);
				break;
			case 2:
				destroy();
				break;

		}
	}


	/**
	 *
	 * @param showtime
	 */
	private void displayPrice(Showtime showtime) {
		double price = showtime.getCinema().getBasePrice();
		Movie movie = showtime.getMovie();
		System.out.println(movie.getTitle());
		System.out.println("Weekdays       Weekends");
		System.out.println("Adults: " + price + "   " + price*1.5);
		System.out.println("Senior Citizen: " + price*0.75 + "   " + price*1.5*0.75);

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
			for (int column = 0; column <= 16; column++) {
				if (seats[row][column] == null){
					System.out.print("   ");
				}
				else if (seats[row][column].isBooked()){
					System.out.print("[x]");
				}
				else{
					System.out.print("[ ]");
				}

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
	private void displayBookSeatMenu(Showtime showtime) {


		System.out.println("Enter row number");
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		System.out.println("Enter column number");
		int column = sc.nextInt();

		if (showtime.getSeatAt(row, column).isBooked()) {
			System.out.println("Seat is unavailable");
			displayBookSeatMenu(showtime);
		}
		else if (showtime.getSeatAt(row, column) == null) {
			System.out.println("Seat does not exist");
			displayBookSeatMenu(showtime);
		}
		else {
			System.out.println(showtime.getMovie().getSales());
			navigateNextView(this, new Booking(showtime.getSeatAt(row, column)));
		}
	}

	protected void destroy() {
		getPrevView();
	}

}