package Controller;

import Model.Parameters.*;

import java.util.*;

import java.math.RoundingMode;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.text.ParseException;

/**This class contains useful utility functions to validate user inputs for data formatting*/
public class InputController implements Controller {

	/**
	 * This method is to read an user choice from std input and upper and lower
	 * indicates the range.
	 * 
	 * @param upper upper bound for the input (inclusive)
	 * @param lower lower bound for the input (inclusvie)
	 * @return input from user if it is between the given range
	 */
	public static int readUserChoice(int upper, int lower) {
		Scanner sc = new Scanner(System.in);
		int input;
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid input. Please enter your choice again!");
			return readUserChoice(upper, lower);
		}

		if (input < lower || input > upper) {
			System.out.println("Invalid input. Please enter your choice again!");
			return readUserChoice(upper, lower);
		} else
			return input;

	}

	
	/**
	 * This method is to ask user for confirmation from std input
	 * 
	 * @param message message to be shown to the user
	 * @return true if the input is 'y' or 'Y'. false otherwise
	 */
	public static boolean confirmation(String message) {
		System.out.println(message);

		Scanner sc = new Scanner(System.in);
		if (sc.next().toUpperCase().equals("Y"))
			return true;
		else if (sc.next().toUpperCase().equals('N'))
			return false; 
		else
			System.out.println("Invalid input. Please try again");
			return confirmation(message); 
	}


	/**
	 * This method is to read a string from a std input.
	 * 
	 * @param message message to be shown to the user.
	 * @return the input from std input.
	 */
	public static String readString(String message) {
		System.out.println(message);

		Scanner sc = new Scanner(System.in);
		return sc.nextLine().strip();
	}

	/**
	 * This method is to read a double from a std input.
	 * 
	 * @param message message to be shown to the user.
	 * @return the input from std input.
	 */
	public static double readDouble(String message) {
		System.out.println(message);

		Scanner sc = new Scanner(System.in);
		double input;

		try {
			input = sc.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please try again");
			sc.nextLine(); // flush down the scanner
			return readDouble(message);
		}

		return input;
	}

	


	/**
	 * This method maps the string ageRestriction to respective enum AgeRestriction
	 * value
	 * 
	 * @param ageRestriction a string input for age restriction.
	 * @return {@code AgeRestriction} the enum AgeRestriction that the input is
	 *         mapped to
	 */
	public static AgeRestriction readAgeRestriction(String ageRestriction) {
		switch (ageRestriction.toUpperCase()) {
			case "G":
				return AgeRestriction.G;
			case "PG":
				return AgeRestriction.PG;
			case "PG13":
				return AgeRestriction.PG13;
			case "NC16":
				return AgeRestriction.NC16;
			case "M18":
				return AgeRestriction.M18;
			case "R21":
				return AgeRestriction.R21;
			default:
				return null;
		}
	}

	/**
	 * This method maps the String movieStatus to respective enum MovieStatus value
	 * 
	 * @param movieStatus a string input for movie status
	 * @return {@code MovieStatus} the enum MovieStatus that the input is mapped to
	 */
	public static MovieStatus readMovieStatus(String movieStatus) {
		switch (movieStatus.toUpperCase()) {
			case "COMING SOON":
				return MovieStatus.COMING_SOON;
			case "NOW SHOWING":
				return MovieStatus.NOW_SHOWING;
			case "END OF SHOWING":
				return MovieStatus.END_OF_SHOWING;
			default:
				return null;
		}
	}

	/**
	 * This method reads a string from std input in MM-dd kk:mm format and converts
	 * it to a Date
	 * 
	 * @param message the message to be shown to the user
	 * @return {@code Date} after formatting
	 */
	public static Date readDateMMddkkmm(String message) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		try {
			String input = readString(message); // call readString method to read a string
			input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input; // set year as current year
			Date date = simpleDateFormat.parse(input);
			return date;
		} catch (ParseException ex) {
			System.out.println("Wrong format. Please try again.");
			return readDateMMddkkmm(message);
		}
	}

	/**
	 * This method reads a string from std input in MM-dd format and converts it to
	 * a Date
	 * 
	 * @param message the message to be shown to the user
	 * @return {@code Date} after formatting
	 */
	public static Date readDateddMMyyyy(String message) {
		Date date;
		try {
			String input = readString(message);
			date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
			return date;
		} catch (ParseException ex) {
			System.out.println("Wrong format. Try again.");
			return readDateddMMyyyy(message);
		}
	}

	
	

	/**
	 * This method formats a date to string with format mm dd, kk:mm
	 * 
	 * @param time date to be formatted
	 * @return formatted string
	 */
	public static String formatDateMMddkkmm(Date time) {
		return new SimpleDateFormat("MMMM dd, kk:mm").format(time);
	}

	/**
	 * This method formats a date to string with format mm dd
	 * 
	 * @param time date to be formatted
	 * @return formatted string
	 */
	public static String formatDateMMdd(Date time) {
		return new SimpleDateFormat("MMMM, dd").format(time);
	}

	/**
	 * This method identify whether a date is a weekend
	 * 
	 * @param time date to be checked
	 * @return true if the date is a weekend. false otherwise
	 */
	public static boolean isWeekend(Date time) {
		String day = new SimpleDateFormat("EEEE").format(time);
		if (day.equals("Saturday") || day.equals("Sunday"))
			return true;
		else
			return false;
	}

	/**
	 * This method checks whether two dates are equal in month and date
	 * 
	 * @param date1 date 1 to be compared
	 * @param date2 date 2 to be compared
	 * @return true if they are equal in month and date.false otherwise
	 */
	public static boolean isEqualDate(Date date1, Date date2) {
		return formatDateMMdd(date1).equals(formatDateMMdd(date2));
	}

	/**
	 * This utility method rounds a double value to a specified decimal place for formatting purposes 
	 * 
	 * @param number value to be rounded
	 * @param places number of decimal places in the result
	 * @return rounded double value
	 */
	public static double round(double number, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		BigDecimal n = new BigDecimal(number);
		n = n.setScale(places, RoundingMode.HALF_UP);
		return n.doubleValue();
	}



	/**
     * This utility method is to generate multiple spaces with given size for formatting purposes.
     * @param size the number of spaces to be generated
     * @return the spaces generated
     */
    public static String generateSpaces(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

}
