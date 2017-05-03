package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Customer;
import beans.Employee;
import beans.Person;

public class DBUtils {

	public static Person findPerson(Connection conn, String SSN) throws SQLException {

		String sql = "Select P.FirstName, P.LastName, P.Address, P.ZipCode, P.Telephone from Person P "
				+ " where P.SSN = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Person person = new Person();
			person.setSSN(SSN);
			person.setFirstName(rs.getString("FirstName"));
			person.setLastName(rs.getString("LastName"));
			person.setAddress(rs.getString("Address"));
			person.setZipCode(rs.getInt("ZipCode"));
			person.setAddress(rs.getString("Telephone"));
			return person;
		}
		return null;
	}

	public static Customer findCustomer(Connection conn, String SSN) throws SQLException {

		String sql = "Select C.Email, C.Rating, C.CreditCardNumber from Customer C " + " where C.ID = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Customer customer = new Customer();
			customer.setId(SSN);
			customer.setEmail(rs.getString("Email"));
			customer.setRating(rs.getInt("Rating"));
			customer.setCreditcardnumber(rs.getLong("CreditCardNumber"));
			return customer;
		}

		return null;

	}

	public static Employee findEmployee(Connection conn, String SSN) throws SQLException {

		String sql = "Select E.ID, E.StartDate, E.HourlyRate from Employee E " + " where E.SSN = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Employee employee = new Employee();
			employee.setHourlyrate(rs.getDouble("HourlyRate"));
			employee.setId(rs.getInt("ID"));
			employee.setStartDate(rs.getDate("StartDate"));
			employee.setSsn(SSN);
			return employee;
		}

		return null;

	}

}
