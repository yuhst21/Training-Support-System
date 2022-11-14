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
import model.Team;
import model.Team_member;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author Admin
 */
public class TeamDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet TeamDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeamDetailController at " + request.getContextPath () + "</h1>");
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
    TeamDAO t = new TeamDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String team_id = request.getParameter("team_id");
        List<Team_member> list = t.getTeamMemberByTeam(team_id);
        Team team = t.getTeamByTeamId(team_id);
        int teamid = team.getTeam_id();
        int class_id = team.getClass_id().getClass_id();
        String team_code = team.getTeam_code();
        String topic_name = team.getTopic_name();
        Boolean status = team.isStatus();
        String description = team.getDescription();
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_status = arr[1];
            t.changeStatusMember(Integer.parseInt(id), raw_status);
        }
        request.setAttribute("team_id", team_id);
        request.setAttribute("team_code", team_code);
        request.setAttribute("topic_name", topic_name);
        request.setAttribute("status", status);
        request.setAttribute("description", description);
        request.setAttribute("class_id", class_id);
        request.setAttribute("listMember", list);
        request.getRequestDispatcher("view/team/team_detail.jsp").forward(request, response);
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
        String team_id = request.getParameter("id");
        String team_code = request.getParameter("team_code");
        String topic_name = request.getParameter("topic_name");
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        t.EditTeam(team_code, topic_name, status, description, team_id);
        response.sendRedirect("list_of_team");
        
        
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
