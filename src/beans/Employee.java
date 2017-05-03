package beans;

import java.util.Date;

public class Employee {

	private int id;
	private String ssn;
	private Date startDate;
	private double hourlyrate;

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getHourlyrate() {
		return hourlyrate;
	}

	public void setHourlyrate(double hourlyrate) {
		this.hourlyrate = hourlyrate;
	}

}
