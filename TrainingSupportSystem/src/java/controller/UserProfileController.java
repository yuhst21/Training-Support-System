/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import dal.UserProfileDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import model.User;

/**
 *
 * @author ACER
 */
@MultipartConfig
public class UserProfileController extends HttpServlet {

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
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
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
        switch (action) {
            case "profile": {
                Profile(request, response);
                break;
            }
            case "changepass": {
                request.getRequestDispatcher("view/user/changepassword.jsp").forward(request, response);
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
        switch (action) {
            case "upload": {
                try {
                    Part part = request.getPart("image");
                    String realpath = request.getServletContext().getRealPath("/images");
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

                    if (!Files.exists(Paths.get(realpath))) {
                        Files.createDirectories(Paths.get(realpath));
                    }

                    if (filename.equals("")) {

                        request.setAttribute("mess", "Please choose an image");
                        request.getRequestDispatcher("view/user/changepass.jsp").forward(request, response);
                    } else {
                        part.write(realpath + "/" + filename);
                        HttpSession session = request.getSession();

                        User user = (User) session.getAttribute("user");

                        UserProfileDAO usdb = new UserProfileDAO();
                        usdb.ChangeAvatar(filename, user.getUser_id());
                        UserDAO udb = new UserDAO();
                        session.setAttribute("user", udb.get(user));

                        Path imagesPath = Paths.get("C:\\Users\\win\\Desktop\\swp391\\g1\\TrainingSupportSystem\\build\\web\\images\\" + user.getAvatar_url());
                        try {
                            Files.delete(imagesPath);

                        } catch (IOException e) {

                        }
                        response.sendRedirect("user?action=profile#demo-modal2");
                    }
                } catch (Exception e) {
                }
                break;
            }
            case "savechange": {
                saveChange(request, response);
                break;
            }
            case "changepass": {
                changePass(request, response);
                break;
            }

            case "ajax": {

                break;
            }

        }
    }

    protected void Profile(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            UserProfileDAO udb = new UserProfileDAO();

            User user = (User) session.getAttribute("user");
            UserDAO db = new UserDAO();
            request.setAttribute("pass", udb.decode(db.getPassword(user.getUser_id())));
            request.getRequestDispatcher("view/user/changepass.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    protected void upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part part = request.getPart("image");
            String realpath = request.getServletContext().getRealPath("/images");
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Paths.get(realpath))) {
                Files.createDirectories(Paths.get(realpath));
            }

            if (filename.equals("")) {

                request.setAttribute("mess", "Please choose an image");
                request.getRequestDispatcher("view/userprofile.jsp").forward(request, response);
            } else {
                part.write(realpath + "/" + filename);
                HttpSession session = request.getSession();

                User user = (User) session.getAttribute("user");

                UserProfileDAO usdb = new UserProfileDAO();
                usdb.ChangeAvatar(filename, user.getUser_id());
                UserDAO udb = new UserDAO();
                session.setAttribute("user", udb.get(user));

                Path imagesPath = Paths.get("C:\\swp\\project2\\g1\\TrainingSupportSystem\\build\\web\\images\\" + user.getAvatar_url());
                try {
                    Files.delete(imagesPath);

                } catch (IOException e) {

                }
                response.sendRedirect("user?action=profile#demo-modal2");

            }
        } catch (Exception e) {
        }
    }

    protected void saveChange(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            UserProfileDAO usdb = new UserProfileDAO();
            UserDAO udb = new UserDAO();
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");

            String company = request.getParameter("company");
            String position = request.getParameter("position");
            String note = request.getParameter("note");
            usdb.SaveChange(name, address, email, company, position, note, user.getUser_id());
            request.getSession().setAttribute("user", udb.get(user));
            response.sendRedirect("user?action=profile#demo-modal2");
        } catch (Exception e) {
        }
    }

    protected void changePass(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserProfileDAO usdb = new UserProfileDAO();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            UserDAO db = new UserDAO();
            UserProfileDAO udb = new UserProfileDAO();
            request.setAttribute("user", user);
            String oldpass = request.getParameter("oldpass");
            String newpass = request.getParameter("newpass");
            String renewpass = request.getParameter("renewpass");

            if (!oldpass.equals(udb.decode(db.getPassword(user.getUser_id())))) {
                request.setAttribute("mess1", "Your current password is not correct");
                request.setAttribute("oldpass", oldpass);
                request.setAttribute("newpass", newpass);
                request.setAttribute("renewpass", renewpass);

                request.getRequestDispatcher("view/changepassword.jsp").forward(request, response);
            } else {
                if (!newpass.equals(renewpass)) {
                    request.setAttribute("mess2", "Your retype password is not correct!");
                    request.setAttribute("oldpass", oldpass);
                    request.setAttribute("newpass", newpass);
                    request.setAttribute("renewpass", renewpass);

                    request.getRequestDispatcher("view/changepassword.jsp").forward(request, response);

                }
            }
            if (oldpass.equals(udb.decode(db.getPassword(user.getUser_id()))) && newpass.equals(renewpass)) {
                udb.ChangePassword(udb.Encode(newpass), user.getUser_id());
                response.sendRedirect("logout");
            }

        } catch (Exception e) {
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
