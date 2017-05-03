package beans;

public class Customer {

	private String id;
	private String email;
	private int rating;
	private long creditcardnumber;

	public Customer() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getCreditcardnumber() {
		return creditcardnumber;
	}

	public void setCreditcardnumber(long creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
	}

}
