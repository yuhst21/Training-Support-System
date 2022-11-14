package common;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
public class UserVerify extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //feth form value
           String name = request.getParameter("username");
           String email = request.getParameter("useremail");
           
      		//create instance object of the SendEmail Class
           SendEmail sm = new SendEmail();
      		//get the 6-digit code
           String code = sm.getRandom();
           
      		//craete new user using all information
           User user = new User(name,email,code);
           
           //call the send email method
//           boolean test = sm.sendEmail(user);
           
      		//check if the email send successfully
//           if(test){
//               HttpSession session  = request.getSession();
//               session.setAttribute("authcode", user);
//               response.sendRedirect("verify.jsp");
//           }else{
//      		  out.println("Failed to send verification email");
//      	   }
            SendEmail se = new SendEmail();
            se.sendEmail(email);
           
        }
    }

   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}