/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.evalcriteria;

import dal.AssignmentDAO;
import dal.EvalDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Assignment;
import model.EvalCriteria;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
public class ListEvalController extends HttpServlet {
   
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
            out.println("<title>Servlet ListEvalController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListEvalController at " + request.getContextPath () + "</h1>");
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
    AssignmentDAO adb = new AssignmentDAO();
     UserDAO dbUser = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        u = dbUser.get(u);
        int user_id= u.getUser_id();
        
        String subject = request.getParameter("subject");
        
        
        if(subject==null){
            
            subject ="1";
        }
        
        List<Assignment> listAssignment = eval.getAllAssignmentBySubject(subject);
        List<Subject> listSubject = adb.getAllSubjectByManager(user_id);
        
        String assignment = request.getParameter("assignment");
        if(assignment==null){
            assignment ="";
        }
        String key = request.getParameter("key");
        if(key==null){
            key ="";
        }
        String status = request.getParameter("status");
        if(status==null){
            status="";
        }
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_status = arr[1];
            eval.changeStatus(Integer.parseInt(id), raw_status);
        }
        
        List<EvalCriteria> listEval = eval.Search(key, subject, status, assignment);
        int page,numperpage = 6;
        int size =listEval.size();
        int num = (size%6==0?(size/6):((size/6))+1);
        String xpage = request.getParameter("page");
        if(xpage == null){
            page =1;
        }else{
            page = Integer.parseInt(xpage);
        }
        int start,end;
        start = (page-1)*numperpage;
        end = Math.min(page*numperpage, size);
        List<EvalCriteria> listEval1 = eval.getListByPage(listEval, start, end);
        request.setAttribute("listeval", key);
               
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("key", key);
        request.setAttribute("status", status);
        request.setAttribute("assignment1", assignment);
        request.setAttribute("subject", subject);
        request.setAttribute("listeval", listEval1);
        request.setAttribute("listAssignment", listAssignment);
        request.setAttribute("listSubject", listSubject);
        request.getRequestDispatcher("view/eval_criteria/listeval.jsp").forward(request, response);
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
        String subject_id = request.getParameter("subject_id");
        List<Assignment> listAss2 = eval.getAllAssignmentBySubject(subject_id);
        request.setAttribute("listassbysubject", listAss2);
        for(Assignment a : listAss2){
            response.getWriter().print("  <option  value=\""+a.getAss_id()+"\" > "+a.getTitle()+"</option>");
        }
        
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
