package Model;

import Model.Parameters.AgeGroup;

/**
 *  This class get details about a customer's booking
 **/ 

//do serializable
public class Customer implements SerializableModel {

	private String name;
	private String mobile;
	private String email;
	private AgeGroup ageGroup;

	public void setCustomerStatus(AgeGroup ageGroup){
		this.ageGroup = ageGroup;
	}

	public String getName() {
		return this.name;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getEmail() {
		return this.email;
	}
	public AgeGroup getAgeGroup(){
		return this.ageGroup;
	}

	/**
	 * 
	 * @param name
	 * @param mobile
	 * @param email
	 * 
	 */
	public Customer(String name, String mobile, String email,AgeGroup ageGroup) {

		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.ageGroup = ageGroup;
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


}