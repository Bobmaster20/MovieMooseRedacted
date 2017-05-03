package beans;

public class Person {
	private String SSN;
	private String firstName;
	private String lastName;
	private String address;
	private int zipCode;
	private String telephone;
	public Person(){
		
	}
	public String getSSN() {
		return SSN;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public int getZipCode() {
		return zipCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
