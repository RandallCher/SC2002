import Controller.CineplexController;
import View.View;

public class App {

    protected void start() {
        // this doesn't work, don't know why. Always fails with or without files, empty
        // or not
        boolean initialized = CineplexController.initialize();
        if (!initialized) {
            System.out.println("Error: failed to read data, please check file integrity.");
            System.out.println("Application terminating...");
            return;
        }

        // call the main UI
        View mainUI = new View();
        mainUI.start();
    }

    public static void main(String[] args) {
        new App().start();
    }
}
