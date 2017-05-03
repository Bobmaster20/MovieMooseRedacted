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
	   String test = request.getParameter("action");
	   String ssn = request.getParameter("ssn_text");
       System.out.println(test);
       
       Person person = null;
       boolean hasError = false;
       String errorString = null;
       
       if(test == null && ssn.length() != 11){
    	   
       }else {
           Connection conn = MyUtils.getStoredConnection(request);
           try {
             
        	   person = DBUtils.findPerson(conn, ssn);
                
               if (person == null) {
                   hasError = true;
                   errorString = "User Name or password invalid";
               }
               else{
            	   System.out.println(person.getFirstName());
            	   System.out.println(person.getLastName());
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
       request.setAttribute("action", test);
       
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
