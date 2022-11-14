/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SubjectDAO;
import dal.SubjectSettingDAO;
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
import model.SubjectSetting;
import model.User;

/**
 *
 * @author mavjp
 */
public class SubjectSettingController extends HttpServlet {

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
            out.println("<title>Servlet SubjectSettingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectSettingController at " + request.getContextPath() + "</h1>");
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
    SubjectDAO dbSub = new SubjectDAO();
    SubjectSettingDAO dbSet = new SubjectSettingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        String action = request.getParameter("action");
        request.getSession().setAttribute("subs", dbSub.list(u.getUser_id()));

        switch (action) {
            case "view":
                view(request, response);
                break;
            case "edit":
                String id = request.getParameter("id");
                SubjectSetting s = new SubjectSetting();
                s.setId(Integer.parseInt(id));
                request.setAttribute("set", dbSet.get(s));
                request.getSession().setAttribute("success_mess", "");
                request.getRequestDispatcher("view/subject/subject_setting_edit.jsp").forward(request, response);
                break;
            case "add":
                request.getSession().setAttribute("add_subset_status", true);
                request.getRequestDispatcher("view/subject/subject_setting_add.jsp").forward(request, response);
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
                edit(request, response);
                break;
            case "add":
                add(request, response);
                break;
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subject = request.getParameter("add_subset_name");
        String type = request.getParameter("add_subset_type");
        String value = request.getParameter("add_subset_value");
        String order = request.getParameter("add_subset_order");
        String title = request.getParameter("add_subset_title");
        String body = request.getParameter("add_subset_body");
        String status = request.getParameter("add_subset_status");
        //set session
        request.getSession().setAttribute("add_subset_name", Integer.parseInt(subject));
        request.getSession().setAttribute("add_subset_type", type);
        request.getSession().setAttribute("add_subset_value", value);
        request.getSession().setAttribute("add_subset_title", title);
        request.getSession().setAttribute("add_subset_order", order);
        request.getSession().setAttribute("add_subset_body", body);
        request.getSession().setAttribute("add_subset_status", status.equals("active"));
        //add
        String num_mess = "";
        if (dbSet.checkSettingExist(Integer.parseInt(subject), Integer.parseInt(type))) {
            request.setAttribute("fail_mess", "Subject Setting Existed!");
            request.getRequestDispatcher("view/subject/subject_setting_add.jsp").forward(request, response);
            return;
        }
        if (!order.matches("^[0-9]+$")) {
            num_mess = "Your input must be a number!";
            request.setAttribute("fail_mess", "Update Fail");
            request.setAttribute("num_mess", num_mess);
            request.getRequestDispatcher("view/subject/subject_setting_add.jsp").forward(request, response);
            return;
        }
        //add to db
        Subject sub = new Subject();
        sub.setSubject_id(Integer.parseInt(subject));
        Setting set = new Setting();
        set.setSetting_id(Integer.parseInt(type));
        SubjectSetting s = new SubjectSetting(dbSet.countID() + 1, sub, set, title, value, order,status.equals("active"), body);
        dbSet.insert(s);
        request.setAttribute("fail_mess", "");
        request.setAttribute("success_mess", "Add sucessfull !");
        request.getSession().removeAttribute("num_mess");
        request.getRequestDispatcher("view/subject/subject_setting_add.jsp").forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        //raw setting
        SubjectSetting s = new SubjectSetting();
        s.setId(Integer.parseInt(id));
        s = dbSet.get(s);
        String subject = request.getParameter("subject");
        String value = request.getParameter("value");
        String order = request.getParameter("order");
        String title = request.getParameter("title");
        //
        boolean status = request.getParameter("status").equals("active");
        String body = request.getParameter("body");
        if (body == null) {
            body = "";
        }
        String num_mess = "";
        if (!order.matches("^[0-9]+$")) {
            num_mess = "Your input must be a number!";
            request.setAttribute("success_mess", "");
            request.setAttribute("fail_mess", "Update Fail");
            request.getSession().setAttribute("num_mess", num_mess);
            response.sendRedirect("subject_setting?action=edit&id=" + id);
            return;
        }
        //validation
        //update
        s = new SubjectSetting();
        s.setId(Integer.parseInt(id));
        s.setSetting_value(value);
        s.setStatus(status);
        s.setDescription(body);
        s.setSetting_title(title);
        s.setDisplay_order(order);
        dbSet.update(s, Integer.parseInt(subject));
        request.setAttribute("success_mess", "Update sucessfull");
        request.setAttribute("fail_mess", "");
        s = dbSet.get(s);
        request.getSession().removeAttribute("num_mess");
        request.setAttribute("set", s);
        request.setAttribute("id", Integer.parseInt(id));
        request.getRequestDispatcher("view/subject/subject_setting_edit.jsp").forward(request, response);
    }
    UserDAO dbUser = new UserDAO();

    public void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String search_txt = request.getParameter("txtsearch");
        if (search_txt == null || search_txt.equals("") || search_txt.equals("undefined")) {
            search_txt = "";
        }
        //filter
        String f_subject = request.getParameter("filter_subject");
        String f_title = request.getParameter("filter_title");
        String f_status = request.getParameter("filter_status");
        String subject = (f_subject != null && !f_subject.equals("-1") && !f_subject.equals("")) ? f_subject : "C# Language";
        String title = (f_title != null && !f_title.equals("-1") && !f_title.equals("")) ? f_title : null;
        Boolean status = (f_status != null && !f_status.equals("-1") && !f_status.equals("undefined")) ? f_status.equals("1") : null;
        request.setAttribute("filter_subject", subject);
        request.setAttribute("filter_title", title);
        request.setAttribute("filter_status", status);

        //check change status
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_status = arr[1];
            dbSet.changeStatus(Integer.parseInt(id), raw_status);
        }
        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 6;
        int count = dbSet.count(search_txt, title, status, subject);
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;

        ArrayList<SubjectSetting> settings = dbSet.pagging(page_size, page_index, search_txt, title, status, subject);
        request.setAttribute("f_subject", subject);
        request.setAttribute("f_title", title);
        request.setAttribute("f_status", status);
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("settings", settings);
        request.setAttribute("total_page", total_page);
        request.setAttribute("count", count);
        request.setAttribute("txtsearch", search_txt);
        request.getRequestDispatcher("view/subject/subject_setting_list.jsp").forward(request, response);
    }

}
