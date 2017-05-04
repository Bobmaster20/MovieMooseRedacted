package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import beans.Customer;
import beans.CustomerList;
import beans.Employee;
import beans.MailingBean;
import beans.MovieList;
import beans.MovieRental;
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

		String sql = "SELECT M.Name FROM Name N, Movie M, Rental R, Orders O WHERE N.AcctId = R.AccountId AND R.OrderId = O.Id AND M.Id = R.MovieId AND O.ReturnDate IS NULL AND N.CustId = ?";

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

	public static List<MovieList> recommended(Connection conn, String ssn) throws SQLException {

		List<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M WHERE M.Type IN (SELECT O.MovieType FROM PastOrder O WHERE O.CustId = ?) AND M.Id NOT IN (SELECT O.MovieId FROM PastOrder O WHERE O.CustId = ?)";
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

	public static void placeOrder(Connection conn, int movieID, int accountID, int employeeID) throws SQLException {
		String sql = "SELECT Id from orders  ORDER BY id DESC LIMIT 1";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		int orderID = 0;
		if (rs.next()) {
			orderID = rs.getInt("id");
			orderID++;
		}

		sql = "INSERT INTO Orders (Id, DateTime, ReturnDate) VALUES (?, ?,NULL)";

		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, orderID);
		Date today = new Date();
		pstm.setTimestamp(2, new Timestamp(today.getTime()));
		System.out.println(new Timestamp(today.getTime()));
		int success = pstm.executeUpdate();

		if (success != 0) {
			sql = "INSERT INTO Rental (AccountId, CustRepId, OrderId, MovieId) VALUES (?, ?, ?, ?)";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, accountID);
			pstm.setInt(2, employeeID);
			pstm.setInt(3, orderID);
			pstm.setInt(4, movieID);
			success = pstm.executeUpdate();
			if (success != 0) {

			}
		}
	}

	public static void addCustomer(Connection conn, String SSN, String lastName, String firstName, String address,
			int zipCode, String telephone, String email, long creditCard) throws SQLException {
		String sql = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		pstm.setString(2, lastName);
		pstm.setString(3, firstName);
		pstm.setString(4, address);
		pstm.setInt(5, zipCode);
		pstm.setString(6, telephone);
		int success = pstm.executeUpdate();
		if (success != 0) {
			sql = "INSERT INTO Customer VALUES (?, ?, 1, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, SSN);
			pstm.setString(2, email);
			pstm.setLong(3, creditCard);
			success = pstm.executeUpdate();
		}

	}

	public static void editCustomer(Connection conn, String SSN, String lastName, String firstName, String address,
			int zipCode, String telephone, String email, long creditCard) throws SQLException {
		if (SSN.compareTo("") == 0) {
			return;
		}
		if (lastName.compareTo("") != 0) {
			String sql = "UPDATE Person SET lastName = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, lastName);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (firstName.compareTo("") != 0) {
			String sql = "UPDATE Person SET firstName = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, firstName);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (address.compareTo("") != 0) {
			String sql = "UPDATE Person SET address = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, address);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (zipCode != 0) {
			String sql = "UPDATE Person SET zipCode = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, zipCode);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (telephone.compareTo("") != 0) {
			String sql = "UPDATE Person SET telephone = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, telephone);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (email.compareTo("") != 0) {
			String sql = "UPDATE Customer SET email = ? WHERE ID = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (creditCard != 0) {
			String sql = "UPDATE Customer SET creditCard = ? WHERE ID = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, creditCard);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

	}

	public static void deleteCustomer(Connection conn, String SSN) throws SQLException {
		if (SSN.compareTo("") == 0) {
			return;
		}

		String sql = "Delete From Customer Where ID = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		int success = pstm.executeUpdate();
		if (success == 0) {
			return;
		}

		sql = "Delete From Person Where SSN = ?";

		pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		success = pstm.executeUpdate();
		if (success == 0) {
			return;
		}

	}

	// ------------
	public static void addEmployee(Connection conn, String SSN, String lastName, String firstName, String address,
			int zipCode, String telephone, int id, Date startDate, Double hourlyRate) throws SQLException {
		String sql = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		pstm.setString(2, lastName);
		pstm.setString(3, firstName);
		pstm.setString(4, address);
		pstm.setInt(5, zipCode);
		pstm.setString(6, telephone);
		int success = pstm.executeUpdate();
		if (success != 0) {
			sql = "INSERT INTO Employee VALUES (?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, SSN);
			pstm.setDate(3, (java.sql.Date) startDate);
			pstm.setDouble(4, hourlyRate);
			success = pstm.executeUpdate();
		}

	}

	public static void editEmployee(Connection conn, String SSN, String lastName, String firstName, String address,
			int zipCode, String telephone, int id, Date startDate, Double hourlyRate) throws SQLException {
		if (SSN.compareTo("") == 0) {
			return;
		}
		if (lastName.compareTo("") != 0) {
			String sql = "UPDATE Person SET lastName = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, lastName);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (firstName.compareTo("") != 0) {
			String sql = "UPDATE Person SET firstName = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, firstName);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (address.compareTo("") != 0) {
			String sql = "UPDATE Person SET address = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, address);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (zipCode != 0) {
			String sql = "UPDATE Person SET zipCode = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, zipCode);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (telephone.compareTo("") != 0) {
			String sql = "UPDATE Person SET telephone = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, telephone);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (id != 0) {
			String sql = "UPDATE Employee SET ID = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (startDate != null) {
			String sql = "UPDATE Employee SET StartDate = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setDate(1, (java.sql.Date) startDate);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (hourlyRate != 0.0) {
			String sql = "UPDATE Employee SET hourlyRate = ? WHERE SSN = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setDouble(1, hourlyRate);
			pstm.setString(2, SSN);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

	}

	public static void deleteEmployee(Connection conn, String SSN) throws SQLException {
		if (SSN.compareTo("") == 0) {
			return;
		}

		String sql = "Delete From Employee Where SSN = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		int success = pstm.executeUpdate();
		if (success == 0) {
			return;
		}

		sql = "Delete From Person Where SSN = ?";

		pstm = conn.prepareStatement(sql);
		pstm.setString(1, SSN);
		success = pstm.executeUpdate();
		if (success == 0) {
			return;
		}

	}
	// --

	public static void addMovie(Connection conn, int id, String name, String type, int rating, double distrfee,
			int numcopies) throws SQLException {
		String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		pstm.setString(2, name);
		pstm.setString(3, type);
		pstm.setInt(4, rating);
		pstm.setDouble(5, distrfee);
		pstm.setInt(6, numcopies);
		int success = pstm.executeUpdate();

	}

	public static void editMovie(Connection conn, int id, String name, String type, int rating, double distrfee,
			int numcopies) throws SQLException {
		if (id != 0) {
			return;
		}
		if (name.compareTo("") != 0) {
			String sql = "UPDATE Movie SET Name = ? WHERE Id = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, id);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (type.compareTo("") != 0) {
			String sql = "UPDATE Movie SET Type = ? WHERE Id = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, type);
			pstm.setInt(2, id);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (rating != 0) {
			String sql = "UPDATE Movie SET Rating = ? WHERE Id = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, rating);
			pstm.setInt(2, id);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (distrfee != 0.0) {
			String sql = "UPDATE Movie SET DistrFee = ? WHERE Id = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setDouble(1, distrfee);
			pstm.setInt(2, id);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

		if (numcopies != 0) {
			String sql = "UPDATE Movie SET NumCopies = ? WHERE Id = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, numcopies);
			pstm.setInt(2, id);
			int success = pstm.executeUpdate();
			if (success == 0) {
				return;
			}
		}

	}

	public static void deleteMovie(Connection conn, int id) throws SQLException {
		if (id != 0) {
			return;
		}

		String sql = "Delete From Movie Where Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		int success = pstm.executeUpdate();
		if (success == 0) {
			return;
		}

	}

	public static ArrayList<MovieRental> getListMovieRental(Connection conn, String name, String type, String cusname)
			throws SQLException {

		ArrayList<MovieRental> list = new ArrayList<MovieRental>();

		if (name != null) {
			String sql = "SELECT R.OrderId, M.Id, M.Type, N.CustId FROM Movie M, Rental R, Name N WHERE N.AcctId = R.AccountId AND M.Id = R.MovieId AND M.Name = ? ";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				MovieRental rental = new MovieRental();
				rental.setCustid(rs.getInt("CustId"));
				rental.setMovieid(rs.getInt("Id"));
				rental.setOrderid(rs.getInt("OrderId"));
				rental.setType(rs.getString("Type"));
				list.add(rental);
			}
		}

		else if (type != null) {
			String sql = "SELECT R.OrderId, M.Id, M.Type, N.CustId FROM Movie M, Rental R, Name N WHERE N.AcctId = R.AccountId AND M.Id = R.MovieId AND M.Type = ? ";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, type);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				MovieRental rental = new MovieRental();
				rental.setCustid(rs.getInt("CustId"));
				rental.setMovieid(rs.getInt("Id"));
				rental.setOrderid(rs.getInt("OrderId"));
				rental.setType(rs.getString("Type"));
				list.add(rental);
			}
		}

		else if (cusname != null) {
			String sql = "SELECT R.OrderId, M.Id, M.Type, N.CustId FROM Movie M, Rental R, Name N WHERE N.AcctId = R.AccountId AND M.Id = R.MovieId AND N.LastName = ? ";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cusname);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				MovieRental rental = new MovieRental();
				rental.setCustid(rs.getInt("CustId"));
				rental.setMovieid(rs.getInt("Id"));
				rental.setOrderid(rs.getInt("OrderId"));
				rental.setType(rs.getString("Type"));
				list.add(rental);
			}
		}

		return list;

	}

	public static int cusrepMostTransactions(Connection conn) throws SQLException {

		String sql = "SELECT C.CustRepId FROM CountTrans C WHERE C.NumTrans >= (SELECT MAX(D.NumTrans) FROM CountTrans D)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return rs.getInt("CustRepId");
		}
		return 0;

	}

	public static ArrayList<CustomerList> mostActiveCustomers(Connection conn) throws SQLException {
		ArrayList<CustomerList> list = new ArrayList<CustomerList>();

		String sql = "SELECT N.CustId, N.FirstName, N.LastName, N.Rating, C.NumOrders FROM CountOrders C, Name N WHERE N.CustId = C.CustId AND C.NumOrders >= (SELECT MAX(D.NumOrders)  FROM CountOrders D) ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			CustomerList cus = new CustomerList();
			cus.setCustid(rs.getInt("CustId"));
			cus.setFirstname(rs.getString("FirstName"));
			cus.setLastname(rs.getString("LastName"));
			cus.setRating(rs.getInt("Rating"));
			cus.setNumOrders(rs.getInt("NumOrders"));
			list.add(cus);
		}
		return list;

	}

	public static ArrayList<MovieList> mostActiveRentedMovies(Connection conn) throws SQLException {
		ArrayList<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.ID, M.Name, M.RATING, O.NumOrders FROM MovieOrder O, Movie M WHERE O.MovieId = M.ID AND O.NumOrders >= (SELECT MAX(R.NumOrders) FROM MovieOrder R) ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			MovieList cus = new MovieList();
			cus.setId(rs.getInt("Id"));
			cus.setName(rs.getString("Name"));
			cus.setRating(rs.getInt("RATING"));
			cus.setNumOrders(rs.getInt("NumOrders"));

			list.add(cus);
		}
		return list;

	}

	public static void editAccount(Connection conn, String id, String type, String ssn) throws SQLException {

		if (type != null) {
			if (!type.equals("")) {
				System.out.println(ssn);
				System.out.println(type);
				String sql = "UPDATE Account SET AccType= ?" + "WHERE Customer = ?";

				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, type);
				pstm.setString(2, ssn);
				pstm.executeUpdate();
			}
		}

		if (id != null) {
			if (id.equals("")) {
				System.out.println(ssn);
				System.out.println(id);
				String sql = "UPDATE Person SET SSN = ?" + "WHERE SSN = ?";

				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, id);
				pstm.setString(2, ssn);
				pstm.executeUpdate();
			}

		}

	}

	public static ArrayList<MovieList> searchGenre(Connection conn, String genre) throws SQLException {
		System.out.println(genre);
		ArrayList<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M, HandOut H WHERE M.NumCopies>H.NumOut AND M.Type = ? "
				+ "AND M.ID = H.MovieId";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, genre);
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

	public static ArrayList<MovieList> searchKeyword(Connection conn, String keyword) throws SQLException {
		String newkeyword = "";
		if (keyword != null) {
			String[] keyword_split = keyword.split(" ");
			if (keyword_split.length > 0) {
				for (int i = 0; i < keyword_split.length; i++) {
					if (i != keyword_split.length - 1) {
						newkeyword = newkeyword + "%" + keyword_split[i];
					} else {
						newkeyword = newkeyword + "%" + keyword_split[i] + "%";
					}

				}
				System.out.println(newkeyword);
			}
		}

		ArrayList<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M, HandOut H WHERE M.Id = H.MovieId AND M.NumCopies>H.NumOut AND M.Name LIKE ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, newkeyword);
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

	public static ArrayList<MovieList> searchActors(Connection conn, String actors) throws SQLException {
		String newkeyword = "";
		if (actors != null) {
			String[] keyword_split = actors.split(",");
			if (keyword_split.length > 0) {
				for (int i = 0; i < keyword_split.length; i++) {
					if (i != keyword_split.length - 1) {
						newkeyword = newkeyword + keyword_split[i] + " OR ";
					} else {
						newkeyword = newkeyword + keyword_split[i];
					}

				}
				System.out.println(newkeyword);
			}
		}

		ArrayList<MovieList> list = new ArrayList<MovieList>();

		String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M, HandOut H, AppearedIn I, ACTOR A WHERE M.Id = H.MovieID AND M.NumCopies>H.NumOut AND A.NAME = ? "
				+ "AND I.ActorId = A.Id AND I.MovieId = M.Id ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, newkeyword);
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

	public static List<MovieList> findMovies(Connection conn) throws SQLException {
		String sql = "SELECT Id, Name, Type, Rating FROM Movie";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<MovieList> list = new ArrayList<MovieList>();
		System.out.println(rs);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String type = rs.getString("Type");
			int rating = rs.getInt("Rating");
			MovieList movie = new MovieList();
			movie.setId(id);
			movie.setName(name);
			movie.setType(type);
			movie.setRating(rating);
			list.add(movie);
		}
		return list;

	}

}