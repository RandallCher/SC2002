package Model;

import java.util.*;

import Controller.IOController;
import Controller.IOController.*;

/**
 *  This class get details about a the showtime venue
 **/ 

public class Showtime {

	private Date time;

	private Movie movie;
	private Cinema cinema;
	private Seat[][] seats;

	interface initializeSeat{
		public void CinemaSeating();
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
		return IOController.formatDateMMddkkmm(time).equals(formatTimeMMddkkmm(showtime.time));
	}
}