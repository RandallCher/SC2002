package Model;

import java.io.Serializable;

public class BookingHistory implements Serializable{

	private String TID;
	private Customer customer;
	private Seat seat;

	/**
	 * 
	 * @param TID
	 * @param customer
	 * @param seat
	 */
	public BookingHistory(String TID, Customer customer, Seat seat) {
		this.TID = TID;
		this.customer = customer;
		this.seat = seat;
	}
	@Override 
	public String toString() {
		// TODO - implement BookingHistory.toString
		return ("TODO");
	}

}