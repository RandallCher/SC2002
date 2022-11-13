package Controller;
import static Controller.FilePaths.*; 
import static Controller.StartUpController.*; 


import Model.*;
import Model.Parameters.Cineplex;
import Model.Parameters.MovieStatus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * This class contains static method to read data from file and to manipulate
 * data from files.
 */

public final class CineplexController extends DataController {

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
    private CineplexController() {
    }

    /**
     * This method initializes and read all the necessary data from the files and store it
     * inside the variables if the files exist 
     * Else if the files do not exist it will create files and initialize required data. 
     * @return true if there is no error. false otherwise
     */
    public static boolean initialize() {
        try {
            // all these will cause FileNotFound Error if it is the first time opening the app and required data do not exist
            readStaffAccount();
            readSystem();
            readCinemaList();
            readMovieListing();
            readBookingHistory();
            readReviewList();
            readHolidayList();
            readMovieShowtime();
    

        } catch (FileNotFoundException e){ 
            //File not found means first time running the application. 
            //call start up controller and initialize the required files
            PrepareFirstTimeUse(); 
            initialize(); 
            

        } catch (IOException e) { 
            //error in file handling
            e.printStackTrace(); 
            return false; 

        } catch (ClassNotFoundException e) { 
            //if the required data is not in the files.
            e.printStackTrace();
        }
      
        return true; 
    }

    /**
     * The method is to verify whether the username and the password are valid.
     * 
     * @param username the username
     * @param password the password
     * @return true if the username exists and the corresponding password is
     *         correct, false otherwise
     */
    public static boolean verification(String username, String password) {
        if (staffAccount.get(username) == null)
            return false; // username does not exist
        else
            return staffAccount.get(username).equals(password); // password does not match
    }

    // READ METHODS

    /**
     * This method is to read cinema list and store it inside
     * {@code HashMap<Cineplex, ArrayList<Cinema>>}.
     * 
     * @throws IOException            when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readCinemaList() throws IOException, ClassNotFoundException {
        if (readFile(CINEMALIST_FILENAME) == null)
            cinemaList = new HashMap<>();
        else
            cinemaList = (HashMap<Cineplex, ArrayList<Cinema>>) readFile(CINEMALIST_FILENAME);
    }

    /**
     * This method is to read staff account and store it inside
     * {@code HashMap<String, String>}.
     * 
     * @throws IOException            when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readStaffAccount() throws IOException, ClassNotFoundException {
        if (readFile(STAFF_FILENAME) == null)
            staffAccount = new HashMap<>();
        else
            staffAccount = (HashMap<String, String>) readFile(STAFF_FILENAME);
    }

    /**
     * This method is to read review list and store it inside
     * {@code HashMap<Movie, ArrayList<Review>>}.
     * 
     * @throws IOException            when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readReviewList() throws IOException, ClassNotFoundException {
        if (readFile(REVIEWLIST_FILENAME) == null) reviewList = new HashMap<>();

        else reviewList = (HashMap<Movie, ArrayList<Review>>) readFile(REVIEWLIST_FILENAME);
        
    }





    /**
     * This method is to read holiday list and store it inside
     * {@code HashMap<String, Holiday>}.
     * 
     * @throws IOException            when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readHolidayList() throws IOException, ClassNotFoundException {
        if (readFile(HOLIDAYLIST_FILENAME) == null)
            holidayList = new HashMap<>();
        else
            holidayList = (HashMap<String, Holiday>) readFile(HOLIDAYLIST_FILENAME);
    }

    /**
     * This method is to read a movie listing from the file and store it inside
     * {@code ArrayList<Movie>}
     * 
     * @throws IOException            if the file cannot be found
     * @throws ClassNotFoundException if the class cannot be found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieListing() throws IOException, ClassNotFoundException {
        if (readFile(MOVIE_FILENAME) == null)
            movieListing = new ArrayList<>();

        else {
            movieListing = (ArrayList<Movie>) readFile(MOVIE_FILENAME);

            // sort the listings according to the movie status
            Collections.sort(movieListing, Comparator.comparing(obj -> obj.getMovieStatus().toString()));
        }
    }

    /**
     * This method is to read Booking History and store it inside
     * {@code ArrayList<BookingHistory>}
     * 
     * @throws IOException            if the file is not found
     * @throws ClassNotFoundException if the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readBookingHistory() throws IOException, ClassNotFoundException {
        if (readFile(BOOKINGHISTORY_FILENAME) == null)
            bookingHistory = new ArrayList<>();
        else
            bookingHistory = (ArrayList<BookingHistory>) readFile(BOOKINGHISTORY_FILENAME);
    }

    /**
     * This method is to read movie showtime and store it inside
     * {@code HashMap<Movie, ArrayList<Showtime>>}
     * 
     * @throws IOException            when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieShowtime() throws IOException, ClassNotFoundException {
        if (readFile(SHOWTIME_FILENAME) == null)
            movieShowtime = new HashMap<>();
        else 
            movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readFile(SHOWTIME_FILENAME);        
    }

    /**
     * This method is to read system and store it inside
     * {@code HashMap<String, Boolean>}
     * 
     * @throws IOException            when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readSystem() throws IOException, ClassNotFoundException {
        if (readFile(SYSTEM_FILENAME) == null)
            system = new HashMap<>();
        else
            system = (HashMap<String, Boolean>) readFile(SYSTEM_FILENAME);
    }

    // UPDATE METHODS

    /**
     * This method is to overwrite cinema list file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateCinemaList() throws IOException {
        writeFile(CINEMALIST_FILENAME, cinemaList);
    }




    /**
     * This method is to overwrite review list file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateReviewList() throws IOException {
        writeFile(REVIEWLIST_FILENAME, reviewList);
    }

    /**
     * This method is to overwrite holiday list file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateHolidayList() throws IOException {
        writeFile(HOLIDAYLIST_FILENAME, holidayList);
    }

    /**
     * This method is to overwrite movie listing file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateMovieListing() throws IOException {
        writeFile(MOVIE_FILENAME, movieListing);
    }

    /**
     * This method is to overwrite booking history file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateBookingHistory() throws IOException {
        writeFile(BOOKINGHISTORY_FILENAME, bookingHistory);
    }

    /**
     * This method is to overwrite showtime file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateMovieShowtime() throws IOException {
        writeFile(SHOWTIME_FILENAME, movieShowtime);
    }



     /**
     * This method is to update staffAccount file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateStaffAccount() throws IOException {
        writeFile(STAFF_FILENAME, staffAccount);
    }



    /**
     * This method is to update system file.
     * 
     * @throws IOException when the file address is invalid
     */
    public static void updateSystem() throws IOException {
        writeFile(SYSTEM_FILENAME, system);
    }

    // GETTER METHODS

    /**
     * This method is to get the top 5 ranking movie.
     * 
     * @return top 5 ranking by overall rating when orderBy is true, top 5 ranking
     *         by ticket sales when orderBy is false
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Movie> getTop5MovieListing() {
        boolean orderBy = system.get("movieOrder");
        ArrayList<Movie> top5 = new ArrayList<>();
        for (Movie movie : movieListing) {
            if (!movie.getMovieStatus().equals(MovieStatus.END_OF_SHOWING))
                top5.add(movie);
        }

        if (orderBy) { // order by overall ratings
            Collections.sort(top5, (o1, o2) -> Double.compare(getMovieRating(o2), getMovieRating(o1)));
        } else { // order by ticket sales
            Collections.sort(top5, (o1, o2) -> Integer.compare(o2.getSales(), o1.getSales()));
        }

        while (top5.size() > 5) {
            top5.remove(5);
        }

        return top5;
    }

    /**
     * This method is to get all movie listing
     * 
     * @return the movie listing {@code ArrayList<Movie>}
     */
    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }

    /**
     * This method is to get showtime by movie.
     * 
     * @param movie the movie to get showtime for
     * @return the showtime of the movie
     */
    public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
        if (movieShowtime == null) return null;        
        for (Map.Entry<Movie, ArrayList<Showtime>> entry: movieShowtime.entrySet()){
            Movie cur = entry.getKey(); 
            ArrayList<Showtime> curShowtime = entry.getValue(); 
            if (movie.equals(cur)) return curShowtime; 
            
        }
        return null;
    }

    /**
     * This method is to get the cinema list by cineplex.
     * 
     * @param cineplex the cineplex
     * @return the cinema list {@code ArrayList<Cinema>}
     */
    public static ArrayList<Cinema> getCinemaList(Cineplex cineplex) {
        return cinemaList.get(cineplex);
    }

    /**
     * This method is to get the review list by movie.
     * 
     * @param movie the movie to get review
     * @return the review list {@code ArrayList<Review>} or null if the movie does not have any reviews 
     */
    public static ArrayList<Review> getReviewList(Movie movie) {
        if (reviewList == null) return null; 
        for (Movie cur: reviewList.keySet()){
            ArrayList<Review> reviews = reviewList.get(cur); 
            if (cur.equals(movie)) return reviews; 
        }
        return null; 
    }

    /**
     * This method is to get the search result by matching the movie title.
     * 
     * @param title the movie title to be searched
     * @return the list of movies {@code ArrayList<Movie>} whose title contains the searched input, returns   an empty array if no movie with input title exists. 
     */
    public static ArrayList<Movie> getMovieByTitle(String title) {
        ArrayList<Movie> searchResult = new ArrayList<>();
        for (Movie movie : movieListing) {
            if (movie.getTitle().toUpperCase().contains(title.toUpperCase()) && movie.getMovieStatus() != MovieStatus.END_OF_SHOWING)
                searchResult.add(movie);
        }

        //if no movie is found, seachResult will be empty. So just return it
        return searchResult;
    }

    /**
     * This method is to get cinema by cinema code.
     * 
     * @param code the cinema code
     * @return the cinema {@code Cinema}
     */
    public static Cinema getCinemaByCode(String code) {
        for (Cineplex cineplex : Cineplex.values()) {
            if (getCinemaList(cineplex) == null)
                continue;
            for (Cinema cinema : getCinemaList(cineplex)) {
                if (cinema.getCode().equals(code))
                    return cinema;
            }
        }
        return null; // not found
    }

    /**
     * This method is to get the average rating of a movie.
     * 
     * @param movie the movie to calculate average rating
     * @return the average rating of the movie (round to two decimal places)
     */
    public static double getMovieRating(Movie movie) {
        ArrayList<Review> reviewList = getReviewList(movie);
        if (reviewList == null || reviewList.isEmpty())
            return 0;
        else {
            double sum = 0, avgRating;
            for (Review review : reviewList)
                sum += review.getRating();
            avgRating = sum / reviewList.size();
            return InputController.round(avgRating, 2);
        }
    }

    /**
     * This method is used to get the holiday with specified date.
     * 
     * @param time the date of the holiday
     * @return the holiday on that date {@code Holiday}
     */
    public static Holiday getHolidayByDate(Date time) {
        HashMap<String, Holiday> holidayList = getHolidayList();
        return holidayList.get(InputController.formatDateMMdd(time));
    }

    /**
     * This method is to get the holiday list.
     * 
     * @return the holiday list {@code HashMap<String, Holiday>}
     */
    public static HashMap<String, Holiday> getHolidayList() {
        return holidayList;
    }

    /**
     * This method is to get the booking history,
     * 
     * @return the booking history {@code ArrayList<BookingHistory>}
     */
    public static ArrayList<BookingHistory> getBookingHistory() {
        return bookingHistory;
    }

    /**
     * This method is to get the system setting.
     * 
     * @return the system setting {@code HashMap<String, Boolean>}
     */
    public static HashMap<String, Boolean> getSystem() {
        return system;
    }

    // ADD METHODS

    /**
     * This method is to add new movie to movie listing and update local files.
     * 
     * @param movie the movie to be added
     * @throws IOException when the file address is invalid
     */
    public static void addMovieListing(Movie movie) throws IOException {
        movieListing.add(movie);
        updateMovieListing();
    }



    /**
     * This method is to register new Staff Account to the database and update local files.
     * 
     * @param userName userName of the staff 
     * @param password password 
     * @throws IOException when the file address is invalid
     */
    public static void addStaffAccount(String userName, String password) throws IOException{
        staffAccount.put(userName, password); 
        updateStaffAccount();
    }

    /**
     * This method is to add showtime to the showtime list of a movie and update
     * local files.
     * 
     * @param showtime the showtime to be added
     * @param movie movie of the showtime
     * @throws IOException when the file address is invalid
     */
    public static void addShowtime(Showtime showtime, Movie movie) throws IOException {
        if (movieShowtime.get(movie) == null)
            movieShowtime.put(movie, new ArrayList<>());
        movieShowtime.get(movie).add(showtime);
        updateMovieShowtime();
    }

    /**
     * The method is to add new cinema to the cinema list and update local files.
     * 
     * @param cinema the cinema to be added
     * @throws IOException when the file address is invalid
     */
    public static void addCinema(Cinema cinema) throws IOException {
        if (cinemaList.get(cinema.getCineplex()) == null)
            cinemaList.put(cinema.getCineplex(), new ArrayList<>());
        cinemaList.get(cinema.getCineplex()).add(cinema);
        updateCinemaList(); 
    }

    /**
     * This method is to log new booking history and update local files.
     * 
     * @param record the new booking record
     * @throws IOException when the file address is invalid
     */
    public static void addBooking(BookingHistory record) throws IOException {
        bookingHistory.add(record);
        updateBookingHistory();
    }

    /**
     * This method is to add new review to a movie and update local files.
     * 
     * @param movie  the movie that got reviewed
     * @param review the review
     * @throws IOException when the file address is invalid
     */
    public static void addReview(Movie movie, Review review) throws IOException {
        if (reviewList.get(movie) == null) //first review of the movie
            reviewList.put(movie, new ArrayList<>());

        ArrayList<Review> temp = reviewList.get(movie); //temp array to store updated review list
        temp.add(review); //add the new review
        reviewList.put(movie, temp); 
        updateReviewList();
    }

    /**
     * The method is to add holiday to the holiday list and update local files.
     * 
     * @param date    the date of the holiday
     * @param holiday the holiday
     * @throws IOException when the file address is invalid
     */
    public static void addHoliday(String date, Holiday holiday) throws IOException {
        holidayList.put(date, holiday);
        updateHolidayList();
    }

    // REMOVE METHODS

    /**
     * This method is to remove a movie from a movie listing (i.e set status of the
     * movie to END_OF_SHOWING) and update the local files
     * 
     * @param movie the Movie to be removed from the list
     * @throws IOException
     */
    public static void removeMovieListing(Movie movie) throws IOException {
        movie.setMovieStatus(MovieStatus.END_OF_SHOWING);
        updateMovieListing();
    }

    /**
     * This method is to remove an existing showtime of a movie from a movie listing
     * 
     * @param showtime the showtime of a movie to be removed from the list
     * @throws IOException
     */
    public static void removeShowtime(Showtime showtime) throws IOException {
        Movie movie = showtime.getMovie();
        movieShowtime.get(movie).remove(showtime);
        updateMovieShowtime();
    }

    /**
     * This method is to remove all showtimes of a movie from a movie listing
     * 
     * @param movie the movie to remove all its showtime
     * @throws IOException
     */
    public static void removeAllShowtime(Movie movie) throws IOException {
        movieShowtime.remove(movie);
        updateMovieShowtime();
    }

    /**
     * This method is to remove a staff acount from the database 
     * @param userName username of the staff account
     * @param password password of the staff account 
     * @throws IOException 
     */
    public static void removeStaffAccount(String userName, String password) throws IOException{
        staffAccount.remove(userName);  
        updateStaffAccount();         
    } 

}
