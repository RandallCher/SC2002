package Model;

import Model.Parameters.Cineplex;
/**
 *  This class is details about a Cinema's pricing strategy
 **/ 

public class Cinema implements SerializableModel {

	private boolean isPlatinum;
	private boolean is3D;
	private String code;
	private double basePrice;
	private Cineplex cineplex;

	/**
	 * 
	 * @param cineplex 
	 * @param isPlatinum
	 * @param is3D
	 * @param code
	 * @param basePrice
	 */
	public Cinema(Cineplex cineplex, boolean isPlatinum, boolean is3D, String code, double basePrice) {
		this.isPlatinum = isPlatinum;
		this.cineplex = cineplex;
		this.is3D = is3D;
		this.code = code;
		this.basePrice = basePrice;
	}

	public boolean isPlatinum() {
		return this.isPlatinum;
	}

	public Cineplex getCineplex() {
		return this.cineplex;
	}

	public boolean is3D() {
		return this.is3D;
	}

	public String getCode() {
		return this.code;
	}

	public double getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(double newPrice) {
		this.basePrice = newPrice;
		return;
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if(getClass()!= o.getClass()){
			return false;
		}
		Cinema cinema = (Cinema) o;
		if(!code.equals(cinema.code)){
			return false;
		}

		return true;
	}


	public String toString() {
		return code;
	}
}