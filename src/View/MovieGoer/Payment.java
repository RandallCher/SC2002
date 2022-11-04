package View.MovieGoer;

import Model.*;

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
		// TODO - implement Payment.Payment
		throw new UnsupportedOperationException();
	}

	private void generateTID() {
		// TODO - implement Payment.generateTID
		throw new UnsupportedOperationException();
	}

	private void computeTotalPrice() {
		// TODO - implement Payment.computeTotalPrice
		throw new UnsupportedOperationException();
	}

	private void displayMenu() {
		// TODO - implement Payment.displayMenu
		throw new UnsupportedOperationException();
	}

	private void logBooking() {
		// TODO - implement Payment.logBooking
		throw new UnsupportedOperationException();
	}

}