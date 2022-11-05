package View.MovieGoer;

import Model.Seat;
import Model.Customer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.Math;
import java.util.Scanner;

public class Payment {

	private String TID;
	private double basePrice;
	private double GST;
	private double totalPrice;
	private Seat seat;
	private Customer customer;

	protected void start() {
		// TODO - implement Payment.start
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param customer
	 * @param seat
	 * @param basePrice
	 * @param GST
	 */
	private Payment(Customer customer, Seat seat, double basePrice, double GST) {
		this.basePrice = basePrice;
		this.customer = customer;
		this.seat = seat;
		this.GST = GST;
	}

	private void generateTID() {
		LocalDateTime datetime= LocalDateTime.now() ;
		DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String datetimeString = datetime.format(format);
		TID = seat.getShowtime().getCinema().getCode() + datetimeString;
	}

	private void computeTotalPrice() {
		if (customer.isSeniorCitizen()) {
			basePrice *= 0.75;
		}
		GST = Math.round(basePrice*0.07);
		totalPrice = Math.round(basePrice + GST);

	}

	private void displayMenu() {
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
				logBooking();
				break;
			case 2:
				destroy();
				break;
		}
	}


	private void logBooking() {
		seat.bookSeat();
		Movie movie = seat.getShowtime().getMovie();
		CineplexManager.getMovieListing().get(CineplexController.getMovieListing().indexOf(movie)).incrementSales();
		BookingHistory record = new BookingHistory(TID, customer, seat);
		CineplexController.logBooking(record);
		CineplexController.updateShowtime();
		CineplexController.updateMovieListing();
		System.out.println("Payment has been confirmed.");
	}


}