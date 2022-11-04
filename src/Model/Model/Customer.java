package Model;
//do serializable
public class Customer {

	private boolean isSeniorCitizen;
	private String name;
	private String mobile;
	private String email;

	public String getName() {
		return this.name;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param name
	 * @param mobile
	 * @param email
	 * @param isSeniorCitizen
	 */
	public Customer(String name, String mobile, String email, boolean isSeniorCitizen) {

		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.isSeniorCitizen = isSeniorCitizen;
	}

	public boolean isSeniorCitizen() {
	
		return isSeniorCitizen;
	}

	/**
	 * 
	 * @param o
	 */
	public boolean equals(Object o) {
		// 
		//checks if the customer object exists
		if(o==null){
			return false;
		}
		if(this.getClass() != o.getClass()){
			return false;
		}
		
		Customer customer = (Customer) o;
		if (!name.equals(customer.name)){
			return false;
		} 
        if (!mobile.equals(customer.mobile)){
			return false;
		} 

		return true;
	}

	public int hashCode() {
		// 
		int result = name.hashCode();
		//The HashCode is computed as s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
		return result;
	}

}