package Controller;
import static Controller.FilePaths.*; 

import Model.Parameters.*;
import Model.Movie;  
import java.io.File; 
import java.io.IOException;
import java.util.*; 

/**
 * This class will never be called if the required data files already exists. 
 * This class is only called for the first time use of the application (i.e. no data files exist) to set * up default admin account and some default files for ease of use. 
 */



public final class StartUpController extends DataController{
    
    /** Private Constructor to supress instantiation */
    private StartUpController(){}

    public static void PrepareFirstTimeUse(){
        try{
        CreateDefaultStaffAccount();
        CreateDefaultMovieListing(); 
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


        
        //Movie 2
        String title3 = "Black Panther: Wakanda Forever";
        String director3 = "Ryan Coogler";
        String synopsis3 = 
        "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with Nakia and Everett Ross to forge a new path for their beloved kingdom."
        ; 
		MovieStatus movieStatus3 = MovieStatus.COMING_SOON;
		AgeRestriction ageRestriction3 = AgeRestriction.PG13;

        ArrayList<String> cast3 = new ArrayList<>();
        cast3.add("Michael B. Jordan"); 
        cast3.add("Letitia Wright"); 
        cast3.add("Angela Bassett");
        cast3.add("Tenoch Huerta"); 

        Movie movie3 = new Movie();
		movie3.setAgeRestrictions(ageRestriction3);
		movie3.setCast(cast3);
		movie3.setTitle(title3);
		movie3.setDirector(director3);
		movie3.setMovieStatus(movieStatus3);
		movie3.setSypnosis(synopsis3);
        movieListing.add(movie3); 


        writeFile(MOVIE_FILENAME,movieListing);
    }
}
