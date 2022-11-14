/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.assignment;

import dal.AssignmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Assignment;
import model.Subject;

/**
 *
 * @author Admin
 */
public class AddAssignmentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddAssignmentServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAssignmentServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    AssignmentDAO a = new AssignmentDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        List<Subject>  list = a.getAllSubject();
        request.setAttribute("list", list);
        request.getRequestDispatcher("view/assignment/add_assignment.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int ass_id = a.countID()+1;
        int subject_id = Integer.parseInt(request.getParameter("subject"));
        String title = request.getParameter("title");
         String ass_body = request.getParameter("ass_body");
         double evalweight = Double.parseDouble(request.getParameter("evalweight"));
         boolean teamwork = Boolean.parseBoolean(request.getParameter("teamwork"));
         boolean ongoing = Boolean.parseBoolean(request.getParameter("ongoing"));
         boolean status = Boolean.parseBoolean(request.getParameter("status"));
         Subject s = new Subject(subject_id);
         a.insert(new Assignment(ass_id,s,title,ass_body,evalweight,teamwork,ongoing,status));
         response.sendRedirect("list_ass");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
