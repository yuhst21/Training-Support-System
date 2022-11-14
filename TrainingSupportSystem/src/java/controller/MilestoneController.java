/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MileStoneDAO;
import dal.ScheduleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Assignment;
import model.Milestone;
import model.Schedule;
import model.User;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author ACER
 */
public class MilestoneController extends HttpServlet {
    
    String mess1, mess2;

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
            out.println("<title>Servlet MilestoneController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MilestoneController at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        MileStoneDAO mdb = new MileStoneDAO();
        switch (action) {
            case "add": {
                UserDAO udb = new UserDAO();
                ScheduleDAO sdb = new ScheduleDAO();
                
                User user = (User) request.getSession().getAttribute("account");
                if (udb.checkRoleID(user.getUser_id(), 14)) {
                    ArrayList<model.Class> clist = mdb.listClass(6);
                    ArrayList<Assignment> alist = mdb.listAssbyCid(1);
                    request.setAttribute("alist", alist);
                    request.setAttribute("clist", clist);
                    request.getRequestDispatcher("view/milestone/milestone_add.jsp").forward(request, response);
                } else {
                    response.getWriter().print("Acces Denided");
                    
                }
                break;
            }
            case "list": {
                UserDAO udb = new UserDAO();
                ScheduleDAO sdb = new ScheduleDAO();
                
                User user = (User) request.getSession().getAttribute("account");
                if (udb.checkRoleID(user.getUser_id(), 14)) {
                    ArrayList<model.Class> clist = mdb.listClass(user.getUser_id());
                    String raw_class = request.getParameter("class");
                    String raw_ass=request.getParameter("ass");
                    String raw_status = request.getParameter("status");
                    String raw_tittle = request.getParameter("tittle");
                    if (raw_class == null || raw_class.equals("")) {
                        raw_class = clist.get(0).getClass_id() + "";
                    }
                    if (raw_ass == null || raw_ass.equals("")) {
                        raw_ass = "-1";
                    }
                    if (raw_status == null || raw_status.equals("all") || raw_status.equals("")) {
                        raw_status = "-1";
                    }
                    
                    if (raw_tittle == null || raw_tittle.equals("")) {
                        raw_tittle = "-1";
                    }

                    //paging             
                    String raw_page = request.getParameter("page");
                    if (raw_page == null || raw_page.equals("")) {
                        raw_page = "1";
                    }
                    int page_index = Integer.parseInt(raw_page);
                    int page_size = 4;
                    int count = mdb.count(user.getUser_id(), Integer.parseInt(raw_class),Integer.parseInt(raw_ass), raw_status, raw_tittle);
                    
                    int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
                    
                    ArrayList<Assignment> alist = mdb.listAssbyCid(Integer.parseInt(raw_class));
                    String status_id = request.getParameter("change_status");
                    if (status_id != null) {
                        String[] arr = status_id.split("_");
                        String id = arr[0];
                        String status = arr[1];
                        mdb.changeStatus(Integer.parseInt(id), status);
                    }
                    ArrayList<Milestone> mlist = mdb.pagging(user.getUser_id(), Integer.parseInt(raw_class),Integer.parseInt(raw_ass), raw_status, raw_tittle, page_size, page_index);
                    request.setAttribute("clist", clist);
                    request.setAttribute("alist", alist);
                    
                    request.setAttribute("class_id", raw_class);
                    request.setAttribute("ass_id", raw_ass);
                    request.setAttribute("status", raw_status);
                    request.setAttribute("tittle", raw_tittle);
                    request.setAttribute("notext", "-1");
                    request.setAttribute("active", "active");
                    request.setAttribute("inactive", "inactive");
                    request.setAttribute("page_size", page_size);
                    request.setAttribute("page_index", page_index);
                    request.setAttribute("subs", mlist);
                    request.setAttribute("total_page", total_page);
                    request.setAttribute("count", count);
                    request.getRequestDispatcher("view/milestone/milestone_list.jsp").forward(request, response);
                } else {
                    response.getWriter().print("Acces Denided");
                }
                break;
            }
            case "view": {
                int id = Integer.parseInt(request.getParameter("id"));
                Milestone m = new Milestone();
                m = mdb.getById(id);
                request.setAttribute("milestone", m);
                request.setAttribute("role", 9);
                request.getRequestDispatcher("view/milestone/milestone_detail.jsp").forward(request, response);
                break;
            }
            case "edit": {
                UserDAO udb = new UserDAO();
                ScheduleDAO sdb = new ScheduleDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Milestone m = new Milestone();
                m = mdb.getById(id);
                
                User user = (User) request.getSession().getAttribute("account");
                if (udb.checkRoleID(user.getUser_id(), 14)) {
                    ArrayList<model.Class> clist = mdb.listClass(6);
                    ArrayList<Assignment> alist = mdb.listAssbyCid(m.getClass_id());
                    
                    Assignment a = mdb.getAssbyId(m.getAssignment().getAss_id());
                    request.setAttribute("clist", clist);
                    request.setAttribute("alist", alist);
                    request.setAttribute("milestone", m);
                    request.setAttribute("mess1", mess1);
                    request.setAttribute("mess1", mess2);
                    request.setAttribute("cname", mdb.getClassName(m.getClass_id()));
                    request.setAttribute("assbody", mdb.getAssbody(m.getClass_id()));
                    request.setAttribute("class_id", m.getClass_id());
                    request.setAttribute("ass_id", m.getAssignment().getAss_id());
                    request.getRequestDispatcher("view/milestone/milestone_update.jsp").forward(request, response);
                } else {
                    response.getWriter().print("Acces Denided");
                    
                }
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                mdb.delete(id);
                response.sendRedirect("milestone?action=list");
                break;
            }
            
            case "load": {
                int class_id = Integer.parseInt(request.getParameter("classid"));
                ArrayList<Assignment> alist = mdb.listAssbyCid(class_id);
                response.getWriter().print("<label class=\"form-label\">Assignment:</label>");
                response.getWriter().print("<select style=\"width: 200px;text-align: center\" class=\"form-control\"  style=\"margin-right: 80px;\" class=\"s_filter\" name=\"ass_id\">");
                for (Assignment a : alist) {
                    response.getWriter().print("<option \n"
                            + "\n"
                            + "value=\"" + a.getAss_id() + "\">" + a.getTitle() + "</option>");
                }
                response.getWriter().print("</select>");
                break;
                
            }
            case "load2": {
                int class_id = Integer.parseInt(request.getParameter("classid"));
                ArrayList<Assignment> alist = mdb.listAssbyCid(class_id);
                response.getWriter().print("<option \n"
                        + "\n"
                        + "                                                        value=\"-1\">All Assignment</option>");
                for (Assignment a : alist) {
                    response.getWriter().print("<option \n"
                            + "\n"
                            + "value=\"" + a.getAss_id() + "\">" + a.getTitle() + "</option>");
                }
                
                break;
            }
            
        }
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
        String action = request.getParameter("action");
        MileStoneDAO mdb = new MileStoneDAO();
        response.getWriter().print(action);
        switch (action) {
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String tittle = request.getParameter("tittle");
                Date fromdate = Date.valueOf(request.getParameter("fromdate"));
                int ass_id = Integer.parseInt(request.getParameter("ass_id"));
                Date todate = Date.valueOf(request.getParameter("todate"));
                String ass_body = request.getParameter("ass_body");
                
                String descrption = request.getParameter("description");
                Boolean status = false;
                if (request.getParameter("status").equals("active")) {
                    status = true;
                }
                
                Milestone m = new Milestone();
                m.setMilestone_id(id);
                m.setTittle(tittle);
                m.setFrom_date(fromdate);
                m.setTo_date(todate);
                Assignment ass = new Assignment();
                ass.setAss_id(ass_id);
                m.setAssignment(ass);
                m.setAss_body(ass_body);
                m.setStatus(status);
                
                mdb.Update(m);
                response.sendRedirect("milestone?action=edit&id=" + id + "#demo-modal2");
                break;
            }
            
            case "add": {
                String tittle = request.getParameter("tittle");
                int class_id = Integer.parseInt(request.getParameter("class_id"));
                int ass_id = Integer.parseInt(request.getParameter("ass_id"));
                Date startdate = Date.valueOf(request.getParameter("fromdate"));
                Date endate = Date.valueOf(request.getParameter("todate"));
                boolean status = false;
                if (request.getParameter("status").equals("active")) {
                    status = true;
                }
                Assignment a = mdb.getAssbyId(ass_id);
                Milestone m = new Milestone();
                m.setClass_id(class_id);
                m.setTittle(tittle);
                m.setAssignment(a);
                m.setFrom_date(startdate);
                m.setTo_date(endate);
                m.setStatus(status);
                
                mdb.add(m);
                response.sendRedirect("milestone?action=list#demo-modal2");
                break;
            }
            case "list": {
                String class_id = request.getParameter("classid");
                response.getWriter().println(" <li class=\"breadcrumb-item active\" aria-current=\"page\">" + class_id + "</li>");
            }
        }
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
