/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SubjectDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.Subject;
import model.User;

/**
 *
 * @author HAICAO
 */
@MultipartConfig
public class SubjectController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    SubjectDAO dbSubject = new SubjectDAO();
    UserDAO dbUser = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Fake Login
//        User u = new User();
//        u.setUser_id(1);
//        u = dbUser.get(u);
        ////--User Login--/////
        User u = (User)request.getSession().getAttribute("user");
        u = dbUser.get(u);
        ////--User Login END--/////
        int role = u.getList().get(0).getSetting_id();
        request.getSession().setAttribute("role", role);
        String action = request.getParameter("action");
        ArrayList<User> managers = dbUser.getUserByRoleId(12);
        ArrayList<User> experts = dbUser.getUserByRoleId(14);
        request.getSession().setAttribute("managers", managers);
        request.getSession().setAttribute("experts", experts);
        switch (action) {
            case "edit":
                String id = request.getParameter("id");
                Subject sub = new Subject();
                sub.setSubject_id(Integer.parseInt(id));
                sub = dbSubject.get(sub);
                request.setAttribute("sub", sub);
                request.getRequestDispatcher("view/subject/subject_edit.jsp").forward(request, response);
                break;
            case "view":
                listSubject(request, response);
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
                editSubject_Post(request, response);
                break;
            case "add":
                addSubject_Post(request, response);
                break;
            case "upload_add":
                uploadIMG_add(request, response);
                break;
            case "upload":
                uploadIMG(request, response);
                break;
        }
    }

    
    //upload img for add action
    public void uploadIMG_add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("image");
        String realpath = request.getServletContext().getRealPath("/images");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (!Files.exists(Paths.get(realpath))) {
            Files.createDirectories(Paths.get(realpath));
        }
        if (filename.equals("")) {
            request.getSession().setAttribute("mess", "Please choose an image");
            response.sendRedirect("subject?action=add");
        } else {
            request.getSession().setAttribute("mess", "");
            request.getSession().setAttribute("add_img_sub", filename);
            part.write(realpath + "/" + filename);
            response.sendRedirect("subject?action=add&add_img_sub="+filename);
        }
    }
    
    //upload img for edit action
    public void uploadIMG(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("image");
        int id = Integer.parseInt(request.getParameter("img_sid"));
        String realpath = request.getServletContext().getRealPath("/images");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (!Files.exists(Paths.get(realpath))) {
            Files.createDirectories(Paths.get(realpath));
        }
        if (filename.equals("")) {
            request.getSession().setAttribute("mess", "Please choose an image");
            response.sendRedirect("subject?action=edit&id="+id);
        } else {
            request.getSession().setAttribute("mess", "");
            part.write(realpath + "/" + filename);
            SubjectDAO db = new SubjectDAO();
            db.changeImg(filename, id);
            request.setAttribute("id", id);
            response.sendRedirect("subject?action=edit&id="+id);
        }
    }
    
    //add subject
    public void addSubject_Post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("add_code_sub");
        String name = request.getParameter("add_name_sub");
        String raw_manager = request.getParameter("add_manager_sub");
        String filename = request.getParameter("add_img_sub");
        if (raw_manager == null || raw_manager.equals("")) {
            raw_manager = "-1";
        }
        String raw_expert = request.getParameter("add_expert_sub");
        if (raw_expert == null || raw_expert.equals("")) {
            raw_expert = "-1";
        }
        int manager = Integer.parseInt(raw_manager);
        int expert = Integer.parseInt(raw_expert);
        String body = request.getParameter("add_body_sub");
        
        request.getSession().setAttribute("add_code_sub", code);
        request.getSession().setAttribute("add_name_sub", name);
        request.getSession().setAttribute("add_manager_sub", manager);
        request.getSession().setAttribute("add_expert_sub", expert);
        request.getSession().setAttribute("add_body_sub", body);
        
        
        if (dbSubject.checkSubjectExist(code)) {
            request.setAttribute("code_mess", "Subject Code existed!");
            request.getRequestDispatcher("view/subject/subject_add.jsp").forward(request, response);
            return;
        }

        //add subject
        if (code != null && name != null && !code.equals("") && !name.equals("")) {
            int count = dbSubject.countID();
            User m = new User();
            m.setUser_id(manager);
            User e = new User();
            e.setUser_id(expert);
            Subject sub = new Subject(count + 1, code, name, m, e, "1", body, filename);
            dbSubject.insert(sub);
            request.setAttribute("code_mess", "");
            request.setAttribute("success_mess", "Add sucessfull !");
        }
        request.setAttribute("code_mess", "");
        request.getRequestDispatcher("view/subject/subject_add.jsp").forward(request, response);
    }
    
    //edit subject
    public void editSubject_Post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int role = (int) request.getSession().getAttribute("role");
        String id = request.getParameter("sid");
        request.setAttribute("id", id);
        Subject s = new Subject();
        s.setSubject_id(Integer.parseInt(id));
        s = dbSubject.get(s);
        String raw_code = s.getSubject_code();
        if (role == 11) {
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            //check if subject code existed
            if (!raw_code.equals(code) && dbSubject.checkSubjectExist(code)) {
                request.getSession().setAttribute("code_mess", "Subject code existed!");
                response.sendRedirect("subject?action=edit&id=" + id);
                return;
            }
            int mger_id = Integer.parseInt(request.getParameter("manager"));
            User manager = new User();
            manager.setUser_id(mger_id);
            int ex_id = Integer.parseInt(request.getParameter("expert"));
            User expert = new User();
            expert.setUser_id(ex_id);
            String raw_status = request.getParameter("status");
            String status;
            if (raw_status.equals("active")) {
                status = "1";
            } else {
                status = "0";
            }
            String body = request.getParameter("body");
            ///-----------/////

            ///------------////
            s = new Subject(Integer.parseInt(id), code, name, manager, expert, status, body, "");
            dbSubject.update(s);
        } else {
            int ex_id = Integer.parseInt(request.getParameter("expert"));
            User expert = new User();
            expert.setUser_id(ex_id);
            String raw_status = request.getParameter("status");
            String status;
            if (raw_status.equals("active")) {
                status = "1";
            } else {
                status = "0";
            }
            s = new Subject(Integer.parseInt(id), expert, status);
            dbSubject.updateForManager(s);
        }
        request.getSession().setAttribute("code_mess", "");
        request.setAttribute("success_mess", "Update sucessfull");
        s = dbSubject.get(s);
        request.setAttribute("sub", s);
        request.getRequestDispatcher("view/subject/subject_edit.jsp").forward(request, response);
    }

    //view list subject
    public void listSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search_txt = request.getParameter("txtsearch");
        if (search_txt == null || search_txt.equals("") || search_txt.equals("undefined")) {
            search_txt = "";
        }
        //filter
        String f_manager = request.getParameter("filter_manager");
        String f_expert = request.getParameter("filter_expert");
        String f_status = request.getParameter("filter_status");
        Integer manager = (f_manager!=null && !f_manager.equals("-1"))?new Integer(f_manager):new Integer("-1");
        Integer expert = (f_expert!=null && !f_expert.equals("-1"))?new Integer(f_expert):new Integer("-1");
        f_status = (f_status!=null && !f_status.equals("-1"))?f_status:"-1";
        request.setAttribute("filter_manager", manager);
        request.setAttribute("filter_expert", expert);
        request.setAttribute("filter_status", f_status);
        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 6;
        int count = dbSubject.count(search_txt,manager,expert,f_status);
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;

        //check change status
        String status_id = request.getParameter("change_status");
        if (status_id != null) {
            String[] arr = status_id.split("_");
            String id = arr[0];
            String raw_status = arr[1];
            dbSubject.changeStatus(Integer.parseInt(id), raw_status);
        }
        //get subject list
        ArrayList<Subject> subs = dbSubject.pagging(page_size, page_index, search_txt,manager,expert,f_status);
        request.setAttribute("f_manager", manager);
        request.setAttribute("f_expert", expert);
        request.setAttribute("f_status", f_status);
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("subs", subs);
        request.setAttribute("total_page", total_page);
        request.setAttribute("count", count);
        request.setAttribute("txtsearch", search_txt);
        request.getRequestDispatcher("view/subject/subject_list.jsp").forward(request, response);
    }

}
