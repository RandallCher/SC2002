package Model;

public class Seat {

	private int row;
	private int col;
	private boolean booked;
	private Showtime showtime;
	private ShowtimeCinema showtimeCinema;

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
	 * @param showtimeCinema
	 */
	public Seat(int row, int col, ShowtimeCinema showtimeCinema) {

		this.row = row;
		this.col = col;
		this.showtimeCinema = showtimeCinema;
		booked = false;
	}

	public void bookSeat() {

		booked = true;
	}


}