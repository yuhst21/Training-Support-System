/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ClassDAO;
import dal.ClassSettingDAO;
import dal.SubjectDAO;
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
import model.Subject;
import model.User;

/**
 *
 * @author win
 */
public class ClassListController extends HttpServlet {

    ClassSettingDAO cDB = new ClassSettingDAO();
    ClassDAO clDB = new ClassDAO();
    UserDAO dbUser = new UserDAO();
    SubjectDAO subDB = new SubjectDAO();

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
            out.println("<title>Servlet ClassListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassListController at " + request.getContextPath() + "</h1>");
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
        request.getSession().setAttribute("managerr", u.getUser_id());
        request.getSession().setAttribute("setting_per", clDB.listAllClassByManager(u.getUser_id()));
        request.getSession().setAttribute("setting_per1", clDB.listAllClassByManager(u.getUser_id()));
        request.getSession().setAttribute("list_sub", subDB.list(u.getUser_id()));
        ArrayList<User> supporter = dbUser.getUserByRoleId(13);
        ArrayList<User> trainer = dbUser.getUserByRoleId(14);
        request.getSession().setAttribute("supporter", supporter);
        request.getSession().setAttribute("trainer", trainer);
        String action = request.getParameter("action");
        ArrayList<model.Class> cl = cDB.listClass();
        request.setAttribute("classlist", cl);
        switch (action) {
            case "edit":
                String setting_id = request.getParameter("class_id");
                model.Class cs = new model.Class();
                cs.setClass_id(Integer.parseInt(setting_id));
                cs = clDB.getByClass(cs);
                request.setAttribute("term_type", clDB.listTermType());
                request.setAttribute("classset", cs);
                request.getRequestDispatcher("view/class/classdetail.jsp").forward(request, response);
                break;
            case "add":
                request.setAttribute("term_type1", clDB.listTermType());
                request.getRequestDispatcher("view/class/classadd.jsp").forward(request, response);
                break;
            case "view":
                request.setAttribute("setting_per", clDB.listAllClass());
                listClass(request, response);
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
                classEdit(request, response);
                break;
            case "add":
                classSettingAdd(request, response);
                break;
        }
    }

    protected void listClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        String raw_supporter_id = request.getParameter("support_get");
        String raw_trainer_id = request.getParameter("trainer_get");
        String raw_status = request.getParameter("status");
        String raw_class_id = request.getParameter("class_get");//M F All
        //Convert to value
        Integer supporter_id = (raw_supporter_id != null && !raw_supporter_id.equals("-1")) ? new Integer(raw_supporter_id) : new Integer("-1");
        Integer trainer_id = (raw_trainer_id != null && !raw_trainer_id.equals("-1")) ? new Integer(raw_trainer_id) : new Integer("-1");
        Integer class_id = (raw_class_id != null && !raw_class_id.equals("-1")) ? new Integer(raw_class_id) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");

        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 5;
        int count = clDB.count(supporter_id, trainer_id, class_id, status,u.getUser_id());
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
        //check change status
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_statuss = arr[1];
            clDB.changeStatus(Integer.parseInt(id), raw_statuss);
        }
        ArrayList<model.Class> classlist = clDB.paggingV2(page_size, page_index, supporter_id, trainer_id, class_id, status,u.getUser_id());
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("setting_fil", classlist);
        request.setAttribute("total_page", total_page);
        request.setAttribute("sup", supporter_id);
        request.setAttribute("train", trainer_id);
        request.setAttribute("cla", class_id);
        request.setAttribute("status", status);
        request.setAttribute("count", count);
        request.getRequestDispatcher("view/class/class.jsp").forward(request, response);
    }

    public void classSettingAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("term_type1", clDB.listTermType());
        String raw_support = request.getParameter("support_get");
        String raw_trainer = request.getParameter("trainer_get");
        String raw_term = request.getParameter("term_get");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("description");
        String raw_subject = request.getParameter("sub_code");
        String raw_class_name = request.getParameter("class_name");

        String description = (raw_des != null && raw_des.length() > 0) ? raw_des : "";
        Integer support_id = (raw_support != null && !raw_support.equals("-1")) ? new Integer(raw_support) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");
        Integer trainer_id = (raw_trainer != null && !raw_trainer.equals("-1")) ? new Integer(raw_trainer) : new Integer("-1");
        Integer term = (raw_term != null && !raw_term.equals("-1")) ? new Integer(raw_term) : new Integer("-1");
        Integer subject_id = (raw_subject != null && !raw_subject.equals("-1")) ? new Integer(raw_subject) : new Integer("-1");

        request.setAttribute("descriptionset", description);
        int count = clDB.countID();
        model.Class c = new model.Class();
        c.setClass_id(count + 1);
        c.setClass_name(raw_class_name);
        c.setSupporter_id(support_id);
        c.setTrainer_id(trainer_id);
        c.setTerm_id(term);
        c.setStatus(Boolean.parseBoolean(raw_status));
        c.setDescription(description);
        Subject sub = new Subject();
        sub.setSubject_id(subject_id);
        c.setSubject(sub);
        //add class Setting
        clDB.insertByClass(c);
        c = clDB.getByClass(c);
        request.getSession().setAttribute("data", c);
        response.sendRedirect("classlist?action=add#demo-modal2");
        //request.getRequestDispatcher("view/class/classadd.jsp#demo-modal2").forward(request, response);
    }

    public void classEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("term_type", clDB.listTermType());
        String raw_class_id = request.getParameter("class_id");
        String raw_support = request.getParameter("support_get");
        String raw_trainer = request.getParameter("trainer_get");
        String raw_term = request.getParameter("term_get");
        String raw_status = request.getParameter("status");
        String raw_des = request.getParameter("description");
        String raw_subject = request.getParameter("sub_code");
        String raw_class_name = request.getParameter("class_name");

        String description = (raw_des != null && raw_des.length() > 0) ? raw_des : "";
        Integer support_id = (raw_support != null && !raw_support.equals("-1")) ? new Integer(raw_support) : new Integer("-1");
        Integer clas_id = (raw_class_id != null && !raw_class_id.equals("-1")) ? new Integer(raw_class_id) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");
        Integer trainer_id = (raw_trainer != null && !raw_trainer.equals("-1")) ? new Integer(raw_trainer) : new Integer("-1");
        Integer term = (raw_term != null && !raw_term.equals("-1")) ? new Integer(raw_term) : new Integer("-1");
        Integer subject_id = (raw_subject != null && !raw_subject.equals("-1")) ? new Integer(raw_subject) : new Integer("-1");

        request.setAttribute("descriptionset", description);
        request.setAttribute("sup", support_id);
        request.setAttribute("train", trainer_id);
        request.setAttribute("cla", clas_id);
        request.setAttribute("status", status);
        model.Class c = new model.Class();
        c.setClass_id(clas_id);
        c.setClass_name(raw_class_name);
        c.setSupporter_id(support_id);
        c.setTrainer_id(trainer_id);
        c.setTerm_id(term);
        if (status == 1) {
            c.setStatus(true);
        } else {
            c.setStatus(false);
        }
        c.setDescription(description);
        //add class Setting
        Subject sub = new Subject();
        sub.setSubject_id(subject_id);
        c.setSubject(sub);

        clDB.updatebyClass(c);
        c = clDB.getByClass(c);

        request.setAttribute("classset", c);
        response.sendRedirect("classlist?action=edit&class_id=" + clas_id + "#demo-modal2");
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
