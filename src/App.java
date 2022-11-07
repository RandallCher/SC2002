import Controller.CineplexController;
import static Controller.StartUpController.*;
import View.View;

public class App {

    protected void start() {
       
        //first check whether this is the first time using the App (i.e. dat files do not exist)
        boolean initialized = CineplexController.initialize();
        if (!initialized) {
            //call the start up class to set up default admin account and data for first time use
            PrepareFirstTimeUse(); 
        }

        // call the main UI
        //View mainUI = new View();
        //mainUI.start();
    }

    public static void main(String[] args) {
        new App().start();
    }
}
