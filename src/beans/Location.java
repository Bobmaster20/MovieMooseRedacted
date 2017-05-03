package beans;

public class Location {
	private int zipCode;
	private String city;
	private String state;
	public Location(){
		
	}
	public int getZipCode() {
		return zipCode;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
}
