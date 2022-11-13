package View.MovieGoer;
import java.io.IOException;
import java.util.UUID;

import Controller.CineplexController;
import static Controller.InputController.*;
import Model.BookingHistory;
import Model.Customer;
import Model.Movie;
import Model.Seat;
import Model.Parameters.AgeGroup;
import View.View;
import java.util.Calendar;


public class Payment extends View{

	private String TID;
	private double basePrice;
	private double GST;
	private double totalPrice;
	private Seat seat;
	private Customer customer;
	@Override
	public void start() {
		try {
			displayMenu();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *	Create class to help with payment for movie
	 * @param customer	the moviegoer
	 * @param seat	seat selected
	 * @param basePrice	baseprice of the movie selected

	 */
	Payment(Customer customer, Seat seat, double basePrice) {
		this.basePrice = basePrice;
		this.customer = customer;
		this.seat = seat;
		generateTID();
		computeTotalPrice();
	}



	/**
	 *	Method generates a transaction ID
	 */
	private void generateTID() {

		TID = UUID.randomUUID().toString();
	}


	/**
	 *	Method generates total price on movie based on age of the moviegoer and time of day
	 */
	private void computeTotalPrice() {
		//Adjusts price by age
		if (customer.getAgeGroup()==AgeGroup.SENIOR_CITIZEN||customer.getAgeGroup()==AgeGroup.CHILD) {
			basePrice *= 0.75;
		}
		//Adjusts price if evening
		Calendar cal = Calendar.getInstance();
		cal.setTime(seat.getShowtime().getTime());
		if (cal.get(Calendar.HOUR_OF_DAY ) >=18){ //evening showtime 
			basePrice *= 1.25;
		}


		GST = basePrice*0.07;

		totalPrice = basePrice + GST;

	}


	/**
	 *	Displays the menu for payment details
	 */
	private void displayMenu() throws IOException {
		System.out.println("Payment Details");
		System.out.printf("Ticket price: %.2f\n",basePrice);
		System.out.printf("Grand total: %.2f\n",totalPrice);
		System.out.printf("Total includes GST of: %.2f\n",GST);

		if (customer.getAgeGroup()!=AgeGroup.ADULT) {
			System.out.println("Discount 25%");
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(seat.getShowtime().getTime());
		if (cal.get(Calendar.HOUR_OF_DAY ) >=18){
			System.out.println("Evening Surcharge 25%");
		}

		System.out.println("1. Confirm your payment");
		System.out.println("2. Go back");

		int input = readUserChoice(2,1);
		switch (input) {
			case 1:
				addBooking();
				break;
			case 2:
				destroy();
				break;
		}
	}

	/**
	 *	Records down booking details of moviegoer
	 */
	private void addBooking() throws IOException {
		seat.bookSeat();
		Movie movie = seat.getShowtime().getMovie();
		CineplexController.getMovieListing().get(CineplexController.getMovieListing().indexOf(movie)).incrementSales();
		BookingHistory booking = new BookingHistory(TID, customer, seat);
		CineplexController.updateMovieShowtime();
		CineplexController.addBooking(booking);

		CineplexController.updateMovieListing();
		System.out.println("Payment has been confirmed.");
	}
	/**
	 *	Gets last view before current view
	 */
	protected void destroy() {
		getPrevView();
	}


}