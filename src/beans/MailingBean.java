package beans;

public class MailingBean {
	private String SSN;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	public MailingBean(){
		
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
	public String getEmail() {
		return email;
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
	public void setEmail(String email) {
		this.email = email;
	}
}
