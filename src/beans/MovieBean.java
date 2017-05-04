package beans;

public class MovieBean {
	private int ID;
	private String name;
	private String type;
	private int rating;
	public MovieBean(){
		
	}
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getRating() {
		return rating;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
