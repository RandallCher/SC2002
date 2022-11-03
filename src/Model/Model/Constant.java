package Model;

public class Constant {


	public enum Cineplex {
		CINELEISURE_ORCHARD("CINELEISURE_ORCHARD"),
		CAUSEWAY_POINT("CAUSEWAY_POINT"),
		AMK_HUB("AMK_HUB"),
		DOWNTOWN_EAST("DOWNTOWN_EAST"),
		WEST_MALL("WEST_MALL"),
		JEM("JEM"),
		PARKWAY_PARADE("PARKWAY_PARADE");

		;

		private String cineplex;

		/**
		 * 
		 * @param cineplex
		 */
		private Cineplex(String cineplex) {
			this.cineplex = cineplex;

			// TODO - implement Cineplex.Cineplex

		}

		public String toString() {
			// TODO - implement Cineplex.toString
			return cineplex;
		}

	}


	public enum AgeRestriction {
		G("G"),
		PG("PG"),
		PG13("PG13"),
		NC16("NC16"),
		M18("M18"),
		R21("R21")
		;

		private String movieRestriction;

		/**
		 * 
		 * @param movieRestriction
		 */
		private AgeRestriction(String movieRestriction) {
			// TODO - implement AgeRestriction.AgeRestriction
			this.movieRestriction = movieRestriction;
		}

		public String toString() {
			// TODO - implement AgeRestriction.toString
			return movieRestriction;
		}

	}


	public enum MovieStatus {
		COMING_SOON("Coming soon"),
		End_OF_SHOWING("End of showing"),
		NOW_SHOWING("Now showing");
		;

		private String status;

		/**
		 * 
		 * @param status
		 */
		MovieStatus(String status) {
			this.status = status;
			// TODO - implement MovieStatus.MovieStatus
		}

		public String toString() {
			// TODO - implement MovieStatus.toString
			return status;
		}

	}

}