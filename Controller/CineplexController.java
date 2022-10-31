public class CineplexController extends DataController {

	private String MOVIE_FILENAME;
	private String STAFF_FILENAME;
	private String CINEMALIST_FILENAME;
	private String REVIEWLIST_FILENAME;
	private String SHOWTIME_FILENAME;
	private String BOOKINGHISTORY_FILENAME;
	private String HOLIDAYLIST_FILENAME;
	private String SYSTEM_FILENAME;
	private HashMap<string, string> staffAccount;
	private HashMap<Cineplex, ArrayList<Cinema>> cinemaList;
	private HashMap<Movie, ArrayList<Review>> reviewList;
	private HashMap<String, Holiday> holidayList;
	private ArrayList<Movie> movieListing;
	private ArrayList<BookingHistory> bookingHistory;
	private HashMap<Movie, ArrayList<Showtime>> movieShowtime;
	private HashMap<String, Boolean> system;

	public HashMap<string, string> getStaffAccount() {
		return this.staffAccount;
	}

	public void setStaffAccount(HashMap<string, string> staffAccount) {
		this.staffAccount = staffAccount;
	}

	public HashMap<Cineplex, ArrayList<Cinema>> getCinemaList() {
		return this.cinemaList;
	}

	public void setCinemaList(HashMap<Cineplex, ArrayList<Cinema>> cinemaList) {
		this.cinemaList = cinemaList;
	}

	public HashMap<Movie, ArrayList<Review>> getReviewList() {
		return this.reviewList;
	}

	public void setReviewList(HashMap<Movie, ArrayList<Review>> reviewList) {
		this.reviewList = reviewList;
	}

	public HashMap<String, Holiday> getHolidayList() {
		return this.holidayList;
	}

	public void setHolidayList(HashMap<String, Holiday> holidayList) {
		this.holidayList = holidayList;
	}

	public ArrayList<Movie> getMovieListing() {
		return this.movieListing;
	}

	public void setMovieListing(ArrayList<Movie> movieListing) {
		this.movieListing = movieListing;
	}

	public ArrayList<BookingHistory> getBookingHistory() {
		return this.bookingHistory;
	}

	public void setBookingHistory(ArrayList<BookingHistory> bookingHistory) {
		this.bookingHistory = bookingHistory;
	}

	public HashMap<String, Boolean> getSystem() {
		return this.system;
	}

	public void setSystem(HashMap<String, Boolean> system) {
		this.system = system;
	}

	private CineplexController() {
		// TODO - implement CineplexController.CineplexController
		throw new UnsupportedOperationException();
	}

	public boolean initialize() {
		// TODO - implement CineplexController.initialize
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	public boolean verification(String userName, String password) {
		// TODO - implement CineplexController.verification
		throw new UnsupportedOperationException();
	}

	private void readCinemaList() {
		// TODO - implement CineplexController.readCinemaList
		throw new UnsupportedOperationException();
	}

	private void readStaffAccount() {
		// TODO - implement CineplexController.readStaffAccount
		throw new UnsupportedOperationException();
	}

	private void readReviewList() {
		// TODO - implement CineplexController.readReviewList
		throw new UnsupportedOperationException();
	}

	private void readHolidayList() {
		// TODO - implement CineplexController.readHolidayList
		throw new UnsupportedOperationException();
	}

	private void readMovieListing() {
		// TODO - implement CineplexController.readMovieListing
		throw new UnsupportedOperationException();
	}

	private void readBookingHistory() {
		// TODO - implement CineplexController.readBookingHistory
		throw new UnsupportedOperationException();
	}

	private void readSystem() {
		// TODO - implement CineplexController.readSystem
		throw new UnsupportedOperationException();
	}

	private void readMovieShowtime() {
		// TODO - implement CineplexController.readMovieShowtime
		throw new UnsupportedOperationException();
	}

	public void updateCinemaList() {
		// TODO - implement CineplexController.updateCinemaList
		throw new UnsupportedOperationException();
	}

	public void updateHolidayList() {
		// TODO - implement CineplexController.updateHolidayList
		throw new UnsupportedOperationException();
	}

	public void updateReviewList() {
		// TODO - implement CineplexController.updateReviewList
		throw new UnsupportedOperationException();
	}

	public void updateMovieListing() {
		// TODO - implement CineplexController.updateMovieListing
		throw new UnsupportedOperationException();
	}

	public void updateBookingSystem() {
		// TODO - implement CineplexController.updateBookingSystem
		throw new UnsupportedOperationException();
	}

	public void updateMovieShowtime() {
		// TODO - implement CineplexController.updateMovieShowtime
		throw new UnsupportedOperationException();
	}

	public void updateSystem() {
		// TODO - implement CineplexController.updateSystem
		throw new UnsupportedOperationException();
	}

	public ArrayList<Movie> getTop5MovieListing() {
		// TODO - implement CineplexController.getTop5MovieListing
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public ArrayList<Showtime> getMovieShowTime(Movie movie) {
		// TODO - implement CineplexController.getMovieShowTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cineplex
	 */
	public ArrayList<Cinema> getCinemaList(Cineplex cineplex) {
		// TODO - implement CineplexController.getCinemaList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public ArrayList<Review> getReviewList(Movie movie) {
		// TODO - implement CineplexController.getReviewList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param title
	 */
	public ArrayList<Movie> getMovieByTitle(String title) {
		// TODO - implement CineplexController.getMovieByTitle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cinemaCode
	 */
	public Cinema getCinemaByCode(String cinemaCode) {
		// TODO - implement CineplexController.getCinemaByCode
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public double getMovieRating(Movie movie) {
		// TODO - implement CineplexController.getMovieRating
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param holidayDate
	 */
	public Holiday getHolidayByDate(date holidayDate) {
		// TODO - implement CineplexController.getHolidayByDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public void addMovieListing(Movie movie) {
		// TODO - implement CineplexController.addMovieListing
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param showtime
	 */
	public void addShowtime(Showtime showtime) {
		// TODO - implement CineplexController.addShowtime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cinema
	 */
	public void addCinema(Cinema cinema) {
		// TODO - implement CineplexController.addCinema
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param booking
	 */
	public void addBooking(BookingHistory booking) {
		// TODO - implement CineplexController.addBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 * @param review
	 */
	public void addReview(Movie movie, Review review) {
		// TODO - implement CineplexController.addReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param holidayDate
	 * @param holiday
	 */
	public void addHoliday(date holidayDate, Holiday holiday) {
		// TODO - implement CineplexController.addHoliday
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public void removeMovieListing(Movie movie) {
		// TODO - implement CineplexController.removeMovieListing
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param showTime
	 */
	public void removeShowTime(ShowTime showTime) {
		// TODO - implement CineplexController.removeShowTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movie
	 */
	public void removeAllShowTime(Movie movie) {
		// TODO - implement CineplexController.removeAllShowTime
		throw new UnsupportedOperationException();
	}

}