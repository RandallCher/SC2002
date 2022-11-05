package View.MovieGoer;
import Controller.CineplexManager;
import Model.*;
import View.View;

import javax.swing.*;
import java.util.Scanner;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

public class Booking {

	private String ticketType;
	private double basePrice;
	private boolean bookingFinished;
	private Seat seat;

	/**
	 *
	 * @param seat
	 */
	public void BookingSeat(Seat seat) {
		this.seat = seat;
		bookingFinished = false;
		basePrice = seat.getShowtime().getCinema().getBasePrice();

	}

	protected void start() {
		if (!bookingFinished){
			displayMenu();
		}
		destroy();
	}

	private void displayMenu() {
		System.out.println("Here are your booking details");
		printBookingDetail();
		System.out.println("1. Press 1 to enter your information");
		System.out.println("2. Press 2 to go back");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		switch (input){
			case 1: promptCustomerInformation();
				break;
			case 2: destroy();
				break;
		}
	}

	private void computeBasePrice() {
		if(isWeekend(seat.getShowtime().getTime)){
			ticketType = "Weekend ";
			basePrice *= 1.5;

		}
		else{
			ticketType = "Weekday ";
		}
		Holiday holiday = getHoliday(seat.getShowtime().getTime());
		if (holiday != null){
			double holidayRate = holiday.getRate();
			basePrice *= holidayRate;
			ticketType = ticketType + holiday.getName();

		}
	}

	private void printBookingDetail() {
		Showtime showtime = seat.getShowtime();
		Movie movie = showtime.getMovie();
		Cinema cinema = showtime.getCinema();

		System.out.println(movie.getTitle());
		System.out.println(movie.getAgeRestriction());
		System.out.println("Date" + formatTimeMMddkkmm(showtime.getTime()));
		System.out.println("Ticket type: " + ticketType);
		System.out.println("Cinema: " + cinema + " (" + cinema.getCineplex() + ")");
		System.out.println("Seat: Row " + (seat.getRow()+1) + " Col " + seat.getCol());
		System.out.println("Ticket price (excluding GST): " + Math.round(basePrice));
	}

	private void promptCustomerInformation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name:");
		String name = sc.nextLine();
		System.out.println("Enter your mobile number:");
		String mobile = sc.nextLine();
		System.out.println("Enter your Email address:");
		String email = sc.nextLine();
		System.out.println("Are you a senior citizen?: Y or N");
		String input = sc.nextLine();
		boolean isSeniorCitizen;
		if (input == 'Y'){
			isSeniorCitizen = true;
		}
		else{
			isSeniorCitizen = false;
		}
		// Create customer object
		Customer moviegoer = new Customer(name, mobile, email, isSeniorCitizen);

		// proceed to payment
		bookingFinished = true;
		intent(this, new Payment(customer, seat, basePrice));
	}

	protected void destroy() {
		((MovieListing)(prevView.prevView)).start(
				CineplexManager.getMovieListing().get(CineplexManager.getMovieListing().indexOf(
						seat.getShowtime().getMovie())));
	}

}