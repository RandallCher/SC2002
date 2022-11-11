package Model;

import java.util.*;

import Controller.InputController;

/**
 *  This class implements details about the showtime venue
 **/ 

public class Showtime implements SerializableModel {

	private Date time;

	private Movie movie;
	private Cinema cinema;
	private Seat[][] seats;

    // total cols
	private static int COLS = 17;
	// total rows
	private static int ROWS = 9;

	public Showtime() {
        
		seats = new Seat[ROWS][COLS];
		CinemaSeating();
	}

	public void CinemaSeating() {
		int Col = 0;
		int Row = 0;

		for (Col = 0; Col < 2; Col++) {
			for (Row = 0; Row < 5; Row++) {
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}

		for (Col = 2; Col < 8; Col++) {
			for (Row = 0; Row < 9; Row++) {
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}
		// aisle
		for (Col = 9; Col < 11; Col++) {
			for (Row = 1; Row < 9; Row++) {
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}
		for (Col = 11; Col < 17; Col++) {
			for (Row = 0; Row < 9; Row++) {
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 */
	public Seat getSeatAt(int row, int col) {
		if (col > 8) {
			col++;
		}
		return seats[row - 1][col - 1];
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Movie getMovie(){
		return this.movie; 
	}

	public Cinema getCinema(){
		return this.cinema; 
	}

	public Date getTime(){
		return this.time; 
	}

	public Seat[][] getSeats(){
		return this.seats; 
	}

	public String getDetails() {
		StringBuilder builtString = new StringBuilder();

		builtString.append("Cineplex: ").append(cinema.getCineplex()).append("|");
		builtString.append("Cinema: ").append(cinema.toString()).append("|");
		builtString.append("Time: ").append(time.toString()).append("|");
		return builtString.toString();
		// Cineplex|Cinema|Time
	}

	public String toString() {
		return cinema.getCineplex().toString() + ": " + time;
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		if (getClass() != o.getClass()) {
			return false;
		}

		Showtime showtime = (Showtime) o;

		if (!movie.equals(showtime.movie)) {
			return false;
		}
		if (!cinema.equals(showtime.cinema)) {
			return false;
		} 	
		if (!time.equals(showtime.time)){
			return false;
		}
		return InputController.formatDateMMddkkmm(time).equals(InputController.formatDateMMddkkmm(showtime.time));
	}
}