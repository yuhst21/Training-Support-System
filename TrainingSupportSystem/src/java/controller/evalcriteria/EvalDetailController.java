/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.evalcriteria;

import dal.EvalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EvalCriteria;

/**
 *
 * @author Admin
 */
public class EvalDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet EvalDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EvalDetailController at " + request.getContextPath () + "</h1>");
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
    EvalDAO eval = new EvalDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String id_raw = request.getParameter("eval_id");
       try{
           int id = Integer.parseInt(id_raw);
           
           EvalCriteria e = eval.getEvalById(id);
           String subject = e.getAssignment().getSubject_id().getSubject_code();
           String eval_name = e.getCriteria_name();
           String assignment = e.getAssignment().getTitle();
           int eval_weight = e.getEval_weight();
           boolean team_work = e.getIs_team_eval();
           boolean status = e.getStatus();
           int max_loc = e.getMax_loc();
           String description = e.getDescription();

           
           
           
           request.setAttribute("id", id);
           
           request.setAttribute("subject", subject);
           request.setAttribute("eval_name", eval_name);
           request.setAttribute("assignment", assignment);
           request.setAttribute("eval_weight", eval_weight);
           request.setAttribute("teamwork", team_work);
           request.setAttribute("status", status);
           request.setAttribute("max_loc", max_loc);
           request.setAttribute("description", description);
           request.getRequestDispatcher("view/eval_criteria/eval_detail.jsp").forward(request, response);
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
        int eval_id = Integer.parseInt(request.getParameter("id")); 

        String evalname = request.getParameter("eval_name");
        
         String title = request.getParameter("title");
         String ass_body = request.getParameter("ass_body");
         int evalweight = Integer.parseInt(request.getParameter("eval_weight"));
         boolean teamwork = Boolean.parseBoolean(request.getParameter("teamwork"));
         String description = request.getParameter("description");
         int max_loc = Integer.parseInt(request.getParameter("max_loc"));
         boolean status = Boolean.parseBoolean(request.getParameter("status"));
        eval.Edit(evalname, teamwork, max_loc, status, description, evalweight, eval_id);
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
