/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.team;

import com.sun.javafx.image.impl.IntArgb;
import dal.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Team;
import model.Team_member;
import model.User;
import model.Class;

/**
 *
 * @author Admin
 */
public class CreateTeamController extends HttpServlet {

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
            out.println("<title>Servlet CreateTeamController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateTeamController at " + request.getContextPath() + "</h1>");
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
    Random rd = new Random();
    TeamDAO t = new TeamDAO();
    
    List<User> list1 = new ArrayList<>();
    List<Team> list2 = new ArrayList<>();
    List<User> list = new ArrayList<>();
//    int group = 4;
    int group;
    int total ;
    String class_id;
    int countTeam;
    String milestone;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         list1.removeAll(list1);
         list.removeAll(list);
         countTeam =t.countTeam();
//        HttpSession session = request.getSession();
//        int group = (int)session.getAttribute("group");
         class_id = request.getParameter("class");
        milestone = request.getParameter("milestone");
        HttpSession session = request.getSession();
        session.setAttribute("class_id", class_id);
        total = t.getTotalMember(class_id);
//        List<User> list = t.getAllTrainee(class_id);
        list = t.getAllTrainee(class_id);
        group = Integer.parseInt(request.getParameter("group"));
        list2.removeAll(list2);
        while (list.size() > 0) {
            int index = rd.nextInt(list.size());

            list1.add(list.get(index));
            list.remove(index);
        }
        if (total % group == 0) {
            int count = 0;
            for (int i = 0; i < group; i++) {
                
                list2.add(new Team());
                for (int j = 0; j < total / group; j++) {
                    
                    Team_member m = new Team_member();
                    m.setTeam(list2.get(i));

                    m.setUser(list1.get(count));
                    if(j==0){
                        m.setIs_leader(true);
                    }else{
                         m.setIs_leader(false);
                    }
                    list2.get(i).getListTeamMember().add(m);
                    list2.get(i).setTeam_id(countTeam+1);
                    
                    count++;

                }
                countTeam++;
            }
        } else {
            int count = 0;
            for (int i = 0; i < group - 1; i++) {
                list2.add(new Team());
                for (int j = 0; j < total / group; j++) {
                    
                    Team_member m = new Team_member();
                    m.setTeam(list2.get(i));
                    if(j==0){
                        m.setIs_leader(true);
                    }else{
                         m.setIs_leader(false);
                    }
                    m.setUser(list1.get(count));
                    list2.get(i).getListTeamMember().add(m);
                    list2.get(i).setTeam_id(countTeam+1);
                    
                    count++;

                }
                countTeam++;

            }
            list2.add(new Team());
            countTeam++;
            for (int j = 0; j < ((total / group) + (total % group)); j++) {
                
                Team_member m = new Team_member();
                m.setTeam(list2.get(group - 1));
                m.setUser(list1.get(count));
                if(j==0){
                        m.setIs_leader(true);
                    }else{
                         m.setIs_leader(false);
                    }
                list2.get(group - 1).getListTeamMember().add(m);
                list2.get(group - 1).setTeam_id(countTeam);
                count++;
            }
        }

        request.setAttribute("total", total);
        request.setAttribute("data", list2);
        
        request.getRequestDispatcher("view/team/list_team.jsp").forward(request, response);
        
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
        String temp;
        t.inserTeam(group,class_id);
        
        t.insertsubmit(group, class_id, milestone);
        
        
        if (total % group == 0) {
            
            for (int i = 0; i < list2.size(); i++) {
                for (int j = 0; j < total / group; j++) {
                     temp = list2.get(i).getListTeamMember().get(j).isIs_leader()+"";
                    if(temp.equals("true")){
                        temp = "1";
                    }else{
                        temp = "0";
                    }
                    t.inserTeamMember(list2.get(i).getListTeamMember().get(j).getTeam().getTeam_id(),
                            list2.get(i).getListTeamMember().get(j).getUser().getUser_id(),temp);
                }
            }
        } else {
            for (int i = 0; i < list2.size() - 1; i++) {
                for (int j = 0; j < total / group; j++) {
                     temp = list2.get(i).getListTeamMember().get(j).isIs_leader()+"";
                    if(temp.equals("true")){
                        temp = "1";
                    }else{
                        temp = "0";
                    }
                    t.inserTeamMember(list2.get(i).getListTeamMember().get(j).getTeam().getTeam_id(),
                            list2.get(i).getListTeamMember().get(j).getUser().getUser_id(),temp);
                }
                for (int j = 0; j < ((total / group) + (total % group)); j++) {
                     temp = list2.get(list2.size() - 1).getListTeamMember().get(j).isIs_leader()+"";
                    if(temp.equals("true")){
                        temp = "1";
                    }else{
                        temp = "0";
                    }
                    t.inserTeamMember(list2.get(list2.size() - 1).getListTeamMember().get(j).getTeam().getTeam_id(),
                            list2.get(list2.size() - 1).getListTeamMember().get(j).getUser().getUser_id(),temp);
                }
            }

        }
        response.sendRedirect("list_of_team");
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
