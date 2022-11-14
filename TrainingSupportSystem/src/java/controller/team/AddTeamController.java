/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.team;

import dal.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Class;
import model.Milestone;
import model.Team;

/**
 *
 * @author Admin
 */
public class AddTeamController extends HttpServlet {
   
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
            out.println("<title>Servlet AddTeamController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddTeamController at " + request.getContextPath () + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Milestone> list2 = tdb.getAllMileStone();
       List<Class> list = tdb.getAllClass(1);
       request.setAttribute("listClass", list);
       request.setAttribute("listMile", list2);
       request.getRequestDispatcher("view/team/add_team.jsp").forward(request, response);
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
        int team_id = tdb.count()+1;
        int class_id = Integer.parseInt(request.getParameter("class_id"));
        String team_code =  request.getParameter("team_code");
        String topic_name =  request.getParameter("topic_name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String description = request.getParameter("description");
        String milestone = request.getParameter("milestone");
        Class c = new Class();
        c.setClass_id(class_id);
        Team t = new Team();
        t.setTeam_id(team_id);
        t.setClass_id(c);
        t.setTeam_code(team_code);
        t.setStatus(status);
        t.setDescription(description);
        t.setTopic_name(topic_name);
        tdb.insert(t);
        tdb.insertsubmitfor1team(team_id, class_id, milestone);
        request.getRequestDispatcher("view/team/team_list.jsp").forward(request, response);
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
