package View.MovieGoer;


import Model.Cinema;
import Model.Customer;
import Model.Holiday;
import Model.Movie;
import Model.Seat;
import View.View;
import Model.Showtime;
import Model.Parameters.AgeGroup;

import java.text.SimpleDateFormat;
import static Controller.InputController.*;
import static Controller.CineplexController.*;

public class Booking extends View{

	private String ticketType;
	private double basePrice;
	private boolean bookingFinished;
	private Seat seat;

	/** Creates a Booking class to help with booking the movie
	 * @param seat 	the seat that has been selected
	 */
	public Booking(Seat seat) {
		this.seat = seat;
		bookingFinished = false;
		basePrice = seat.getShowtime().getCinema().getBasePrice();
		computeBasePrice();

	}
	/** Method displays the menu if booking is not finished
	 *
	 */
	public void start() {
		if (!bookingFinished){
			displayMenu();
		}
		destroy();
	}
	/** Method displays the menu to facilitate booking
	 *
	 */
	private void displayMenu() {
		System.out.println("Here are your booking details");
		printBookingDetail();
		System.out.println("1. Press 1 to enter your information");
		System.out.println("2. Press 2 to go back");
		int input = readUserChoice(2,1);
		switch (input){
			case 1: promptCustomerInformation();
				break;
			case 2: destroy();
				break;
		}
	}
	/** Method helps compute price depending on weekday/weekend and holiday
	 *
	 */
	private void computeBasePrice() {
		if(isWeekend(seat.getShowtime().getTime())){
			ticketType = "Weekend ";
			basePrice *= 1.5;

		}
		else{
			ticketType = "Weekday ";
		}
		Holiday holiday =  getHolidayByDate((seat.getShowtime().getTime()));
		if (holiday != null){
			double holidayRate = holiday.getHolidayRate();
			basePrice *= holidayRate;
			ticketType = ticketType + holiday.getHolidayName();

		}
	}
	/** Method displays the details of the movie booking
	 *
	 */
	private void printBookingDetail() {
		Showtime showtime = seat.getShowtime();
		Movie movie = showtime.getMovie();
		Cinema cinema = showtime.getCinema();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String date = simpleDateFormat.format(showtime.getTime());
		System.out.println(movie.getTitle());
		System.out.println(movie.getAgeRestriction());
		System.out.println("Date" + formatDateMMddkkmm(showtime.getTime()));
		System.out.println("Ticket type: " + ticketType);
		System.out.println("Cinema: " + cinema + " (" + cinema.getCineplex() + ")");
		System.out.println("Seat: Row " + (seat.getRow()+1) + " Col " + seat.getCol()+1);
		System.out.println("Ticket price (excluding GST): " + Math.round(basePrice));
	}


	/** Method asks for customers information
	 *
	 */
	private void promptCustomerInformation() {
		AgeGroup ageGroup = AgeGroup.ADULT;

		String name = readString("Enter your name:");
		String mobile = readMobileNumber("Enter your mobile number:");
		String email = readEmail("Enter your Email address:");
		System.out.println("Select your age group: ");
		System.out.println("1. Child");
		System.out.println("2. Adult");
		System.out.println("3. Elderly ");
		int input = readUserChoice(3,1);
		switch (input){
			case 1: ageGroup = AgeGroup.CHILD;
				break;
			case 2: ageGroup = AgeGroup.ADULT;
				break;
			case 3: ageGroup = AgeGroup.SENIOR_CITIZEN;
				break;
		}

		// Create customer object
		Customer customer = new Customer(name, mobile, email, ageGroup);

		// proceed to payment
		bookingFinished = true;
		navigateNextView(this, new Payment(customer, seat, basePrice));
	}

	protected void destroy() {
		((MovieListingsView)(prevView.prevView)).start(
				getMovieListing().get(getMovieListing().indexOf(
						seat.getShowtime().getMovie())));
	}
}
