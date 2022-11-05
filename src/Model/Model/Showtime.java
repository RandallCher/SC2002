package Model;

import java.util.*;

public class Showtime {

	private Date time;
	//total cols
	private static int COLS = 17;
	//total rows
	private static int ROWS = 9;
	private Movie movie;
	private Cinema cinema;
	private Seat[][] seats;

	public Showtime() {
		seats = new Seat [ROWS][COLS];
		initializeSeat();
	}

	/**
	 * 
	 * @param row
	 * @param col
	 */
	public Seat getSeatAt(int row, int col) {
		if(col>8){
			col++;
		}
		return seats[row-1][col-1];
	}

	private void initializeSeat() {
		int Row = 0;
		int Col = 0;

		for( Col=0;Col<2;Col++){
			for(Row=0;Row<5;Row++){
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}

		for(Col=2;Col<8;Col++){
			for(Row=0;Row<9;Row++){
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}
			//aisle
		for(Col=9;Col<11;Col++){
			for(Row=1;Row<9;Row++){
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}	
		for(Col=11;Col<17;Col++){
			for(Row=0;Row<9;Row++){
				seats[Row][Col] = new Seat(Row, Col, this);
			}
		}	
	}

	public String getDetails() {
		StringBuilder builtString = new StringBuilder();

		builtString.append("Cineplex: ").append(cinema.getCineplex()).append("|");
		builtString.append("Cinema: ").append(cinema.toString()).append("|");
		builtString.append("Time: ").append(time.toString()).append("|");
		return builtString.toString();
		//Cineplex|Cinema|Time
	}

	public String toString() {
		return cinema.getCineplex().toString() + ": " + time;
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		if(getClass() != o.getClass()){
			return false;
		}

		Showtime showtime = (Showtime) o;
		if(movie != showtime.movie){
			return false;
		}
		if(cinema != showtime.cinema){
			return false;
		}
		if(time != showtime.time){
			return false;
		}
		return true;
	}

    public int hashCode() {
        int result = movie.hashCode();
        result = 31 * result + cinema.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

}