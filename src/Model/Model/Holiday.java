package Model;

import java.util.Date;

public class Holiday {

	private String name;
	private Date date;
	private double rate;

	public String getName() {
		return this.name;
	}

	public Date getDate() {
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

	public String Query() {
		return (name + "("+date+")");
	}

}