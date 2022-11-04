package Model;

import java.io.Serializable;

import Model.Parameters.Cineplex;

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
		this.isPlatinum = isPlatinum;
		this.cineplex = cineplex;
		this.is3D = is3D;
		this.code = code;
		this.basePrice = basePrice;
	}

	public boolean isPlatinum() {
		return this.isPlatinum;
	}
	public Cineplex getCineplex(){
		return this.cineplex;
	}

	public boolean is3D() {
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
		if (this==o){
			return true;
		} 
		if (o==null){
			return false;
		}

		return false;
	}

	public int hashCode() {
		return code.hashCode();
	}


}