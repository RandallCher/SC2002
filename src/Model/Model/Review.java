package Model;
public class Review {

	private Date date;
	private int rating;
	private String content;
	private String name;
	private static int maxRating = 5;
	private static int minRating = 1;

	public Date getDate() {
		return this.date;
	}

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

	/**
	 * 
	 * @param movie
	 * @param rating
	 * @param content
	 * @param name
	 */
	public Review(Movie movie, int rating, String content, String name) {
		// TODO - implement Review.Review
		throw new UnsupportedOperationException();
	}

}