package Controller;
import static Controller.FilePaths.*; 

import Model.Parameters.*;
import Model.*; 
import java.io.File; 
import java.io.IOException;
import java.util.*; 


/**
 * This class is only called for the first time use of the application (i.e. no data files exist) to set * up default admin account and some default files for ease of use. 
 * This class will never be called if the required data fileS already exists. 
 */



public final class StartUpController extends DataController{
    
    /** Private Constructor to supress instantiation */
    private StartUpController(){}

    public static void PrepareFirstTimeUse(){
        try{
        CreateDefaultStaffAccount();
        CreateDefaultMovieListing(); 
        CreateDefaultHolidayList(); 
        CreateDefaultCinemaList(); 
        CreateDefaultReviewList(); 
        CreateDefaultShowtime(); 
        CreateDefaultBookingHistory(); 
        CreateDefaultSystem(); 
    
        }
        catch (IOException e){
            e.printStackTrace(); 
        }
    }


    /**
     * This method is to create a file to set up and store staff accounts and then set up the file with 
     * default admin account: User name "admin" and Password "1234567890"
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultStaffAccount() throws IOException {
        File f = new File(STAFF_FILENAME);
        f.createNewFile(); 
        String defaultName = "admin"; 
        String defaultPassword = "1234567890"; 
        HashMap<String, String> staffAccount = new HashMap<>();
        staffAccount.put(defaultName,defaultPassword); 
        writeFile(STAFF_FILENAME,staffAccount);
    }



     /**
     * This method is to create a file to store movie listings and then set up the file with 
     * some movies 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultMovieListing() throws IOException {

        File f = new File(MOVIE_FILENAME);
        f.createNewFile(); 
        ArrayList<Movie> movieListing = new ArrayList<>();
        
        //below are some movies to initialize the file. Movie Staff can later edit the movie listings as required


        //Movie 1
        String title1 = "Black Adam";
        String director1 = "Jaume Collet-Serra";
        String synopsis1 = 
        "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam. Nearly 5,000 years have passed, and Black Adam has gone from man to myth to legend. Now free, his unique form of justice, born out of rage, is challenged by modern-day heroes who form the Justice Society: Hawkman, Dr. Fate, Atom Smasher and Cyclone."
        ; 
		MovieStatus movieStatus1 = MovieStatus.NOW_SHOWING;
		AgeRestriction ageRestriction1 = AgeRestriction.PG13;

        ArrayList<String> cast1 = new ArrayList<>();
        cast1.add("Dwayne Johnson"); 
        cast1.add("Pierce Brosnan"); 
        cast1.add("Noah Centineo");
        cast1.add("Sarah Shahi"); 

        Movie movie1 = new Movie();
		movie1.setAgeRestrictions(ageRestriction1);
		movie1.setCast(cast1);
		movie1.setTitle(title1);
		movie1.setDirector(director1);
		movie1.setMovieStatus(movieStatus1);
		movie1.setSypnosis(synopsis1);
        movieListing.add(movie1); 




        //Movie 2
        String title2 = "Black Panther: Wakanda Forever";
        String director2 = "Ryan Coogler";
        String synopsis2 = 
        "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with Nakia and Everett Ross to forge a new path for their beloved kingdom."
        ; 
		MovieStatus movieStatus2 = MovieStatus.COMING_SOON;
		AgeRestriction ageRestriction2 = AgeRestriction.PG13;

        ArrayList<String> cast2 = new ArrayList<>();
        cast2.add("Michael B. Jordan"); 
        cast2.add("Letitia Wright"); 
        cast2.add("Angela Bassett");
        cast2.add("Tenoch Huerta"); 

        Movie movie2 = new Movie();
		movie2.setAgeRestrictions(ageRestriction2);
		movie2.setCast(cast2);
		movie2.setTitle(title2);
		movie2.setDirector(director2);
		movie2.setMovieStatus(movieStatus2);
		movie2.setSypnosis(synopsis2);
        movieListing.add(movie2); 


        
        //Movie 3
        String title3 = "Avatar 2: The Way of Water";
        String director3 = "James Cameron";
        String synopsis3 = 
        "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora. When an ancient threat resurfaces, Jake must fight a difficult war against the humans."
        ; 
		MovieStatus movieStatus3 = MovieStatus.COMING_SOON;
		AgeRestriction ageRestriction3 = AgeRestriction.PG13;

        ArrayList<String> cast3 = new ArrayList<>();
        cast3.add("Sam Worthington"); 
        cast3.add("Zoe Saldana"); 
        cast3.add("Kate Winslet");
        cast3.add("Vin Diesel"); 
        cast3.add("Michelle Yeoh"); 

        Movie movie3 = new Movie();
		movie3.setAgeRestrictions(ageRestriction3);
		movie3.setCast(cast3);
		movie3.setTitle(title3);
		movie3.setDirector(director3);
		movie3.setMovieStatus(movieStatus3);
		movie3.setSypnosis(synopsis3);
        movieListing.add(movie3); 


        
        //Movie 4
        String title4 = "Nope";
        String director4 = "Jordan Peele";
        String synopsis4 = 
        "Two siblings running a horse ranch in California discover something wonderful and sinister in the skies above, while the owner of an adjacent theme park tries to profit from the mysterious, otherworldly phenomenon."
        ; 
		MovieStatus movieStatus4 = MovieStatus.END_OF_SHOWING;
		AgeRestriction ageRestriction4 = AgeRestriction.R21;

        ArrayList<String> cast4 = new ArrayList<>();
        cast4.add("Keke Palmer"); 
        cast4.add("Daniel Kaluuya"); 
        cast4.add("Steven Yeun");
        cast4.add("Brandon Perea"); 
        
        Movie movie4 = new Movie();
		movie4.setAgeRestrictions(ageRestriction4);
		movie4.setCast(cast4);
		movie4.setTitle(title4);
		movie4.setDirector(director4);
		movie4.setMovieStatus(movieStatus4);
		movie4.setSypnosis(synopsis4);
        movieListing.add(movie4); 


        writeFile(MOVIE_FILENAME,movieListing);
    }



    /**
     * This method is to create a file to store holiday list 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultHolidayList() throws IOException {
        File f = new File(HOLIDAYLIST_FILENAME);
        f.createNewFile();
        
        //TODO ADD HOLIDAYS 
    }

    /**
     * This method is to create a file to store cinema list 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultCinemaList() throws IOException{
        File f = new File(CINEMALIST_FILENAME); 
        f.createNewFile(); 
        ArrayList<Cinema> cinemaListing = new ArrayList<>();

        HashMap<Cineplex, ArrayList<Cinema>> cinemaList = new HashMap<>();
        

       //Cinema 1
        boolean isplatinum1 = "True";
        boolean is3D1 = "True";
        String code1 = "CIN"
        double baseprice1 = 2.0;
        Cineplex cineplex1 = Cineplex.CINELEISURE_ORCHARD;
	    
	Cinema cinema1 = new Cinema(cineplex1, isplatinum1, is3D1, code1, baseprice1);
        cinemaListing.add(cinema1);
        
	    
        //Cinema 2
        boolean isplatinum2 = "True";
        boolean is3D2 = "True";
        String code2 = "CAU"
        double baseprice2 = 2.0;
        Cineplex cineplex2 = Cineplex.CAUSEWAY_POINT; 
        
	Cinema cinema2 = new Cinema(cineplex2, isplatinum2, is3D2, code2, baseprice2);
        cinemaListing.add(cinema2);    
        
	    
	//Cinema 3
        boolean isplatinum3 = "True";
        boolean is3D3 = "True";
        String code3 = "AMK"
        double baseprice3 = 2.0;
        Cineplex cineplex3 = Cineplex.AMK_HUB; 
	Cinema cinema3 = new Cinema(cineplex3, isplatinum3, is3D3, code3, baseprice3);
        cinemaListing.add(cinema3);    
        //TODO ADD CINEMAS 
    }



    /**
     * This method is to create a file to store showtimes 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultShowtime() throws IOException{
        File f = new File(SHOWTIME_FILENAME); 
        f.createNewFile(); 

        //TODO ADD SHOWTIMES
    }



    /**
     * This method is to create a file to store reviews list 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultReviewList() throws IOException{
        File f = new File(REVIEWLIST_FILENAME); 
        f.createNewFile(); 


    }



     /**
     * This method is to create a file to store booking history 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultBookingHistory() throws IOException{
        File f = new File(BOOKINGHISTORY_FILENAME); 
        f.createNewFile(); 
    }


    /**
     * This method is to create a file to store system settingss
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultSystem() throws IOException{
        File f = new File(SYSTEM_FILENAME); 
        f.createNewFile(); 
    }

}
