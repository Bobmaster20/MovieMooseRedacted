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

import beans.Employee;
import beans.Person;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/Manager"})
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
       
       if (action == null){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/managerView.jsp");

			dispatcher.forward(request, response);
			return;
		// Send to place order
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
