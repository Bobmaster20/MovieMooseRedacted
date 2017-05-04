package servlet;

import java.io.IOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CustomerList;
import beans.Employee;
import beans.MailingBean;
import beans.MovieList;
import beans.Person;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/Manager" })
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Employee employee = MyUtils.getEmployee(request.getSession());

		System.out.println(action);

		Person person = null;
		boolean hasError = false;
		String errorString = null;

		person = MyUtils.getLoginedUser(request.getSession());
		System.out.println(person.getFirstName());
		System.out.println(person.getLastName());

		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("person", person);
		request.setAttribute("action", action);

		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("person", person);

		if (action == null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");

			dispatcher.forward(request, response);
			return;
			// List of movies
		} else if (action.compareTo("1") == 0) {
			Connection conn = MyUtils.getStoredConnection(request);
			List<MovieList> list = null;
			try {
				list = DBUtils.findMovies(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			request.setAttribute("movieList", list);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_movies.jsp");
			dispatcher.forward(request, response);
			return;
		} else if (action.compareTo("2") == 0) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_add_mov.jsp");

			dispatcher.forward(request, response);
			return;
		} else if (action.compareTo("3") == 0) {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				int id = Integer.parseInt(request.getParameter("id_txt"));
				String name = request.getParameter("name_txt");
				String type = request.getParameter("type_txt");
				int rating = Integer.parseInt(request.getParameter("rating_txt"));
				double distrfee = Double.parseDouble(request.getParameter("distrfee_txt"));
				int numcopies = Integer.parseInt(request.getParameter("num_copies_txt"));
				DBUtils.addMovie(conn, id, name, type, rating, distrfee, numcopies);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(action.compareTo("4") == 0){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_edit_mov.jsp");

			dispatcher.forward(request, response);
			return;
		}else if(action.compareTo("5") == 0){
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				int id = 0;
				if(request.getParameter("id_txt").compareTo("") != 0){
					id = Integer.parseInt(request.getParameter("id_txt"));
				}
				String name = request.getParameter("name_txt");
				String type = request.getParameter("type_txt");
				int rating = 0;
				if(request.getParameter("rating_txt").compareTo("") != 0){
					rating = Integer.parseInt(request.getParameter("rating_txt"));
				}
				double distrfee = 0;
				if(request.getParameter("distrfee_txt").compareTo("") != 0){
					distrfee = Double.parseDouble(request.getParameter("distrfee_txt"));
				}
				int numcopies = 0;
				if(request.getParameter("num_copies_txt").compareTo("") != 0){
					numcopies = Integer.parseInt(request.getParameter("num_copies_txt"));
				}
				DBUtils.editMovie(conn, id, name, type, rating, distrfee, numcopies);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(action.compareTo("6") == 0){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_delete_mov.jsp");

			dispatcher.forward(request, response);
			return;
		}else if(action.compareTo("7") == 0){
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				int id = Integer.parseInt(request.getParameter("id_txt"));
				DBUtils.deleteMovie(conn, id);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");
			dispatcher.forward(request, response);
			return;
			// Send to add employee page
		}else if(action.equals("9")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_add_emp.jsp");
			dispatcher.forward(request, response);
			return;
		// Insert Employee
		}else if(action.equals("10")){
			// Valid SSN I guess.
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				String SSN = request.getParameter("ssn_txt");
				String lastName = request.getParameter("last_name_txt");
				String firstName = request.getParameter("first_name_txt");
				String address = request.getParameter("address_txt");
				int zipCode = Integer.parseInt(request.getParameter("zipcode_txt"));
				String telephone = request.getParameter("telephone_txt");
				String email = request.getParameter("email_txt");
				double hourlyRate = Long.parseLong(request.getParameter("ccn_txt"));
				int id = Integer.parseInt(request.getParameter("id_txt"));
				DBUtils.addEmployee(conn, SSN, lastName, firstName, address, zipCode, telephone, id, new Date(), hourlyRate);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");
			dispatcher.forward(request, response);
			return;
			// Send to edit employee page
		}else if(action.equals("11")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_edit_emp.jsp");
			dispatcher.forward(request, response);
			return;
		// Update customer
		}else if(action.equals("12")){
			// Valid SSN I guess.
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				String SSN = request.getParameter("ssn_txt");
				String lastName = request.getParameter("last_name_txt");
				String firstName = request.getParameter("first_name_txt");
				String address = request.getParameter("address_txt");
				int zipCode = 0;
				if(request.getParameter("zipcode_txt").compareTo("") != 0){
					zipCode = Integer.parseInt(request.getParameter("zipcode_txt"));
				}
				String telephone = request.getParameter("telephone_txt");
				String email = request.getParameter("email_txt");
				double hourlyRate = 0;
				if(request.getParameter("ccn_txt").compareTo("") != 0){
					hourlyRate = Long.parseLong(request.getParameter("ccn_txt"));
				}
				int id = 0;
				if(request.getParameter("zipcode_txt").compareTo("") != 0){
					id = Integer.parseInt(request.getParameter("id_txt"));
				}
				DBUtils.editEmployee(conn, SSN, lastName, firstName, address, zipCode, telephone, id, hourlyRate);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");
			dispatcher.forward(request, response);
			return;
		// Send to delete customer
		}else if(action.equals("13")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/man_delete_emp.jsp");
			dispatcher.forward(request, response);
			return;
			// Delete user
		}else if(action.equals("14")){
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				String SSN = request.getParameter("ssn_txt");
				DBUtils.deleteEmployee(conn, SSN);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");

			dispatcher.forward(request, response);
			return;
		} else if (action.compareTo("8") == 0) {
			int id = 0;
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				id = DBUtils.cusrepMostTransactions(conn);

			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			request.setAttribute("empName", id);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/man_best_emp.jsp");
			dispatcher.forward(request, response);
			return;

		} else if (action.compareTo("16") == 0) {
			ArrayList<MovieList> movies = null;
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				movies = DBUtils.mostActiveRentedMovies(conn);

			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			request.setAttribute("movieList", movies);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/man_most_rented.jsp");
			dispatcher.forward(request, response);
			return;

		}

		else if (action.compareTo("17") == 0) {
			ArrayList<CustomerList> customer = null;
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				customer = DBUtils.mostActiveCustomers(conn);

			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			request.setAttribute("custList", customer);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/man_most_active.jsp");
			dispatcher.forward(request, response);
			return;

		}

       
       // Forward to /WEB-INF/views/homeView.jsp
       // (Users can not access directly into JSP pages placed in WEB-INF)
       RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");
        
       dispatcher.forward(request, response);
   }
 
   

		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
