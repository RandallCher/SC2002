package Model;

import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Locale;

//do serializable
/**
 *  This class get details about Holidays, particulatly holiday rates
 **/ 

public class Holiday implements SerializableModel{

	private String name;
	private Date date;
	// Calendar calendar = Calendar.getInstance();
	private double rate;

	public String getHolidayName() {
		return this.name;
	}

	public Date getHolidayDate() {
		// XXXYYYYMMDDhhmm
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		return this.date;
	}

	public double getHolidayRate() {
		return this.rate;
	}

	/**
	 * 
	 * @param name
	 * @param date
	 * @param rate
	 */

	public Holiday(String name, Date date) {
		this.name = name;
		this.date = date;
		this.rate = 1.2;
	}

	public String printDetail() {
		return ("The Date is: " + date + "\n" + " The Name is: " + this.name + "\n");

	}

	public String toString() {
		return ("Name: " + name + "|"+"Date: " + date + "|");
		//example: Name|Date|
	}

}