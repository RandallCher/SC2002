package Controller;
import static Controller.FilePaths.*; 
import java.io.File; 
import java.io.IOException;
/**
 * This class is only called for the first time use of the application to set up default admin account and files 
 */



public final class StartUpController {
    
    /** Private Constructor to supress instantiation */
    private StartUpController(){}

    public static void PrepareFirstTimeUse(){
        try{
        CreateDefaultStaffAccount();
        System.out.println("Done"); 
        }
        catch (IOException e){
            e.printStackTrace(); 
        }
    }


    /**
     * This method is to create a file to store staff accounts and then set up default account with 
     * User name "Admin" and Password "1234567890"
     */
    private static void CreateDefaultStaffAccount() throws IOException {
        File f = new File(STAFF_FILENAME);
        f.createNewFile(); 
    }
}
