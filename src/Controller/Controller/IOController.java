package Controller;
import static Controller.CineplexController.*; 

import Model.Parameters.*;

import java.util.*;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

import java.math.RoundingMode;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.text.ParseException;


public class IOController implements Controller {

	/**
	 * This method is to read an user choice from std input and upper and lower indicates the range. 
	 * @param upper upper bound for the input (inclusive)
	 * @param lower lower bound for the input (inclusvie) 
	 * @return input from user if it is between the given range
	 */
	public static int readUserChoice(int upper, int lower) {
		Scanner sc = new Scanner(System.in); 
		int input; 
		try {
			input = sc.nextInt(); 
		}
		catch (InputMismatchException ex){
			System.out.println("Invalid input. Please try again! "); 
			return readUserChoice(upper, lower); 
		}

		if (input < lower || input > upper){
			System.out.println("Invalid iput. Please try again!"); 
			return readUserChoice(upper, lower); 
		}else return input; 

	}



	/**
	 * This method is to read a string from a std input. 
	 * @param message message(s) to be shown to the user. 
	 * @return the input from std input. 
	 */
	public static String readString(String... message) {
		for (String s: message) System.out.println(s); 

		Scanner sc = new Scanner(System.in); 
		return sc.nextLine(); 
	}




	/**
	 * This method is to read a double from a std input. 
	 * @param message message(s) to be shown to the user. 
	 * @return the input from std input. 
	 */
	public static double readDouble(String... message) {
		for (String s: message) System.out.println(s); 

		Scanner sc = new Scanner(System.in); 
		double input; 

		try{
			input = sc.nextDouble(); 
		}catch (InputMismatchException e){
			System.out.println("Invalid input. Please try again"); 
			sc.nextLine(); //flush down the scanner 
			return readDouble(message); 
		}

		return input; 
	}




	/**
	 * This method generates multiple spaces of a given size
	 * @param size number of spaces to generate
	 * @return the spaces generated
	 */
	public static String generateSpaces(int size) {
		StringBuilder s = new StringBuilder(); 
		for (int i = 0; i < size;i++) s.append(" "); 
		return s.toString(); 
	}



	/**
	 * This method reads an email address from an input
	 * @param message message(s) to be shown to the user. 
	 * @return the input from std input with email format 
	 */
	public static String readEmail(String... message) {
		for (String s: message) System.out.println(s); 
		Scanner sc = new Scanner(System.in); 
		String input = sc.nextLine(); 

		//REGEX pattern for email 
		Pattern Email = Pattern.compile(
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
		); 
		Matcher matcher = Email.matcher(input); 
		if (matcher.matches()) return input; 
		else{
			System.out.println("Email address is not valid. Please try again"); 
			return readEmail(message); 
		}
	}




	/**
	 * This method adds linebreaks for a string when its length exceeds a certain given value. 
	 * @param s input string to be formatted
	 * @param maxLineLength maximum length a line can be 
	 * @param lengthOfSpace number of spaces to be added after second line onwards
	 * @return formatted string
	 */
	public static String addLinebreaks(String s, int maxLineLength, int lengthOfSpace) {
		StringTokenizer token = new StringTokenizer(s, " "); 
		StringBuilder str = new StringBuilder(s.length()); 
		int lineLength = 0; 

		while (token.hasMoreTokens()){
			String word = token.nextToken(); 

			if (lineLength + word.length() > maxLineLength) { //exceeds max length 
                str.append("\n");
                for (int i = 0; i < lengthOfSpace; i++) str.append(" ");
                lineLength = 0;
            }
            str.append(word).append(" ");
            lineLength += word.length();
		}
		return str.toString(); 
	}



	/**
	 * This method maps the string ageRestriction to respective enum AgeRestriction value 
	 * @param ageRestriction a string input for age restriction.
	 * @return {@code AgeRestriction} the enum AgeRestriction that the input is mapped to 
	 */
	public static AgeRestriction readAgeRestriction(String ageRestriction) {
		switch(ageRestriction.toUpperCase()){
			case "G": return AgeRestriction.G; 
			case "PG": return AgeRestriction.PG; 
			case "PG13": return AgeRestriction.PG13; 
			case "NC16": return AgeRestriction.NC16; 
			case "M18": return AgeRestriction.M18; 
			case "R21": return AgeRestriction.R21; 
			default : return null; 
		}
	}




	/**
	 * This method maps the String movieStatus to respective enum MovieStatus value
	 * @param movieStatus a string input for movie status
	 * @return {@code MovieStatus} the enum MovieStatus that the input is mapped to
	 */
	public static MovieStatus readMovieStatus(String movieStatus) {
		switch (movieStatus.toUpperCase()){
			case "COMING SOON": return MovieStatus.COMING_SOON;
            case "NOW SHOWING": return MovieStatus.NOW_SHOWING;
            case "END OF SHOWING": return MovieStatus.END_OF_SHOWING;
            default: return null;
		}
	}




	/** This method reads a string from std input in MM-dd kk:mm format and converts it to a Date
	 * @param message the message(s) to be shown to the user
	 * @return {@code Date} after formatting
	 */
	public static Date readDateMMddkkmm(String... message) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        try {
            String input = readString(message); // call readString method to read a string 
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Please try again.");
            return readDateMMddkkmm(message);
        }
	}



	/** This method reads a string from std input in MM-dd format and converts it to a Date
	 * @param message the message(s) to be shown to the user
	 * @return {@code Date} after formatting
	 */
	public static Date readDateMMdd(String... message) {
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String input = readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readDateMMdd(message);
        }
	}



	/**
	 * This method is to ask user for confirmation from std input 
	 * @param message message(s) to be shown to the user 
	 * @return true if the input is Y. false otherwise
	 */
	public static boolean confirmation(String... message) {
		for (String s: message) System.out.println(s); 
		Scanner sc = new Scanner(System.in); 
		if (sc.next().toUpperCase().equals("Y")) return true; 
		else return false; 
	}




	/**
	 * This method prints menu items to std output.
	 * @param menuItems the menu items to be printed 
	 */
	public static void printMenu(String... menuItems) {
		for (String s: menuItems) System.out.println(s); 
	}




	/**
	 * This method prints specified header to std output
	 * @param header the header message to be printed
	 */
	public static void printHeader(String header) {
		int length = 65;
        for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();

        int indent = (length - header.length()) / 2;
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.print(header);
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println();

        for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();

	}




	/** 
	 * This method formats a date to string with format mm dd, kk:mm
	 * @param time date to be formatted
	 * @return formatted string 
	 */
	public static String formatDateMMddkkmm(Date time) {
	    return new SimpleDateFormat("MMMM dd, kk:mm").format(time);
	}




	/** 
	 * This method formats a date to string with format mm dd
	 * @param time date to be formatted
	 * @return formatted string 
	 */
	public static String formatDateMMdd(Date time) {
		return new SimpleDateFormat("MMMM, dd").format(time);
	}




	/**
	 * This method  identify whether a date is a weekend
	 * @param time date to be checked
	 * @return true if the date is a weekend. false otherwise
	 */
	public static boolean isWeekend(Date time) {
		String day = new SimpleDateFormat("EEEE").format(time);
        if (day.equals("Saturday") || day.equals("Sunday")) return true;
        else return false;
	}




	/**
	 * This method checks whether two dates are equal in month and date 
	 * @param date1 date 1 to be compared
	 * @param date2	date 2 to be compared 
	 * @return true if they are equal in month and date.false otherwise
	 */
	public static boolean isEqualDate(Date date1, Date date2) {
		return formatDateMMdd(date1).equals(formatDateMMdd(date2));
	}



	/**
	 * This method rounds a double value to a specified decimal place
	 * @param number value to be rounded
	 * @param places number of decimal places in the result
	 * @return rounded double value 
	 */
	public static double round(double number, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal n = new BigDecimal(number); 
		n = n.setScale(places, RoundingMode.HALF_UP); 
		return n.doubleValue(); 
	}

}
