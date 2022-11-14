/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Setting;
import model.Subject;
import model.User;

/**
 *
 * @author mavjp
 */
public class UsersController extends HttpServlet {
   
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
            out.println("<title>Servlet UserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath () + "</h1>");
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
    UserDAO dbUser = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //get user login
        User u = (User)request.getSession().getAttribute("user");
        u = dbUser.get(u);
        int role = u.getList().get(0).getSetting_id();
        request.getSession().setAttribute("role", role);
        //
        ArrayList<Setting> roles = dbUser.listRoles();
        request.getSession().setAttribute("roles", roles);
        //
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                String id = request.getParameter("id");
                User user = new User();
                user.setUser_id(Integer.parseInt(id));
                user = dbUser.get(user);
                request.setAttribute("user", user);
                request.getRequestDispatcher("view/subject/subject_edit.jsp").forward(request, response);
                break;
            case "view":
                viewUser(request, response);
                break;
            case "add":
                request.getRequestDispatcher("view/subject/subject_add.jsp").forward(request, response);
                break;
            case "upload":
                break;
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
        
    }


    protected void viewUser(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //search
        String search_txt = request.getParameter("txtsearch");
        if (search_txt == null || search_txt.equals("") || search_txt.equals("undefined")) {
            search_txt = "";
        }
        //filter
        String f_status = request.getParameter("filter_status");
        f_status = (f_status!=null && !f_status.equals("-1"))?f_status:"-1";
        String f_role = request.getParameter("filter_role");
        Integer role = (f_role!=null && !f_role.equals("-1"))?new Integer(f_role):new Integer("-1");
        request.setAttribute("filter_status", f_status);
        request.setAttribute("filter_role", role);
        
        //
        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 6;
        int count = dbUser.count(search_txt,f_status,role);
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
        //
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_status = arr[1];
            dbUser.changeStatus(Integer.parseInt(id), raw_status);
        }
        //
        ArrayList<User> users = dbUser.pagging(page_size, page_index, search_txt, role, f_status);
        
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("users", users);
        request.setAttribute("total_page", total_page);
        request.setAttribute("count", count);
        request.setAttribute("txtsearch", search_txt);
        request.getRequestDispatcher("view/users/user_list.jsp").forward(request, response);
    }
}
