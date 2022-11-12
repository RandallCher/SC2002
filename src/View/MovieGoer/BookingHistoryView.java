package View.MovieGoer;

import Model.BookingHistory;
import View.View;
import java.util.ArrayList;

import Controller.InputController;

import static Controller.CineplexController.getBookingHistory;
/**
 *	Create class to help with displaying booking history
 */
public class BookingHistoryView extends View {
	/**
	 *	Method displays the booking history menu
	 */
	public void start() {
		displayBookingHistory();
	}

	/**
	 *	Method displays booking history
	 */
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
		System.out.println("Press 1 to go back");
		System.out.println("1. Go Back");
		int input = InputController.readUserChoice(1,1);
		getPrevView();
	}

}