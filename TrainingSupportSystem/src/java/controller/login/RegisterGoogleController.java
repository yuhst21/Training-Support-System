/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import common.Captcha;
import dal.SignUpDAO;
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
public class RegisterGoogleController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterGoogleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterGoogleController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/login/regisgoogle.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Captcha c = new Captcha();
    String rd = c.generateCaptcha();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        if (!pass.equals(repass)) {
            request.setAttribute("error", "Password do not match!");
            request.getRequestDispatcher("view/login/regisgoogle.jsp").forward(request, response);
//            response.sendRedirect("change");
        } else {
            HttpSession s = request.getSession();
//            GoogleDTO userToken =  (GoogleDTO) s.getAttribute("account");
            String name = (String) s.getAttribute("name");
            String email = (String) s.getAttribute("email");

//            UserDBContext us = new UserDBContext();                       
//            int userid = us.getIdByEmail(u.getEmail());
//            us.updatePass(pass, userid);
            SignUpDAO se = new SignUpDAO();

//            se.signup(userToken.getName(), userToken.getEmail(), "");
            se.signup(name, email, pass);
//            String rd = getRandom();
            rd = c.generateCaptcha();
            request.setAttribute("captcha", rd);
            request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
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
