package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Customer;
import beans.MailingBean;
import beans.MovieList;
import beans.Order;
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
		Customer test = MyUtils.getCustomer(request.getSession());
		String ssn = test.getId();
		ArrayList<String> movieNames = null;

		System.out.println(action);

		Person person = null;
		boolean hasError = false;
		String errorString = null;
		ArrayList<Order> orders = null;
		ArrayList<MovieList> movies = null;

		// Redirect to home

		// Valid SSN I guess.
		Connection conn = MyUtils.getStoredConnection(request);
		if (action != null) {
			if (action.equals("1")) {

				try {
					movieNames = DBUtils.currentlyHeldMovies(conn, ssn);
				} catch (SQLException e) {
					e.printStackTrace();
					hasError = true;
					errorString = e.getMessage();
				}
				request.setAttribute("movieNames", movieNames);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/customer_checkout.jsp");

				dispatcher.forward(request, response);
				return;
			}

			if (action.equals("2")) {
				try {
					movieNames = DBUtils.customerqueue(conn, ssn);
				} catch (SQLException e) {
					e.printStackTrace();
					hasError = true;
					errorString = e.getMessage();
				}
				request.setAttribute("movieQueue", movieNames);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/customer_wishlist.jsp");

				dispatcher.forward(request, response);
				return;
			}

			if (action.equals("3")) {
				try {
					orders = DBUtils.historyOrders(conn, ssn);
				} catch (SQLException e) {
					e.printStackTrace();
					hasError = true;
					errorString = e.getMessage();
				}
				request.setAttribute("Orders", orders);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/customer_history.jsp");

				dispatcher.forward(request, response);
				return;

			}

			if (action.equals("4")) {
				try {
					movies = DBUtils.bestseller(conn);
				} catch (SQLException e) {
					e.printStackTrace();
					hasError = true;
					errorString = e.getMessage();
				}
				request.setAttribute("Movies", movies);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/customer_best_selling_movies.jsp");

				dispatcher.forward(request, response);
				return;

			}

			if (action.equals("5")) {
				try {
					movies = DBUtils.recommended(conn, ssn);
				} catch (SQLException e) {
					e.printStackTrace();
					hasError = true;
					errorString = e.getMessage();
				}
				request.setAttribute("Movies", movies);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/customer_recommended.jsp");

				dispatcher.forward(request, response);
				return;

			}

		}

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
