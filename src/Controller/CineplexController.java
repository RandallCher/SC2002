import Model.*; 
import java.io.IOException; 
import java.util.*;

import Constant.Cineplex;

import static Model.Constant.*; 
import static Controller.IOController; 

/**
	* This class contains static method to read data from file and to manipulate data from files. 
 */

public class CineplexController extends DataController {

	/** Addresses of the files */
	private static final String MOVIE_FILENAME = "./data/movieListing.dat";
	private static final String STAFF_FILENAME= "./data/staffAccount.dat";
	private static final String CINEMALIST_FILENAME= "./data/cinemaList.dat";
	private static final String REVIEWLIST_FILENAME= "./data/reviewList.dat";
	private static final String SHOWTIME_FILENAME= "./data/showtime.dat";
	private static final String BOOKINGHISTORY_FILENAME= "./data/bookingHistory.dat";
	private static final String HOLIDAYLIST_FILENAME= "./data/holidayList.dat";
	private static final String SYSTEM_FILENAME= "./data/system.dat";

	/** To store data from file */
	private static HashMap<String, String> staffAccount;
	private static HashMap<Cineplex, ArrayList<Cinema>> cinemaList;
	private static HashMap<Movie, ArrayList<Review>> reviewList;
	private static HashMap<String, Holiday> holidayList;
	private static ArrayList<Movie> movieListing;
	private static ArrayList<BookingHistory> bookingHistory;
	private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;
	private static HashMap<String, Boolean> system;



	/** Private Constructor to supress instantiation */
	private CineplexController(){}


	public boolean initialize() {
	}
	




	//READ METHODS 



	/**
     * This method is to read cinema list and store it inside {@code HashMap<Cineplex, ArrayList<Cinema>>}.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readCinemaList() throws IOException, ClassNotFoundException {
        if (readFile(CINEMALIST_FILENAME) == null) cinemaList = new HashMap<>();
        else cinemaList = (HashMap<Cineplex, ArrayList<Cinema>>) readFile(CINEMALIST_FILENAME);
    }


    /**
     * This method is to read staff account and store it inside {@code HashMap<String, String>}.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readStaffAccount() throws IOException, ClassNotFoundException {
        if (readFile(STAFF_FILENAME) == null) staffAccount = new HashMap<>();
        else staffAccount = (HashMap<String, String>) readFile(STAFF_FILENAME);
    }



    /**
     * This method is to read review list and store it inside {@code HashMap<Movie, ArrayList<Review>>}.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readReviewList() throws IOException, ClassNotFoundException {
        if (readFile(REVIEWLIST_FILENAME) == null) reviewList = new HashMap<>();
        else reviewList = (HashMap<Movie, ArrayList<Review>>) readFile(REVIEWLIST_FILENAME);
    }


	 /**
     * This method is to read holiday list and store it inside {@code HashMap<String, Holiday>}.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readHolidayList() throws IOException, ClassNotFoundException {
        if (readFile(HOLIDAYLIST_FILENAME) == null) holidayList = new HashMap<>();
        else holidayList = (HashMap<String, Holiday>) readFile(HOLIDAYLIST_FILENAME);
    }



	/**
	 * This method is to read a movie listing from the file and store it inside {@code ArrayList<Movie>}
	 * @throws IOException if the file cannot be found
	 * @throws ClassNotFoundException if the class cannot be found
	 */
	@SuppressWarnings("unchecked")
	private static void readMovieListing() throws IOException, ClassNotFoundException{
		if (readFile(MOVIE_FILENAME) == null) movieListing = new ArrayList<>(); 
		
		else{
			movieListing = (ArrayList<Movie>) readFile(MOVIE_FILENAME); 

			//sort the listings according to the movie status 
			Collections.sort(movieListing, Comparator.comparing(obj-> obj.getMovieStatus().toString())); 
		}
	}



	/**
	 *	This method is to read Booking History and store it inside {@code ArrayList<BookingHistory>}
	 *	@throws IOException if the file is not found
	 *  @throws ClassNotFoundException if the class is not found
	 */
	@SuppressWarnings("unchecked")
	private static void readBookingHistory() throws IOException, ClassCastException{
		if (readFile(BOOKINGHISTORY_FILENAME) == null) bookingHistory = new ArrayList<>(); 
		else bookingHistory = (ArrayList<BookingHistory>) readFile(BOOKINGHISTORY_FILENAME); 
	}



	/**
     * This method is to read movie showtime and store it inside {@code HashMap<Movie, ArrayList<Showtime>>}
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieShowtime() throws IOException, ClassNotFoundException {
        if (readFile(SHOWTIME_FILENAME) == null) movieShowtime = new HashMap<>();
        else movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readFile(SHOWTIME_FILENAME);
    }



	/**
     * This method is to read system and store it inside {@code HashMap<String, Boolean>}
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readSystem() throws IOException, ClassNotFoundException {
        if (readFile(SYSTEM_FILENAME) == null) system = new HashMap<>();
        else system = (HashMap<String, Boolean>) readFile(SYSTEM_FILENAME);
    }



	//UPDATE METHODS



    /**
     * This method is to overwrite cinema list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateCinemaList() throws IOException {
        writeSerializedObject(CINEMALIST_FILENAME, cinemaList);
    }



}