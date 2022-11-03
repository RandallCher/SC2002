package Model;

import Model.Constant.Cineplex;
import java.io.Serializable;

public class Cinema implements Serializable{

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
		// TODO - implement Cinema.Cinema
		this.isPlatinum = isPlatinum;
		this.cineplex = cineplex;
		this.is3D = is3D;
		this.code = code;
		this.basePrice = basePrice;
	}

	public boolean isPlatinum() {
		// TODO - implement Cinema.isPlatinum
		return this.isPlatinum;
	}
	public Cineplex getCineplex(){
		return this.cineplex;
	}

	public boolean is3D() {
		// TODO - implement Cinema.is3D
		return this.is3D;
	}
	public String getCode(){
		return this.code;
	}
	public double getBasePrice(){
		return this.basePrice;
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// TODO - implement Cinema.equals
		if (this==o){
			return true;
		} 
		if (o==null){
			return false;
		}

		return false;
	}

	public int hashCode() {
		// TODO - implement Cinema.hashCode
		return code.hashCode();
	}

	public String toString() {
		// TODO - implement Cinema.toString
		return code;
	}

}