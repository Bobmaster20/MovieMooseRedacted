package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Customer;
import beans.Employee;
import beans.MailingBean;
import beans.MovieList;
import beans.Order;
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

	public static ArrayList<String> currentlyHeldMovies(Connection conn, String SSN) throws SQLException {

		ArrayList<String> movieNames = new ArrayList<String>();

		String sql = "SELECT M.Name FROM Name N, Movie M, Rental R, Orders O WHERE N.AcctId = R.AccountId AND R.OrderId = O.Id AND M.Id = R.MovieId AND O.ReturnDate = NULL AND N.CustId = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String movieName = rs.getString("Name");
			movieNames.add(movieName);
		}

		return movieNames;

	}

	public static List<MailingBean> findMailing(Connection conn) throws SQLException {
		String sql = "SELECT  P.SSN, P.FirstName, P.LastName, P.Address, C.Email FROM    Customer C, Person P WHERE   C.Id = P.SSN";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<MailingBean> list = new ArrayList<MailingBean>();
		System.out.println(rs);
		while (rs.next()) {
			String ssn = rs.getString("ssn");
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String address = rs.getString("Address");
			String email = rs.getString("Email");
			MailingBean mailing = new MailingBean();
			mailing.setSSN(ssn);
			mailing.setFirstName(firstName);
			mailing.setLastName(lastName);
			mailing.setAddress(address);
			mailing.setEmail(email);
			list.add(mailing);
		}
		return list;

	}

	public static ArrayList<String> customerqueue(Connection conn, String SSN) throws SQLException {

		ArrayList<String> movieNames = new ArrayList<String>();

		String sql = "SELECT M.Name FROM Name N, MovieQ Q, Movie M WHERE N.AcctId = Q.AccountId AND Q.MovieId = M.Id AND N.CustID = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String movieName = rs.getString("Name");
			movieNames.add(movieName);
		}

		return movieNames;

	}

	public static ArrayList<Order> historyOrders(Connection conn, String SSN) throws SQLException {

		ArrayList<Order> orders = new ArrayList<Order>();

		String sql = "SELECT O.Id, R.MovieId, R.CustRepId, O.DateTime, O.ReturnDate FROM Name N, Rental R, Orders O WHERE N.AcctId = R.AccountId AND R.OrderId = O.Id AND N.CustId = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Order order = new Order();
			order.setOrderid(rs.getInt("Id"));
			order.setMovieid(rs.getInt("MovieId"));
			order.setCustrepid(rs.getInt("CustRepId"));
			order.setDatetime(rs.getString("DateTime"));
			order.setReturndate(rs.getString("ReturnDate"));
			orders.add(order);
		}

		return orders;

	}

	public static ArrayList<MovieList> bestseller(Connection conn) throws SQLException {

		ArrayList<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.Id, M.Name, M.Type, M.Rating, N.NumOrders FROM MovieOrder N, Movie M WHERE N.MovieId = M.Id ORDER BY N.NumOrders DESC";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			MovieList movie = new MovieList();
			movie.setId(rs.getInt("Id"));
			movie.setName(rs.getString("Name"));
			movie.setType(rs.getString("Type"));
			movie.setRating(rs.getInt("Rating"));
			movie.setNumOrders(rs.getInt("NumOrders"));
			list.add(movie);
		}

		return list;

	}

	public static ArrayList<MovieList> recommended(Connection conn, String ssn) throws SQLException {

		ArrayList<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M WHERE M.Type IN (SELECT O.MovieType FROM PastOrder O WHERE O.CustId = ?)  AND M.Id NOT IN (SELECT O.MovieId FROM PastOrder O WHERE O.CustId = ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, ssn);
		pstm.setString(2, ssn);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			MovieList movie = new MovieList();
			movie.setId(rs.getInt("Id"));
			movie.setName(rs.getString("Name"));
			movie.setType(rs.getString("Type"));
			list.add(movie);
		}

		return list;

	}

}
