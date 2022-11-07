import Controller.CineplexController;

import View.View;

public class App {

    protected void start() {
       
        //first check whether this is the first time using the App (i.e. dat files do not exist)
        boolean initialized = CineplexController.initialize();
        if (!initialized) {

        }

        // call the main UI
        View mainUI = new View();
        mainUI.start();
    }

    public static void main(String[] args) {
        new App().start();
    }
}
