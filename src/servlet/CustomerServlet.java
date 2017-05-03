package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import beans.Person;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/Customer" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String ssn = request.getParameter("ssn_text");
		Customer test = MyUtils.getCustomer(request.getSession());

		System.out.println(action);
		System.out.println(ssn);
		if (ssn == null) {
			ssn = test.getId();
		}
		System.out.println(test);

		Person person = null;
		boolean hasError = false;
		String errorString = null;

		// Redirect to home
		if (ssn == null || ssn.length() != 11) {
			response.sendRedirect("http://localhost:8080/MovieMoose/");
			return;
		} else {
			// Valid SSN I guess.
			Connection conn = MyUtils.getStoredConnection(request);
			try {

				person = DBUtils.findPerson(conn, ssn);

				if (person == null) {
					hasError = true;
					errorString = "SSN invalid";
				} else {
					System.out.println(person.getFirstName());
					System.out.println(person.getLastName());
					MyUtils.storeLoginedUser(request.getSession(), person);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}

		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("person", person);
		request.setAttribute("action", action);
		request.setAttribute("ssn", ssn);
		// Forward to /WEB-INF/views/homeView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/customerView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
