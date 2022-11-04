package View;

import java.util.Scanner;

// import where ever all the functions are

public class MovieGoerView extends View {
    public void start() {
        while (true) {
            System.out.print("------------- MOVIE GOER MENU -------------\n"
                    + "1: Search/List movie\n"
                    + "2: View movie details\n"
                    + "3: Check seat availability and selection of seat/s.\n"
                    + "4: Book and purchase ticket\n"
                    + "5: View booking history\n"
                    + "6: List Top 5 movie rankings\n"
                    + "7: Exit\n\n"
                    + "Enter your choice: ");
            Scanner scan = new Scanner(System.in);
            int choice;
            try {
                choice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                break;
            }
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    this.end();
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}