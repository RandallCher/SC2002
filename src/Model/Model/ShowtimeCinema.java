package Model;

import Model.Showtime.initializeSeat;


public class ShowtimeCinema implements initializeSeat{
    private Seat[][] seats;
    // total cols
	private static int COLS = 17;
	// total rows
	private static int ROWS = 9;

    public ShowtimeCinema() {
        
		seats = new Seat[ROWS][COLS];
		CinemaSeating();
	}

    @Override
    public void CinemaSeating() {
		int Row = 0;
		int Col = 0;

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
    
}
