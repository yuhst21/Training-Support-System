/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.evalcriteria;

import dal.AssignmentDAO;
import dal.EvalDAO;
import dal.TeamDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Assignment;
import model.Milestone;
import model.User;

/**
 *
 * @author Admin
 */
public class AddNewEvalController extends HttpServlet {
   
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
            out.println("<title>Servlet AddNewEval</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewEval at " + request.getContextPath () + "</h1>");
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
    TeamDAO tdb = new TeamDAO();
    AssignmentDAO adb =new AssignmentDAO();
    EvalDAO e = new EvalDAO();
    UserDAO dbUser = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        u = dbUser.get(u);
        int user_id= u.getUser_id();
        
        List<Assignment> listAssignment = adb.getAllByManager(user_id);
        
        
        request.setAttribute("listAssignment", listAssignment);
        request.getRequestDispatcher("view/eval_criteria/Addeval.jsp").forward(request, response);
        
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
        String ass_id = request.getParameter("assignment");
        String mile_id = request.getParameter("milestone");
        String eval_name = request.getParameter("eval_name");
        String teamwork = request.getParameter("teamwork");
        String max_loc = request.getParameter("max_loc");
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String eval_weight = request.getParameter("eval_weight");
        e.insertEval(ass_id, mile_id, eval_name, teamwork, max_loc, status, description, eval_weight);
        response.sendRedirect("listeval");
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
