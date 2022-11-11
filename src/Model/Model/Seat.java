package Model;

public class Seat {

/**
 *  This class get details about a customer's booking's seat
 **/ 

	private int row;
	private int col;
	private boolean booked;
	private Showtime showtime;

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public boolean isBooked() {
		return this.booked;
	}

	public Showtime getShowtime() {
		return this.showtime;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param showtime2
	 */
	public Seat(int row, int col, Showtime showtime2) {

		this.row = row;
		this.col = col;
		this.showtime = showtime2;
		booked = false;
	}

	public void bookSeat() {

		booked = true;
	}


}