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
		}else if (action.equals("1")){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/emp_place_order.jsp");

			dispatcher.forward(request, response);
			return;
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
