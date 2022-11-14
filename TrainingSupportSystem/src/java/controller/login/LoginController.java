/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import common.Captcha;
import dal.LoginDAO;
import dal.UserDAO;
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
public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    LoginDAO d = new LoginDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get parameter
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        String captchagg = request.getParameter("g-recaptcha-response");

        
        
        //check user and pass are correct and return user       
        User a = d.check(email, pass);
        Boolean test= a.isStatus();
     
            if (a == null) {
            request.setAttribute("email", email);
            request.setAttribute("pass", pass);
            request.setAttribute("error", "Wrong email or password ");
            rd = c.generateCaptcha();
            request.setAttribute("captcha", rd);
            request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
        } else {
            //wrong captcha
            
            if (captchagg.equals("")) {
                request.setAttribute("email", email);
                request.setAttribute("pass", pass);
                request.setAttribute("error2", "You must input captcha");
                rd = c.generateCaptcha();
                request.setAttribute("captcha", rd);
                request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
            } else {
                //All are correct and return home page
                if(d.checkRole(a.getUser_id())==null){
                    d.insertRollNew(a.getUser_id(), 15);
                }
                HttpSession session = request.getSession();
                UserDAO udb = new UserDAO();
                a = udb.get(a);
                session.setAttribute("account", a);
                session.setAttribute("user", a);
                int role=a.getList().get(0).getSetting_id();
                
                request.getSession().setAttribute("role", role);
                response.sendRedirect("homepage");
            }

        
        }
        //wrong email or password
        

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

}
