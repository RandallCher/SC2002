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

import static Controller.CineplexController.getMovieShowtime;
import static Controller.InputController.*;
public class ShowtimeView extends View {

	private Movie movie;

	/**
	 *	The class is to help display showtimes of a movie
	 * @param movie	the movie selected
	 */
	public ShowtimeView(Movie movie) {
		this.movie = movie;
	}

	public void start() {
		displayMenu();
	}
	/**
	 *	Method is to display the dates and showtime of the movie
	 */
	private void displayMenu() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date dayAfter = calendar.getTime();
		Date showDate = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String todayStr = simpleDateFormat.format(today).toString();
		String tomorrowStr = simpleDateFormat.format(tomorrow).toString();
		String afterStr = simpleDateFormat.format(dayAfter).toString();

		System.out.println("1. " + todayStr);
		System.out.println("2. " + tomorrowStr);
		System.out.println("3. " + afterStr);
		System.out.println("4. Go back");
		int input = readUserChoice(1,3);
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
			case 4:
				end();;
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
			for(int i = 0; i<showList.size();i++){
				System.out.println(i+1 + showList.get(i).toString());
			}
			
		}

		Showtime showtime = showList.get(input - 1);
		displayShowtimeDetailMenu(showtime);


	}



	/**
	 *	Method is to help with booking a seat for the selected showtime and movie
	 * @param showtime	the showtime of the movie
	 */
	private void displayShowtimeDetailMenu(Showtime showtime) {
		System.out.println(showtime.toString());
		displaySeat(showtime.getSeats());
		System.out.println("1. Select seat");
		System.out.println("2. Show Prices");
		System.out.println("3. Go back");
		int input = readUserChoice(1,3);
		switch (input) {
			case 1:
				displaySeat(showtime.getSeats());
				displayBookSeatMenu(showtime);
				break;
			case 2:
				displayPrice(showtime);
				displayBookSeatMenu(showtime);
			case 3:
				destroy();
				break;

		}
	}


	/**
	 *	Method is to display costs of the movie depending on age
	 * @param showtime	selected showtime of the movie
	 */
	private void displayPrice(Showtime showtime) {
		double price = showtime.getCinema().getBasePrice();
		Movie movie = showtime.getMovie();
		System.out.println(movie.getTitle());
		System.out.println("Weekdays       Weekends");
		System.out.println("Adults: " + price + "   " + price*1.5);
		System.out.println("Senior Citizen/Child: " + price*0.75 + "   " + price*1.5*0.75);

	}

	/**
	 *	Method is to display the seats available for booking
	 * @param seats	the seats for the movie
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
	 *	Method is for booking of seats
	 * @param showtime	selected showtime of movie
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
	/**
	 *	Gets last view before current view
	 */
	protected void destroy() {
		getPrevView();
	}

}