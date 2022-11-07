package Model;
import java.io.Serializable;
import java.util.*;

/**
 *  This class allows to write in reviews and retrieve reviews
 **/ 
public class Review implements Serializable{

	private Date date;
	private int rating;
	private String content;
	private String name;
	private Movie movie;
	public final static int maxRating = 5;
	public final static int minRating = 1;



	public void setDate(Date date) {
		this.date = date;
	}

	public int getRating() {
		return this.rating;
	}

	public String getContent() {
		return this.content;
	}

	public String getName() {
		return this.name;
	}
	public Movie getMovie(){
		return this.movie;
	}
	public Date getDate() {
		return this.date;
	}


	/**
	 * 
	 * @param movie
	 * @param rating
	 * @param content
	 * @param name
	 */
	public Review(Movie movie, int rating, String content, String name) {
		if(rating > maxRating) this.rating = maxRating;
        else if (rating < minRating) this.rating = minRating;
        this.rating = rating;

        this.date = new Date();
        this.content = content;
        this.movie = movie;
        this.name = name;
    }

}