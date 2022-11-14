/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contact;

import dal.CategoryDAO;
import common.EmailAutoSend;
import dal.UserDAO;
import dal.WebContactDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.User;
import model.WebContact;

/**
 *
 * @author win
 */
public class WebContactListController extends HttpServlet {

    EmailAutoSend eas = new EmailAutoSend();
    WebContactDAO wDB = new WebContactDAO();
    CategoryDAO cDB = new CategoryDAO();
    UserDAO dbUser = new UserDAO();

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
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        int role = u.getList().get(0).getSetting_id();
        request.getSession().setAttribute("role", role);
        ArrayList<User> supporter = dbUser.getUserByRoleId(13);
        request.getSession().setAttribute("supporter", supporter);
        String action = request.getParameter("action");
        switch (action) {
            case "edit":
                String contact_id = request.getParameter("contact_id");
                WebContact wc = new WebContact();
                wc.setContact_id(Integer.parseInt(contact_id));
                request.setAttribute("maincontact", wDB.get(wc));
                request.getRequestDispatcher("view/admin/webcontact/webdetail.jsp").forward(request, response);
                break;
            case "view":
                listWebContact(request, response);
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
                editWebContact(request, response);
                break;

        }
    }

    protected void listWebContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cate", cDB.list());
        request.setAttribute("web", wDB.list());
        //search
        String raw_cusname = request.getParameter("cusname");
        String raw_category = request.getParameter("category");
        String raw_status = request.getParameter("satus_web");
        String raw_supporter = request.getParameter("supporter");//M F All
        String customer_name = (raw_cusname != null && raw_cusname.length() > 0) ? raw_cusname : "";
        Integer category = (raw_category != null && !raw_category.equals("-1")) ? new Integer(raw_category) : new Integer("-1");
        Integer supporter = (raw_supporter != null && !raw_supporter.equals("-1")) ? new Integer(raw_supporter) : new Integer("-1");
        Integer status = (raw_status != null && !raw_status.equals("-1")) ? new Integer(raw_status) : new Integer("-1");

        //paging
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.equals("")) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 5;
        int count = wDB.count(customer_name, category, supporter, status);
        int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
        ArrayList<WebContact> webs = wDB.paggingV2(page_size, page_index, customer_name, category, supporter, status);
        request.setAttribute("page_size", page_size);
        request.setAttribute("page_index", page_index);
        request.setAttribute("webs", webs);
        request.setAttribute("total_page", total_page);
        request.setAttribute("cusname", customer_name);
        request.setAttribute("catego", category);
        request.setAttribute("sup", supporter);
        request.setAttribute("sta", status);
        request.setAttribute("count", count);
        request.getRequestDispatcher("view/admin/webcontact/weblist.jsp").forward(request, response);

    }

    protected void editWebContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        String contact_id = request.getParameter("contact_id_per");
        String raw_response = request.getParameter("message_per");
        //Validate
        String reponse_user = (raw_response != null && raw_response.length() > 0) ? raw_response : null;
//        Integer supporter_id = (raw_supporter_id != null && raw_supporter_id.length() > 0)
//                ? new Integer(raw_supporter_id) : null;
        WebContact wc = new WebContact();
        wc.setContact_id(Integer.parseInt(contact_id));
        wc = wDB.get(wc);
        
        eas.sendEmailBySupporter(wc.getEmail(), reponse_user, u.getUsername());
        wc.setRespones(reponse_user);
        wDB.updateResponse(wc,u.getUser_id());
        request.setAttribute("maincontact", wDB.get(wc));
        response.sendRedirect("weblist?action=edit&contact_id=" + wc.getContact_id() + "#demo-modal2");      
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
