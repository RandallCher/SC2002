package View.MovieGoer;

import Model.BookingHistory;
import View.View;
import java.util.ArrayList;


import static Controller.CineplexController.getBookingHistory;

public class BookingHistoryView extends View {

	public void start() {
		displayBookingHistory();
	}

	public void displaySeats() {

	}

	public void makeBooking() {
		;
	}

	public void displayBookingHistory() {
		System.out.println("Booking history");
		ArrayList<BookingHistory> bookingHistory = getBookingHistory();

		if (bookingHistory.isEmpty()) {
			System.out.println("No previous bookings");
		}
		else {
			for(int i = 0; i< bookingHistory.size(); i++){
				System.out.println(bookingHistory.get(i));
			}


		}

		getPrevView();
	}

}