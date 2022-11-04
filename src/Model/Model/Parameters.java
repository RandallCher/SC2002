package Model;


public class Parameters {


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

		}

		public String Query() {
			return this.cineplex;
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
			this.movieRestriction = movieRestriction;
		}

		public String Query() {
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
		}
		public String Query(){
			return this.status;
		}



	}

}