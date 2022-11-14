package controller.team;

import dal.TeamDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Class;
import model.Milestone;
import model.Team;
import model.Team_member;
import model.User;

/**
 *
 * @author Admin
 */
public class TeamListController extends HttpServlet {

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
            out.println("<title>Servlet TeamListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeamListController at " + request.getContextPath() + "</h1>");
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
    TeamDAO t = new TeamDAO();
    UserDAO dbUser = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String milestone = request.getParameter("milestone");
        if (milestone == null) {
            milestone = "";
        }
        String status = request.getParameter("status");
        if (status == null) {
            status = "";
        }
        
        String team_code = request.getParameter("team_code");
        if (team_code == null) {
            team_code = "";
        }
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_status = arr[1];
            t.changeStatus(Integer.parseInt(id), raw_status);
        }
        User u = (User) request.getSession().getAttribute("user");

        u = dbUser.get(u);
        int role = u.getList().get(0).getSetting_id();
        request.getSession().setAttribute("role", role);
        int user_id = u.getUser_id();
        List<Class> list = t.getAllClass(user_id);
        List<Milestone> listMileStone = t.getAllMileStone();
        String class_id = request.getParameter("class_id");

        if (class_id == null) {
            class_id = "";
        }
        
        List<Team> list2 = new ArrayList<>();
        if (role == 15) {
            Team_member tm = new Team_member();
            tm = t.getTeamByUser(u);
            list2 = t.getTeamByClass(class_id, role, tm.getTeam().getTeam_id(), u.getUser_id());
        } else if (role == 13 || role == 14) {
            list2 = t.getTeamByClass(class_id, team_code, status,milestone);
        }
        
        String class_code = t.getClassCodeById(class_id);

        int page, numperpage = 6;
        int size = list2.size();
        int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Team> list3 = t.getListByPage(list2, start, end);
        if(list3!=null){
            for(int i = 0;i<list3.size();i++){
            request.setAttribute("team"+i, t.getTeamMemberByTeam(list3.get(i).getTeam_id()+""));
        }
        
        
        }
        
        
       request.setAttribute("t", new TeamDAO());
       
        request.setAttribute("milestone", milestone);
        request.setAttribute("status", status);
        request.setAttribute("class_code", class_code);
        request.setAttribute("class_id", class_id);
        request.setAttribute("listTeam", list3);
        request.setAttribute("listClass", list);
        request.setAttribute("listMileStone", listMileStone);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.getRequestDispatcher("view/team/team_list.jsp").forward(request, response);
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
