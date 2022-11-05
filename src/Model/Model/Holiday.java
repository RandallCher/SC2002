package Model;

import java.util.Calendar;
import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Locale;


//do serializable

public class Holiday {


	private String name;
	private Date date;
	Calendar calendar = Calendar.getInstance();
	private double rate;


	public String getName() {
		return this.name;
	}

	public Date getDate() {
		//XXXYYYYMMDDhhmm
		//int uear = calendar.get(Calendar.YEAR);
		//int month = calendar.get(Calendar.MONTH);
		//int day = calendar.get(Calendar.DAY_OF_MONTH);
		return this.date;
	}
	public double getRate() {
		return this.rate;
	}

	/**
	 * 
	 * @param name
	 * @param date
	 * @param rate
	 */
	public Holiday(String name, Date date, double rate) {
		this.name = name;
		this.date = date;
		this.rate =rate;
	}

	public String printDetail() {
		return("The Date is: "+ date+"\n" + " The Price is: "+ rate*Movie.price + "\n");

	}

	public String toString() {
		return ("Name: "+name + "Date: "+date);
	}

}