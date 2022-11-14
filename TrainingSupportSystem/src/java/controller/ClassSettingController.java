/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ClassSettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.ClassSetting;
import model.Setting;
import model.User;

/**
 *
 * @author win
 */
public class ClassSettingController extends HttpServlet {

    ClassSettingDAO cDB = new ClassSettingDAO();
    UserDAO dbUser = new UserDAO();

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
            out.println("<title>Servlet ClassSettingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassSettingController at " + request.getContextPath() + "</h1>");
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
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        ////--User Login END--/////
        int role = u.getList().get(0).getSetting_id();
        request.getSession().setAttribute("role", role);
        request.getSession().setAttribute("suOrTra", u.getUser_id());
        String action = request.getParameter("action");
        ArrayList<model.Class> cl = cDB.listClassByUser(u.getUser_id());
        request.setAttribute("classlist", cl);
        switch (action) {
            case "edit":
                String setting_id = request.getParameter("setting_id");
                ClassSetting cs = new ClassSetting();
                cs.setSetting_id(Integer.parseInt(setting_id));
                cs = cDB.get(cs);
                request.setAttribute("classset", cs);
                request.setAttribute("typelist", cDB.listType());
                request.setAttribute("success_mess", "Update sucessfull");
                request.getRequestDispatcher("view/admin/classsetting/classsettingedit.jsp").forward(request, response);
                break;
            case "add":
                request.setAttribute("typelist", cDB.listType());
                request.getRequestDispatcher("view/admin/classsetting/classsettingadd.jsp").forward(request, response);
                break;
            case "view":
                listClassSetting(request, response);
                break;

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
        switch (action) {
            case "edit":
                classSettingEdit(request, response);
                break;
            case "add":
                classSettingAdd(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    protected void listClassSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        request.setAttribute("typelist", cDB.listType());
        request.setAttribute("setting_per", cDB.list());
        String raw_title = request.getParameter("title");
        String raw_status = request.getParameter("status");
        String raw_type = request.getParameter("type");//M F All
        String raw_class = request.getParameter("class_get");//M F All
        //Convert to value
        String title = (raw_title != null && raw_title.length() > 0) ? raw_title : "";

        Integer type = (raw_type != null && !raw_type.equals("-1")) ? new Integer(raw_type) : new Integer("-1");
        Integer class_in = (raw_class != null && !raw_class.equals("-1")) ? new Integer(raw_class) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");

        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 5;
        int count = cDB.count(title, type, status, class_in,u.getUser_id());
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
        //check change status
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_statuss = arr[1];
            cDB.changeStatus(Integer.parseInt(id), raw_statuss);
        }
        ArrayList<ClassSetting> setting = cDB.paggingV2(page_size, page_index, type, title, status, class_in,u.getUser_id());
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("setting_fil", setting);
        request.setAttribute("total_page", total_page);
        request.setAttribute("settingtitle", title);
        request.setAttribute("status", status);
        request.setAttribute("count", count);
        request.setAttribute("cl_in", class_in);
        request.setAttribute("settingtype", type);
        request.getRequestDispatcher("view/admin/classsetting/classsettinglist.jsp").forward(request, response);
    }

    public void classSettingAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        request.setAttribute("typelist", cDB.listType());
        ArrayList<model.Class> cl = cDB.listClassByUser(u.getUser_id());
        request.setAttribute("classlist", cl);
        String raw_type = request.getParameter("settingtype");
        String raw_setting_title = request.getParameter("settingtitle");
        String raw_dis_order = request.getParameter("settingorder");
        String raw_class_id = request.getParameter("class");
        String raw_set_value = request.getParameter("settingvalue");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("description");

        String setting_title = (raw_setting_title != null && raw_setting_title.length() > 0) ? raw_setting_title : "";
        String setting_value = (raw_set_value != null && raw_set_value.length() > 0) ? raw_set_value : "";
        String display_order = (raw_dis_order != null && raw_dis_order.length() > 0) ? raw_dis_order : "";
        String description = (raw_des != null && raw_des.length() > 0) ? raw_des : "";
        Integer type_id = (raw_type != null && !raw_type.equals("-1")) ? new Integer(raw_type) : new Integer("-1");
        Integer clas_id = (raw_class_id != null && !raw_class_id.equals("-1")) ? new Integer(raw_class_id) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");

        request.setAttribute("typeset", type_id);
        request.setAttribute("titleset", setting_title);
        request.setAttribute("orderset", display_order);
        request.setAttribute("valueset", setting_value);
        request.setAttribute("descriptionset", description);
        request.setAttribute("clas_id", clas_id);

        Setting s = new Setting();
        s.setSetting_id(type_id);
        model.Class c = new model.Class();
        c.setClass_id(clas_id);
        //add class Setting
        int count = cDB.countID();
        ClassSetting cs = new ClassSetting(count + 1, s, setting_title, setting_value, display_order, c, status, description);
        cDB.insert(cs);
        request.setAttribute("code_mess", "");
        request.setAttribute("success_mess", "Add sucessfull !");
        request.setAttribute("code_mess", "");
        request.getRequestDispatcher("view/admin/classsetting/classsettingadd.jsp").forward(request, response);
    }

    public void classSettingEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        request.setAttribute("typelist", cDB.listType());
        ArrayList<model.Class> cl = cDB.listClassByUser(u.getUser_id());
        request.setAttribute("classlist", cl);
        String raw_setting_id = request.getParameter("settingid");
        String raw_type = request.getParameter("settingtype");
        String raw_setting_title = request.getParameter("settingtitle");
        String raw_dis_order = request.getParameter("settingorder");
        String raw_class_id = request.getParameter("class");
        String raw_set_value = request.getParameter("settingvalue");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("description");

        String setting_title = (raw_setting_title != null && raw_setting_title.length() > 0) ? raw_setting_title : "";
        String setting_value = (raw_set_value != null && raw_set_value.length() > 0) ? raw_set_value : "";
        String display_order = (raw_dis_order != null && raw_dis_order.length() > 0) ? raw_dis_order : "";
        String description = (raw_des != null && raw_des.length() > 0) ? raw_des : "";
        Integer type_id = (raw_type != null && !raw_type.equals("-1")) ? new Integer(raw_type) : new Integer("-1");
        Integer clas_id = (raw_class_id != null && !raw_class_id.equals("-1")) ? new Integer(raw_class_id) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");
        Integer setting_id = (raw_setting_id != null && !raw_setting_id.equals("-1")) ? new Integer(raw_setting_id) : new Integer("-1");

        request.setAttribute("typeset", setting_title);
        request.setAttribute("titleset", setting_title);
        request.setAttribute("orderset", display_order);
        request.setAttribute("valueset", setting_value);
        request.setAttribute("descriptionset", description);

        Setting s = new Setting();
        s.setSetting_id(type_id);
        model.Class c = new model.Class();
        c.setClass_id(clas_id);
        //add class Setting
        ClassSetting cs = new ClassSetting(setting_id, s, setting_title, setting_value, display_order, c, status, description);
        cDB.update(cs);
        cs = cDB.get(cs);
        request.setAttribute("classset", cs);
        response.sendRedirect("classsetting?action=edit&setting_id=" + setting_id + "#demo-modal2");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
