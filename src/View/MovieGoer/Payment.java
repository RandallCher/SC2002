package View.MovieGoer;
import java.io.IOException;
import java.util.UUID;

import Controller.CineplexController;
import Model.*;
import Model.BookingHistory;
import Model.Customer;
import Model.Movie;
import Model.Seat;
import View.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.Math;
import java.util.Scanner;

public class Payment extends View {

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
	 *
	 * @param customer
	 * @param seat
	 * @param basePrice

	 */
	Payment(Customer customer, Seat seat, double basePrice) {
		this.basePrice = basePrice;
		this.customer = customer;
		this.seat = seat;

	}

	private void generateTID() {

		TID = UUID.randomUUID().toString();
	}

	private void computeTotalPrice() {
		if (customer.isSeniorCitizen()) {
			basePrice *= 0.75;
		}
		GST = Math.round(basePrice*0.07);
		totalPrice = Math.round(basePrice + GST);

	}

	private void displayMenu() throws IOException {
		System.out.println("Payment Details");
		System.out.println("Ticket price: " + basePrice);
		System.out.println("Grand total: " + totalPrice);
		System.out.println("Total includes GST of: " + GST);


		if (customer.isSeniorCitizen()) {
			System.out.println("Senior Citizen Discount 25%");
		}
		System.out.println("1. Confirm your payment");
		System.out.println("2. Go back");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				addBooking();
				break;
			case 2:
				destroy();
				break;
		}
	}


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

	protected void destroy() {
		navigateNextView(this, new Booking(seat));
	}


}