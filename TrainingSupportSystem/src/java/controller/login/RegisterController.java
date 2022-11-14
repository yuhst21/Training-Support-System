/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import common.Captcha;
import common.SendEmail;
import dal.SignUpDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import model.User;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //generate captcha
    Captcha c = new Captcha();
    String rd = c.generateCaptcha();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("captcha", rd);
        request.getRequestDispatcher("view/login/signup.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //get parameter from jsp
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String captcha = request.getParameter("captcha");
        SignUpDAO s = new SignUpDAO();
        //check duplicate email anh return a user
        User a = s.checkUser(email);
        if (a != null) {
            //email duplicated
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("error2", "Email Duplicated!");
            rd = c.generateCaptcha();
            request.setAttribute("captcha", rd);
            request.getRequestDispatcher("view/login/signup.jsp").forward(request, response);
        }
        if (!captcha.equals(rd)) {
            //wrong captcha
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("error", "Wrong captcha");
            rd = c.generateCaptcha();
            request.setAttribute("captcha", rd);
            request.getRequestDispatcher("view/login/signup.jsp").forward(request, response);
        } else {
            SendEmail sm = new SendEmail();
            //get the 6-digit code
//            String code = sm.getRandom();

            //craete new user using all information
//            User user = new User(name, email, code);
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
            String rd = sm.sendEmail(email);
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("rd", rd);
            request.getRequestDispatcher("view/login/email.jsp").forward(request, response);

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}
