/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.SystemPermissionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Permission;
import model.Setting;

/**
 *
 * @author mavjp
 */
public class SystemPermissionController extends HttpServlet {
   
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
            out.println("<title>Servlet SystemPermissionController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SystemPermissionController at " + request.getContextPath () + "</h1>");
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
    SystemPermissionDAO dbSys = new SystemPermissionDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ArrayList<Permission> pers = dbSys.listPermission();
        ArrayList<Setting> roles = dbSys.listSettingByTypeID(1);
        ArrayList<Setting> screens = dbSys.listSettingByTypeID(5);
        request.getSession().setAttribute("pers", pers);
        request.getSession().setAttribute("roles", roles);
        request.getSession().setAttribute("screens", screens);
        request.getRequestDispatcher("view/admin/system_permission.jsp").forward(request, response);
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
        String[] components = request.getParameterValues("component");
        ArrayList<Permission> pers = new ArrayList<>();
        for (String item : components) {
            int role_id = Integer.parseInt(item.split("_")[0]);
            Setting role = new Setting();
            role.setSetting_id(role_id);
            int screen_id = Integer.parseInt(item.split("_")[1]);
            Setting screen = new Setting();
            screen.setSetting_id(screen_id);
            //get permission
            String data = request.getParameter("data_"+role_id+"_"+screen_id);
            data = (data == null || data.length()==0)?"0":data;
            String add = request.getParameter("add_"+role_id+"_"+screen_id);
            add = (add== null || add.length()==0)?"0":add;
            String delete = request.getParameter("delete_"+role_id+"_"+screen_id);
            delete = (delete==null || delete.length()==0)?"0":delete;
            String edit = request.getParameter("edit_"+role_id+"_"+screen_id);
            edit = (edit==null || edit.length()==0)?"0":edit;
            //add new permission
            Permission p = new Permission();
            p.setRole(role);
            p.setScreen(screen);
            p.setCan_add(add.equals("1"));
            p.setCan_delete(delete.equals("1"));
            p.setCan_edit(edit.equals("1"));
            p.setGet_all_data(data.equals("1"));
            pers.add(p);
        }
        dbSys.saveChanges(pers);
        response.sendRedirect("system_permission");
//        request.setAttribute("mess", "Update sucess !");
//        request.getRequestDispatcher("view/system_permission.jsp").forward(request, response);
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
