/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contact;

import common.EmailAutoSend;
import dal.CategoryDAO;
import dal.WebContactDAO;
import model.Category;
import model.WebContact;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author win
 */
public class ContactController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    CategoryDAO cDB = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cate", cDB.list());
        request.getRequestDispatcher("view/landing/contact.jsp").forward(request, response);
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
        request.setAttribute("cate", cDB.list());
        String raw_name = request.getParameter("name");
        String raw_category_id = request.getParameter("category");
        String raw_email = request.getParameter("email");
        String raw_mobile = request.getParameter("mobile");
        String raw_message = request.getParameter("message");
        // validate
        Integer category = (raw_category_id != null && raw_category_id.length() > 0) ? new Integer(raw_category_id) : null;
        String name = (raw_name != null && raw_name.length() > 0) ? raw_name : null;
        String email = (raw_email != null && raw_email.length() > 0) ? raw_email : null;
        String mobile = (raw_mobile != null && raw_mobile.length() > 0) ? raw_mobile : null;
        String message = (raw_message != null && raw_message.length() > 0) ? raw_message : null;
        WebContact wc = new WebContact();
        WebContactDAO wdb = new WebContactDAO();
        wc.setName(name);
        Category c = new Category();
        c.setCategory_id(category);
        wc.setCategory(c);
        wc.setEmail(email);
        wc.setMobile(mobile);
        wc.setMessage(message);
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now);
        wc.setDateSend(date);
        wdb.insert(wc);
        EmailAutoSend eas = new EmailAutoSend();
        eas.sendEmail(email);
        response.sendRedirect("contact");
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
