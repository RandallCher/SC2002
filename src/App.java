import Controller.CineplexController;

import View.View;

/**
 * This class starts the application
 */
public class App {

    protected void start() {

        // first check whether this is the first time using the App (i.e. dat files do
        // not exist)
        boolean initialized = CineplexController.initialize();

        // call the main UI
        new View().start();
    }

    /**
     * This is the main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new App().start();
    }
}
