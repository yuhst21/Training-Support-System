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
public class EditAssignmentController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditAssignmentController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAssignmentController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    AssignmentDAO a = new AssignmentDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String id_raw = request.getParameter("ass_id");
       try{
           int id = Integer.parseInt(id_raw);
           
           Assignment ass = a.getAssignmentById(id);
           String ass_body = ass.getAss_body();
           String title = ass.getTitle();
           double evalweight = ass.getEval_weight();
           boolean teamwork = ass.isIs_team_work();
           boolean ongoing = ass.isIs_ongoing();
           boolean status = ass.isStatus();
//           String subject = ass.getSubject_id().getSubject_code();
           int subjectid = ass.getSubject_id().getSubject_id();
           String subject = a.getCodeById(subjectid);
           List<Subject>  list = a.getAllSubject();
           request.setAttribute("id", id);
           request.setAttribute("list", list);
           request.setAttribute("assbody", ass_body);
           request.setAttribute("title", title);
           request.setAttribute("evalweight", evalweight);
           request.setAttribute("ongoing", ongoing);
           request.setAttribute("status", status);
           request.setAttribute("subject", subject);
           request.setAttribute("teamwork", teamwork);
           request.getRequestDispatcher("view/assignment/edit_ass.jsp").forward(request, response);
       }catch(Exception e){
           System.out.println(e);
       }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         int ass_id = Integer.parseInt(request.getParameter("id")); 
//         int subject_id = Integer.parseInt(request.getParameter("subject"));
        String subject = request.getParameter("subject");
        int subject_id = a.getIdByCode(subject);
         String title = request.getParameter("title");
         String ass_body = request.getParameter("ass_body");
         double evalweight = Double.parseDouble(request.getParameter("evalweight"));
         boolean teamwork = Boolean.parseBoolean(request.getParameter("teamwork"));
         boolean ongoing = Boolean.parseBoolean(request.getParameter("ongoing"));
         boolean status = Boolean.parseBoolean(request.getParameter("status"));
         a.Edit(new Assignment(ass_id,new Subject(subject_id),title,ass_body,evalweight,teamwork,ongoing,status));
         response.sendRedirect("list_ass");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
