package Model;

//  This class basically contains the booking history with all the details like :-
//  TID (Transaction ID), customer who made the booking and the seat booked.
/**
 *  
 **/ 
public class BookingHistory implements SerializableModel{

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
	public Customer getCustomer(){
		return this.customer;
	}
	public Seat getSeat(){
		return this.seat;
	}
	public String getTID(){
		return this.TID;
	}

}
