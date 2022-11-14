/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SettingListDAO;
import dal.UserDAO;
import dal.WebContactDAO;
import model.WebContact;
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
public class SettingListController extends HttpServlet {

    SettingListDAO sDB = new SettingListDAO();
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
        //comment
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
        ////--User Login--/////
//        User u = (User) request.getSession().getAttribute("user");
//        u = dbUser.get(u);
//        int role = u.getList().get(0).getSetting_id();
//        request.getSession().setAttribute("role", role);
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                String setting_id = request.getParameter("setting_id");
                Setting s = new Setting();
                s.setSetting_id(Integer.parseInt(setting_id));
                s = sDB.get(s);
                request.setAttribute("classset", s);
                request.setAttribute("setting_type", sDB.listByType());
                request.getRequestDispatcher("view/admin/setting/settingedit.jsp").forward(request, response);
                break;
            case "add":
                request.setAttribute("setting_type", sDB.listByType());
                request.getRequestDispatcher("view/admin/setting/settingadd.jsp").forward(request, response);
                break;
            case "view":
                listSetting(request, response);
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
                settingEdit(request, response);
                break;
            case "add":
                settingAdd(request, response);
                break;

        }
    }

    protected void listSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("setting_per", sDB.list());
        request.setAttribute("setting_type", sDB.listByType());
        String raw_title = request.getParameter("settingtitle");
        String raw_status = request.getParameter("status");
        String raw_type = request.getParameter("type");//M F All
        String title = (raw_title != null && raw_title.length() > 0) ? raw_title : "";
        Integer type = (raw_type != null && !raw_type.equals("-1")) ? new Integer(raw_type) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");

        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 5;
        int count = sDB.count(title, type, status);
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
        //check change status
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_statuss = arr[1];
            sDB.changeStatus(Integer.parseInt(id), raw_statuss);
        }
        ArrayList<Setting> setting = sDB.paggingV2(page_size, page_index, type, title, status);
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("setting_fil", setting);
        request.setAttribute("total_page", total_page);
        request.setAttribute("settingtitle", title);
        request.setAttribute("settingtype", type);
        request.setAttribute("settingstatus", status);
        request.setAttribute("count", count);

        request.getRequestDispatcher("view/admin/setting/settinglist.jsp").forward(request, response);
    }

    public void settingAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("setting_type", sDB.listByType());
        String raw_type = request.getParameter("settingtype");
        String raw_setting_title = request.getParameter("settingtitle");
        String raw_dis_order = request.getParameter("settingorder");
        String raw_set_value = request.getParameter("settingvalue");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("description");

        String setting_title = (raw_setting_title != null && raw_setting_title.length() > 0) ? raw_setting_title : "";
        String setting_value = (raw_set_value != null && raw_set_value.length() > 0) ? raw_set_value : "";
        String display_order = (raw_dis_order != null && raw_dis_order.length() > 0) ? raw_dis_order : "";
        String description = (raw_des != null && raw_des.length() > 0) ? raw_des : "";
        Integer type_id = (raw_type != null && !raw_type.equals("-1")) ? new Integer(raw_type) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");

        request.setAttribute("typeset", type_id);
        request.setAttribute("titleset", setting_title);
        request.setAttribute("orderset", display_order);
        request.setAttribute("valueset", setting_value);
        request.setAttribute("descriptionset", description);

        //add Setting
        int count = sDB.countID();
        // ClassSetting cs = new ClassSetting(count + 1, s, setting_title, setting_value, display_order, c, status, description);
        Setting s = new Setting(count + 1, type_id, setting_title, setting_value, Integer.parseInt(display_order), status, description);
        sDB.insert(s);
        s = sDB.get(s);
        request.getSession().setAttribute("setlist", s);
        response.sendRedirect("settinglist?action=add#demo-modal2");
        //request.getRequestDispatcher("view/admin/setting/settingadd.jsp").forward(request, response);
    }

    public void settingEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("setting_type", sDB.listByType());
        String raw_setting_id = request.getParameter("settingid");
        String raw_type = request.getParameter("settingtype");
        String raw_setting_title = request.getParameter("settingtitle");
        String raw_dis_order = request.getParameter("settingorder");
        String raw_set_value = request.getParameter("settingvalue");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("description");

        String setting_title = (raw_setting_title != null && raw_setting_title.length() > 0) ? raw_setting_title : "";
        String setting_value = (raw_set_value != null && raw_set_value.length() > 0) ? raw_set_value : "";
        String display_order = (raw_dis_order != null && raw_dis_order.length() > 0) ? raw_dis_order : "";
        String description = (raw_des != null && raw_des.length() > 0) ? raw_des : "";
        Integer type_id = (raw_type != null && !raw_type.equals("-1")) ? new Integer(raw_type) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");
        Integer setting_id = (raw_setting_id != null && !raw_setting_id.equals("-1")) ? new Integer(raw_setting_id) : new Integer("-1");

        request.setAttribute("typeset", setting_title);
        request.setAttribute("titleset", setting_title);
        request.setAttribute("orderset", display_order);
        request.setAttribute("valueset", setting_value);
        request.setAttribute("descriptionset", description);

        //add class Setting
        Setting s = new Setting(setting_id, type_id, setting_title, setting_value, Integer.parseInt(display_order), status, description);
        sDB.update(s);
        s = sDB.get(s);
        request.setAttribute("classset", s);

        response.sendRedirect("settinglist?action=edit&setting_id=" + setting_id + "#demo-modal2");
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
