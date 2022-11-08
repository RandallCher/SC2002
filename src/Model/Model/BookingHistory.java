package Model;

import java.io.Serializable;
//  This class basically contains the booking history with all the details like :-
//  TID (Transaction ID), customer who made the booking and the seat booked.
/**
 *  
 **/ 
public class BookingHistory implements Serializable{

	private String TID;
	private Customer customer;
	private Seat seat;

	/**
	 * @code BookingHistory :- Constructor to initialize the following details
	 * @param TID (Transaction ID)
	 * @param customer (person making the booking)
	 * @param seat (Seat details)
	 */
	public BookingHistory(String TID, Customer customer, Seat seat) {
		this.TID = TID;
		this.customer = customer;
		this.seat = seat;
	}

	//might not use 
	@Override 
	public String toString() {
		StringBuilder builtString = new StringBuilder();


		builtString.append("Name: ").append(customer.getName()).append("|");
		builtString.append("Mobile: ").append(customer.getMobile()).append("|");
		builtString.append("Email: ").append(customer.getEmail()).append("|");
		builtString.append("AgeGroup : ").append(customer.getAgeGroup()).append("|");
		builtString.append("Moviedet: ").append(seat.getShowtime().getDetails()).append("|");
		builtString.append("SeatRow: ").append(seat.getRow()).append("|");
		builtString.append("SeatCol: ").append(seat.getCol()).append("|");
		//Name|Mobile|Email|isSeniorCitizen|Showtime|Row|Col
		return builtString.toString();
	}

}
