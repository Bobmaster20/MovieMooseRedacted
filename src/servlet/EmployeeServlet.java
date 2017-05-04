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

import beans.Employee;
import beans.MailingBean;
import beans.Person;
import utils.DBUtils;
import utils.MyUtils;



@WebServlet(urlPatterns = { "/Employee" })
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeServlet() {
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
		if (action == null){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/employeeView.jsp");

			dispatcher.forward(request, response);
			return;
		// Send to place order
		}else if (action.equals("1")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/emp_place_order.jsp");

			dispatcher.forward(request, response);
			return;
		// Queries the mailing list
		}else if (action.equals("2")){
			// Valid SSN I guess.
			Connection conn = MyUtils.getStoredConnection(request);
			List<MailingBean> list = null;
			try {
				list = DBUtils.findMailing(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			request.setAttribute("mailingList", list);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/emp_cust_email.jsp");
			dispatcher.forward(request, response);
			return;
		// Inserts an Order and Rental
		}else if(action.equals("3")){
			// Valid SSN I guess.
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				int employeeID = employee.getId();
				int movieID = Integer.parseInt(request.getParameter("mov_id_txt"));
				int accountID = Integer.parseInt(request.getParameter("acc_id_txt"));
				DBUtils.placeOrder(conn, movieID, accountID, employeeID);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/employeeView.jsp");
<<<<<<< HEAD
			dispatcher.forward(request, response);
			return;
		// Send to add customer page
		}else if(action.equals("4")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/emp_add_cust.jsp");
			dispatcher.forward(request, response);
			return;
		// Insert Customer
		}else if(action.equals("5")){
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
				long creditCard = Long.parseLong(request.getParameter("ccn_txt"));
				DBUtils.addCustomer(conn, SSN, lastName, firstName, address, zipCode, telephone, email, creditCard);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/employeeView.jsp");
			dispatcher.forward(request, response);
			return;
		// Send to edit customer page
		}else if(action.equals("6")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/emp_edit_cust.jsp");
			dispatcher.forward(request, response);
			return;
		// Update customer
		}else if(action.equals("7")){
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
				long creditCard = 0;
				if(request.getParameter("ccn_txt").compareTo("") != 0){
					creditCard = Long.parseLong(request.getParameter("ccn_txt"));
				}
				DBUtils.editCustomer(conn, SSN, lastName, firstName, address, zipCode, telephone, email, creditCard);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/employeeView.jsp");
			dispatcher.forward(request, response);
			return;
		// Send to delete customer
		}else if(action.equals("8")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/emp_delete_cust.jsp");
			dispatcher.forward(request, response);
			return;
		// Delete user
		}else if(action.equals("9")){
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				String SSN = request.getParameter("ssn_txt");
				DBUtils.deleteCustomer(conn, SSN);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/employeeView.jsp");
=======
>>>>>>> 25edc8ed6914e6f56bf353f05319d36371f4a4bf
			dispatcher.forward(request, response);
			return;
		}
		// Forward to /WEB-INF/views/homeView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/employeeView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
