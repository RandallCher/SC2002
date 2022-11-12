package Controller;
import static Controller.FilePaths.*; 

import Model.Parameters.*;
import Model.*; 
import java.io.File; 
import java.io.IOException;
import java.util.*;
import java.util.spi.CalendarDataProvider; 
import java.util.Calendar;


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
		MovieStatus movieStatus2 = MovieStatus.NOW_SHOWING;
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
		MovieStatus movieStatus3 = MovieStatus.NOW_SHOWING;
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
        HashMap<String, Holiday> holidayList = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        //Holidays
         
        String holidayName1 = "Christmas";
        calendar.set(2022, 11, 25);
        Date chrismas = calendar.getTime(); 
        Holiday holiday1 = new Holiday(holidayName1, chrismas);
        holidayList.put(holidayName1, holiday1);

        String holidayName2 = "New Year's Day";
        calendar.set(2023, 0, 1);
        Date newYearDay = calendar.getTime(); 
        Holiday holiday2 = new Holiday(holidayName2, newYearDay);
        holidayList.put(holidayName2, holiday2);

        String holidayName3 = "Chinese New Year";
        calendar.set(2023, 0, 24);
        Date chineseNewYear = calendar.getTime(); 
        Holiday holiday3 = new Holiday(holidayName3, chineseNewYear);
        holidayList.put(holidayName3, holiday3);

        String holidayName4 = "Good Friday";
        calendar.set(2023, 3, 7);
        Date goodFriday = calendar.getTime(); 
        Holiday holiday4 = new Holiday(holidayName4, goodFriday);
        holidayList.put(holidayName4, holiday4);

        writeFile(HOLIDAYLIST_FILENAME,holidayList);
 
    }

    /**
     * This method is to create a file to store cinema list for each cineplex and fill it with some  cinemas 
     * @throws IOException when there are any error in file handling. 
     */
    private static void CreateDefaultCinemaList() throws IOException{
        File f = new File(CINEMALIST_FILENAME); 
        f.createNewFile(); 

        HashMap<Cineplex, ArrayList<Cinema>> cinemaList = new HashMap<>();


          //FOR CINEPLEX CINELEISURE_ORCHARD
        ArrayList<Cinema> cinemaListing1 = new ArrayList<>();

       //Cinema 1 
        Cineplex cineplex1 = Cineplex.CINELEISURE_ORCHARD; 
        boolean isplatinum11 = true;
        boolean is3D11 = true;
        String code11 = "CIN1";
        double baseprice11 = 3.0;
	    Cinema cinema1 = new Cinema(cineplex1, isplatinum11, is3D11, code11, baseprice11);
        cinemaListing1.add(cinema1);
        
	    
        //Cinema 2
        boolean isplatinum12 = false;
        boolean is3D12 = true;
        String code12 = "CIN2";
        double baseprice12 = 2.0;
	    Cinema cinema2 = new Cinema(cineplex1, isplatinum12, is3D12, code12, baseprice12);
        cinemaListing1.add(cinema2);    
        
	    
	    //Cinema 3
        boolean isplatinum13 = true;
        boolean is3D13 = false;
        String code13 = "CIN3";
        double baseprice13 = 2.0;
	    Cinema cinema3 = new Cinema(cineplex1, isplatinum13, is3D13, code13, baseprice13);
        cinemaListing1.add(cinema3);    
        
        cinemaList.put(cineplex1, cinemaListing1); 


        //FOR CINEPLEX CAUSEWAY_POINT
        ArrayList<Cinema> cinemaListing2 = new ArrayList<>();

       //Cinema 1 
        Cineplex cineplex2 = Cineplex.CAUSEWAY_POINT; 
        boolean isplatinum21 = true;
        boolean is3D21 = true;
        String code21 = "CAU1";
        double baseprice21 = 3.0;
	    cinema1 = new Cinema(cineplex2, isplatinum21, is3D21, code21, baseprice21);
        cinemaListing2.add(cinema1);
        
	    
        //Cinema 2
        boolean isplatinum22 = false;
        boolean is3D22 = true;
        String code22 = "CAU2";
        double baseprice22 = 2.0;
	    cinema2 = new Cinema(cineplex2, isplatinum22, is3D22, code22, baseprice22);
        cinemaListing2.add(cinema2);
        
	    
	    //Cinema 3
        boolean isplatinum23 = true;
        boolean is3D23 = false;
        String code23 = "CAU3";
        double baseprice23 = 2.0;
	    cinema3 = new Cinema(cineplex2, isplatinum23, is3D23, code23, baseprice23);
        cinemaListing2.add(cinema3);
        
        cinemaList.put(cineplex2, cinemaListing2);


        //FOR CINEPLEX AMK_HUB
        ArrayList<Cinema> cinemaListing3 = new ArrayList<>();

        //Cinema 1
        Cineplex cineplex3 = Cineplex.AMK_HUB;
        boolean isplatinum31 = true;
        boolean is3D31 = true;
        String code31 = "AMK1";
        double baseprice31 = 3.0;
        cinema1 = new Cinema(cineplex3, isplatinum31, is3D31, code31, baseprice31);
        cinemaListing3.add(cinema1);


        //Cinema 2
        boolean isplatinum32 = false;
        boolean is3D32 = true;
        String code32 = "AMK2";
        double baseprice32 = 2.0;
        cinema2 = new Cinema(cineplex3, isplatinum32, is3D32, code32, baseprice32);
        cinemaListing3.add(cinema2);


        //Cinema 3
        boolean isplatinum33 = true;
        boolean is3D33 = false;
        String code33 = "AMk3";
        double baseprice33 = 2.0;
        cinema3 = new Cinema(cineplex3, isplatinum33, is3D33, code33, baseprice33);
        cinemaListing3.add(cinema3);

        cinemaList.put(cineplex3, cinemaListing3);



        //FOR CINEPLEX DOWNTOWN_EAST
        ArrayList<Cinema> cinemaListing4 = new ArrayList<>();

        //Cinema 1
        Cineplex cineplex4 = Cineplex.DOWNTOWN_EAST;
        boolean  isplatinum41 = true;
        boolean is3D41 = true;
        String code41 = "DWE1";
        double baseprice41 = 3.0;
        cinema1 = new Cinema(cineplex4, isplatinum41, is3D41, code41, baseprice41);
        cinemaListing4.add(cinema1);


        //Cinema 2
        boolean isplatinum42 = false;
        boolean is3D42 = true;
        String code42 = "DWE2";
        double baseprice42 = 2.0;
        cinema2 = new Cinema(cineplex4, isplatinum42, is3D42, code42, baseprice42);
        cinemaListing4.add(cinema2);


        //Cinema 3
        boolean isplatinum43 = true;
        boolean is3D43 = false;
        String code43 = "DWE3";
        double baseprice43 = 2.0;
        cinema3 = new Cinema(cineplex4, isplatinum43, is3D43, code43, baseprice43);
        cinemaListing4.add(cinema3);

        cinemaList.put(cineplex4, cinemaListing4);


        //FOR CINEPLEX WEST_MALL
        ArrayList<Cinema> cinemaListing5 = new ArrayList<>();

        //Cinema 1
        Cineplex cineplex5 = Cineplex.WEST_MALL;
        boolean isplatinum51 = true;
        boolean is3D51 = true;
        String code51 = "WEST1";
        double baseprice51 = 3.0;
        cinema1 = new Cinema(cineplex5, isplatinum51, is3D51, code51, baseprice51);
        cinemaListing5.add(cinema1);


        //Cinema 2
        boolean isplatinum52 = false;
        boolean is3D52 = true;
        String code52 = "WEST2";
        double baseprice52 = 2.0;
        cinema2 = new Cinema(cineplex5, isplatinum52, is3D52, code52, baseprice52);
        cinemaListing5.add(cinema2);


        //Cinema 3
        boolean isplatinum53 = true;
        boolean is3D53 = false;
        String code53 = "WEST3";
        double baseprice53 = 2.0;
        cinema3 = new Cinema(cineplex5, isplatinum53, is3D53, code53, baseprice53);
        cinemaListing5.add(cinema3);

        cinemaList.put(cineplex5, cinemaListing5);


        //FOR CINEPLEX JEM
        ArrayList<Cinema> cinemaListing6 = new ArrayList<>();

        //Cinema 1
        Cineplex cineplex6 = Cineplex.WEST_MALL;
        boolean isplatinum61 = true;
        boolean is3D61 = true;
        String code61 = "JEM1";
        double baseprice61 = 3.0;
        cinema1 = new Cinema(cineplex6, isplatinum61, is3D61, code61, baseprice61);
        cinemaListing6.add(cinema1);


        //Cinema 2
        boolean isplatinum62 = false;
        boolean is3D62 = true;
        String code62 = "JEM2";
        double baseprice62 = 2.0;
        cinema2 = new Cinema(cineplex6, isplatinum62, is3D62, code62, baseprice62);
        cinemaListing6.add(cinema2);


        //Cinema 3
        boolean isplatinum63 = true;
        boolean is3D63 = false;
        String code63 = "JEM3";
        double baseprice63 = 2.0;
        cinema3 = new Cinema(cineplex6, isplatinum63, is3D63, code63, baseprice63);
        cinemaListing6.add(cinema3);

        cinemaList.put(cineplex6, cinemaListing6);


        //FOR CINEPLEX PARKWAY_PARADE
        ArrayList<Cinema> cinemaListing7 = new ArrayList<>();

        //Cinema 1
        Cineplex cineplex7 = Cineplex.PARKWAY_PARADE;
        boolean isplatinum71 = true;
        boolean is3D71 = true;
        String code71 = "PP1";
        double baseprice71 = 3.0;
        cinema1 = new Cinema(cineplex7, isplatinum71, is3D71, code71, baseprice71);
        cinemaListing7.add(cinema1);


        //Cinema 2
        boolean isplatinum72 = false;
        boolean is3D72 = true;
        String code72 = "PP2";
        double baseprice72 = 2.0;
        cinema2 = new Cinema(cineplex7, isplatinum72, is3D72, code72, baseprice72);
        cinemaListing7.add(cinema2);


        //Cinema 3
        boolean isplatinum73 = true;
        boolean is3D73 = false;
        String code73 = "PP3";
        double baseprice73 = 2.0;
        cinema3 = new Cinema(cineplex7, isplatinum73, is3D73, code73, baseprice73);
        cinemaListing7.add(cinema3);

        cinemaList.put(cineplex7, cinemaListing7);



        writeFile(CINEMALIST_FILENAME,cinemaList); 
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

        //default is orded top 5 movings by rating
        HashMap <String,Boolean> defaultSystem = new HashMap<>(); 
        boolean isRatingRanked = true; 
        String order = "movieOrder"; 
        defaultSystem.put(order, isRatingRanked); 

        writeFile(SYSTEM_FILENAME, defaultSystem); 
    }

}
