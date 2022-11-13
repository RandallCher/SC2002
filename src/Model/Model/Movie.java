package Model;
//do serializable
import Model.Parameters.*;
import java.util.*;
import Controller.CineplexController;


/**
 *  This class get details about a movie
 **/ 
public class Movie implements SerializableModel{

	private String title;
	private String director;
	private String sypnosis;
	private ArrayList<String> cast;
	private int sales;
	private AgeRestriction ageRestriction;
	private MovieStatus movieStatus;

	//set functions
	public Movie() {
		this.sales = 0;
	}
	

	public void setTitle(String title){
		this.title = title;
	}

	public void setDirector(String director){
		this.director = director;
	}
	public void setSypnosis(String sypnosis){
		this.sypnosis = sypnosis;
	}
	public void setCast(ArrayList<String> cast){
		this.cast = cast;
	}

	public void incrementSales() {
		this.sales ++;
	}
	public void decrementSales() {
		this.sales --;
	}
	public void setAgeRestrictions(AgeRestriction ageRestriction){
		this.ageRestriction = ageRestriction;
	}
	public void setMovieStatus(MovieStatus movieStatus){
		this.movieStatus = movieStatus;
	}

	//get functions
	public String getTitle(){
		return this.title;
	}
	public String getDirector(){
		return this.director;
	}
	public String getSypnosis(){
		return this.sypnosis;
	}
	public ArrayList<String> getCast(){
		return this.cast;
	}
	public int getSales(){
		return this.sales;
	}
	public AgeRestriction getAgeRestriction(){
		return this.ageRestriction;
	}
	public MovieStatus getMovieStatus(){
		return this.movieStatus;
	}


	
	public String toString() {
		StringBuilder builtString = new StringBuilder();
		StringBuilder casts = new StringBuilder();

		builtString.append("Title: ").append(getTitle()).append("|");
		builtString.append("Director: ").append(getDirector()).append("|");
		builtString.append("Cast: ");
		for (String Cast : cast) casts.append(Cast).append(", ");
		builtString.append(casts.toString()).append("|");
		builtString.append("Age Restriction: ").append(getAgeRestriction()).append("|");
		builtString.append("Movie Status: ").append(getMovieStatus()).append("|");
		builtString.append("Movie Rating: ").append(CineplexController.getMovieRating(this)).append("|");
		builtString.append("Movie Sales: ").append(getSales()).append("|");
		//example: Title|Director|cast1,cast2,cast3|ageRestriction|movieStatus|Rating|Sales|
		//
		return builtString.toString();
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		if(getClass() != o.getClass()){
			return false;
		}

		Movie movie = (Movie) o;
		if(this.title == null){
			return false;
		}
		if(!title.equals(movie.title)){
			return false;
		}
		//if(this.director == null){
		//	return false;
		//}
		//if(!director.equals(movie.director)){
		//	return false;
		//}

		return true;

	}


}
