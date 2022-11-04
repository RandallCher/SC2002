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
		StringBuilder builtString = new StringBuilder();


		builtString.append("Name: ").append(customer.getName()).append("|");
		builtString.append("Mobile: ").append(customer.getMobile()).append("|");
		builtString.append("Email: ").append(customer.getEmail()).append("|");
		builtString.append("isSeniorCitizen: ").append(customer.isSeniorCitizen()).append("|");
		builtString.append("Moviedet: ").append(seat.getShowtime().getDetails()).append("|");
		//Name|Mobile|Email|isSeniorCitizen|Showtime
		return ("TODO");
	}

}