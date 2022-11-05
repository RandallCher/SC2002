package Controller;

import Model.*;
import Model.Parameters.Cineplex;
import java.io.IOException; 
import java.util.*;

import Controller.IOController; 

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



 	/**
     * This method is to overwrite review list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateReviewList() throws IOException {
        writeFile(REVIEWLIST_FILENAME, reviewList);
    }



    /**
     * This method is to overwrite holiday list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateHolidayList() throws IOException {
        writeFile(HOLIDAYLIST_FILENAME, holidayList);
    }



    /**
     * This method is to overwrite movie listing file.
     * @throws IOException when the file address is invalid
     */
    public static void updateMovieListing() throws IOException {
        writeFile(MOVIE_FILENAME, movieListing);
    }	



	/**
     * This method is to overwrite booking history file.
     * @throws IOException when the file address is invalid
     */
    public static void updateBookingHistory() throws IOException {
        writeFile(BOOKINGHISTORY_FILENAME, bookingHistory);
    }



	/**
     * This method is to overwrite showtime file.
     * @throws IOException when the file address is invalid
     */
    public static void updateMovieShowtime() throws IOException {
        writeFile(SHOWTIME_FILENAME, movieShowtime);
    }



	/**
     * This method is to overwrite system file.
     * @throws IOException when the file address is invalid
     */
    public static void updateSystem() throws IOException {
        writeFile(SYSTEM_FILENAME, system);
    }




	//GETTER METHODS


	 /**
     * This method is to get the top 5 ranking movie. 
     * @return top 5 ranking by overall rating when orderBy is true, top 5 ranking by ticket sales when orderBy is false
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Movie> getTop5MovieListing() {
        boolean orderBy = system.get("movieOrder");
        ArrayList<Movie> top5 = new ArrayList<>();
        for (Movie movie : movieListing) {
            if (!movie.getMovieStatus().equals(MovieStatus.END_OF_SHOWING)) top5.add(movie);
        }

        if (orderBy) {  // order by overall ratings
            Collections.sort(top5, (o1, o2) -> Double.compare(getMovieRating(o2), getMovieRating(o1)));
        }
        else {  // order by ticket sales
            Collections.sort(top5, (o1, o2) -> Integer.compare(o2.getSales(), o1.getSales()));
        }

        while (top5.size() > 5) {
            top5.remove(5);
        }

        return top5;
    }



	/**
     * This method is to get all movie listing 
     * @return the movie listing {@code ArrayList<Movie>}
     */
    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }



	/**
     * This method is to get showtime by movie.
     * @param movie the movie to get showtime for
     * @return the showtime of the movie
     */
    public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
        return movieShowtime.get(movie);
    }



	/**
     * This method is to get the cinema list by cineplex.
     * @param cineplex the cineplex
     * @return the cinema list {@code ArrayList<Cinema>}
     */
    public static ArrayList<Cinema> getCinemaList(Cineplex cineplex) {
        return cinemaList.get(cineplex);
    }



	
    /**
     * This method is to get the review list by movie.
     * @param movie the movie to get review
     * @return the review list {@code ArrayList<Review>}
     */
    public static ArrayList<Review> getReviewList(Movie movie) {
        return reviewList.get(movie);
    }



	/**
     * This method is to get the search result by matching the movie title.
     * @param title the movie title to be searched
     * @return the movie list {@code ArrayList<Movie>}
     */
    public static ArrayList<Movie> getMovieByTitle(String title) {
        ArrayList<Movie> searchResult = new ArrayList<>();
        for (Movie movie: movieListing) {
            if (movie.getTitle().toUpperCase().contains(title.toUpperCase())) searchResult.add(movie);
        }
        return searchResult;
    }



	
    /**
     * This method is to get cinema by cinema code.
     * @param code the cinema code
     * @return the cinema {@code Cinema}
     */
    public static Cinema getCinemaByCode(String code) {
        for (Cineplex cineplex : Cineplex.values()) {
            if (getCinemaList(cineplex) == null) continue;
            for (Cinema cinema : getCinemaList(cineplex)) {
                if (cinema.getCode().equals(code)) return cinema;
            }
        }
        return null;  // not found
    }



	/**
     * This method is to get the average rating of a movie.
     * @param movie the movie to calculate average rating
     * @return the average rating of the movie (round to two decimal places) 
     */
    public static double getMovieRating(Movie movie) {
        ArrayList<Review> reviewList = getReviewList(movie);
        if (reviewList == null || reviewList.isEmpty()) return 0;
        else {
            double sum = 0;
            for (Review review : reviewList) sum += review.getRating();
            return round(sum / reviewList.size(), 1);
        }
    }


	
    /**
     * This method is used to get the holiday with specified date.
     * @param time the date of the holiday 
     * @return the holiday on that date {@code Holiday}
     */
    public static Holiday getHolidayByDate(Date time) {
        HashMap<String, Holiday> holidayList = getHolidayList();
        return holidayList.get(formatTimeMMdd(time));
    }



    /**
     * This method is to get the holiday list. 
     * @return the holiday list {@code HashMap<String, Holiday>}
     */
    public static HashMap<String, Holiday> getHolidayList() {
        return holidayList;
    }



	/**
     * This method is to get the booking history, 
     * @return the booking history {@code ArrayList<BookingHistory>}
     */
    public static ArrayList<BookingHistory> getBookingHistory() {
        return bookingHistory;
    }



	/**
     * This method is to get the system setting.
     * @return the system setting {@code HashMap<String, Boolean>}
     */
    public static HashMap<String, Boolean> getSystem() { 
		return system; 
	}




	//ADD METHODS

	
    /**
     * This method is to add new movie to movie listing and update local files.
     * @param movie the movie to be added
     * @throws IOException when the file address is invalid
     */
    public static void addMovieListing(Movie movie) throws IOException{
        movieListing.add(movie);
        updateMovieListing();
    }



	/**
     * This method is to add showtime to the showtime list of a movie and update local files.
     * @param showtime the showtime to be added
     * @throws IOException when the file address is invalid
     */
    public static void addShowtime(Showtime showtime) throws IOException {
        Movie movie = showtime.getMovie();
        if (movieShowtime.get(movie) == null) movieShowtime.put(movie, new ArrayList<>());
        movieShowtime.get(movie).add(showtime);
        updateMovieShowtime();
    }


	
    /**
     * The method is to add new cinema to the cinema list and update local files.
     * @param cinema the cinema to be added
     * @throws IOException when the file address is invalid
     */
    public static void addCinema(Cinema cinema) throws IOException {
        if (cinemaList.get(cinema.getCineplex()) == null) cinemaList.put(cinema.getCineplex(), new ArrayList<>());
        cinemaList.get(cinema.getCineplex()).add(cinema);
    }



	 /**
     * This method is to log new booking history and update local files.
     * @param record the new booking record
     * @throws IOException when the file address is invalid
     */
    public static void addBooking(BookingHistory record) throws IOException {
        bookingHistory.add(record);
        updateBookingHistory();
    }



	 /**
     * This method is to add new review to a movie and update local files.
     * @param movie the movie that got reviewed
     * @param review the review
     * @throws IOException when the file address is invalid
     */
    public static void addReview(Movie movie, Review review) throws IOException {
        if(reviewList.get(movie) == null) reviewList.put(movie, new ArrayList<>());
        reviewList.get(movie).add(review);
        updateReviewList();
    }



	/**
     * The method is to add holiday to the holiday list and update local files.
     * @param date the date of the holiday
     * @param holiday the holiday
     * @throws IOException when the file address is invalid
     */
    public static void addHoliday(String date, Holiday holiday) throws IOException {
        holidayList.put(date, holiday);
        updateHolidayList();
    }




	//REMOVE METHODS
	




}