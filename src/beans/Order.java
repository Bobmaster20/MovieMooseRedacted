package beans;

public class Order {

	private int orderid;
	private int movieid;
	private int custrepid;
	private String datetime;
	private String returndate;

	public Order() {
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public int getCustrepid() {
		return custrepid;
	}

	public void setCustrepid(int custrepid) {
		this.custrepid = custrepid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}
