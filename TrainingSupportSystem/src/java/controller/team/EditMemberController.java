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
import model.User;

/**
 *
 * @author Admin
 */
public class EditMemberController extends HttpServlet {
   
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
            out.println("<title>Servlet EditMemberController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditMemberController at " + request.getContextPath () + "</h1>");
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
        String id_raw = request.getParameter("user_id");
       try{
           int id = Integer.parseInt(id_raw);
           int team = tdb.getTeamById(id);
           Team_member tm = tdb.getMemberById(id);
           int team_id = tm.getTeam().getTeam_id();                 
           boolean is_active = tm.isIs_active();
           boolean is_leader = tm.isIs_leader();
           String class_id = tdb.getClassByUser(id);
           List<Team> listTeam = tdb.getAllTeam(class_id);
           request.setAttribute("id", id);
           request.setAttribute("team_id", team_id);
           request.setAttribute("is_active", is_active);
           request.setAttribute("is_leader", is_leader);
           request.setAttribute("listTeam", listTeam);          
           request.getRequestDispatcher("view/team/edit_member.jsp").forward(request, response);
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
       

        
        int user_id = Integer.parseInt(request.getParameter("id")); 

        int team_id = Integer.parseInt(request.getParameter("team_id"));
        List<Team_member> list= tdb.getTeamMemberById(user_id, team_id);
        for (int i = 0; i < list.size(); i++) {
            tdb.EditLeader(list.get(i));
        }
         boolean is_active = Boolean.parseBoolean(request.getParameter("is_active"));
         boolean is_leader = Boolean.parseBoolean(request.getParameter("is_leader"));
         
         tdb.Edit(new Team_member( new Team(team_id),new User(user_id), is_leader, is_active));
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
