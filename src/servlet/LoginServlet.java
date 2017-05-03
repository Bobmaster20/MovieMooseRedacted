package servlet;

import java.io.IOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Customer;
import beans.Employee;
import beans.Person;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ssn = request.getParameter("ssn_text");

		Customer customer = null;
		Employee employee = null;
		Person person = null;
		boolean hasError = false;
		String errorString = null;

		Connection conn = MyUtils.getStoredConnection(request);
		try {
			person = DBUtils.findPerson(conn, ssn);
			customer = DBUtils.findCustomer(conn, ssn);
			employee = DBUtils.findEmployee(conn, ssn);

			if (customer == null && employee == null) {
				hasError = true;
				errorString = "Customer not in database!";
				response.sendRedirect("http://localhost:8080/MovieMoose/");
			} else if (customer != null) {
				
				MyUtils.storeLoginedUser(request.getSession(), person);
				MyUtils.storeCustomer(request.getSession(), customer);

				response.sendRedirect("http://localhost:8080/MovieMoose/Customer");

			} else if (employee != null) {
				
				MyUtils.storeLoginedUser(request.getSession(), person);
				MyUtils.storeEmployee(request.getSession(), employee);

				response.sendRedirect("http://localhost:8080/MovieMoose/Employee");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			hasError = true;
			errorString = e.getMessage();
		}

		// Store info in request attribute, before forward to views

		// Forward to /WEB-INF/views/homeView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
