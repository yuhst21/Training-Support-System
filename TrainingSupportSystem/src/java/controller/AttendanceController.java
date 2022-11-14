/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendanceDAO;
import dal.ScheduleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;
import model.Class;
import model.Schedule;
import model.User;

/**
 *
 * @author mavjp
 */
public class AttendanceController extends HttpServlet {

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
    UserDAO dbUser = new UserDAO();
    ScheduleDAO dbSche = new ScheduleDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        switch (action) {
            case "tracking":
                tracking(request, response);
                break;
            case "detail":
                String schedule_id = request.getParameter("schedule");
                Schedule sche = dbAttend.getSchedule(Integer.parseInt(schedule_id));
                ArrayList<User> stus = dbAttend.listStudentByClass(sche.getClasss().getClass_id());
                ArrayList<Attendance> attends = dbAttend.list(sche.getClasss().getClass_id());

                //
                for (User stu : stus) {
                    stu.getAttends().clear();
                    for (Attendance attend : attends) {
                        if (attend.getTrainee().getUser_id() == stu.getUser_id()) {
                            stu.getAttends().add(attend);
                        }
                    }
                }
                //
                request.getSession().setAttribute("sche", sche);
                request.getSession().setAttribute("students", stus);
                request.getRequestDispatcher("view/attendance/attend_detail.jsp").forward(request, response);
                break;

            case "schedule":

                //
                ArrayList<Class> cs = dbAttend.listClassByTrainee(u.getUser_id());

                int class_id;
                String raw_id = request.getParameter("class");
                if (raw_id == null) {
                    class_id = cs.get(0).getClass_id();
                } else {
                    class_id = Integer.parseInt(raw_id);
                }
                ArrayList<Attendance> attend = dbAttend.list(class_id);
                for (Attendance attendance : attend) {
                    if (attendance.getTrainee().getUser_id() == u.getUser_id()) {
                        u.getAttends().add(attendance);
                    }
                }
                ArrayList<Schedule> schedules = dbAttend.listScheduleByClass(class_id);
                request.setAttribute("class_id", class_id);
                request.setAttribute("stu", u);
                request.setAttribute("schedules", schedules);
                request.setAttribute("cs", cs);
                request.getRequestDispatcher("view/attendance/attend_schedule.jsp").forward(request, response);
                break;
            case "attend":
                attend(request, response);
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
    AttendanceDAO dbAttend = new AttendanceDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "detail":
                ArrayList<User> stus = (ArrayList<User>) request.getSession().getAttribute("students");
                Schedule sche = (Schedule) request.getSession().getAttribute("sche");
                for (User stu : stus) {
                    Attendance attendance = new Attendance();
                    boolean attend = request.getParameter("check_" + stu.getUser_id()).equals("true");
                    String comment = request.getParameter("comment_" + stu.getUser_id());
                    attendance.setStatus(attend);
                    attendance.setComment(comment);
                    attendance.setSchedule(sche);
                    attendance.setClass_id(sche.getClasss());
                    attendance.setTrainee(stu);
                    sche.setStatus(true);

                    if (!dbAttend.isExist(attendance)) {
                        dbAttend.insert(attendance);
                    } else {
                        dbAttend.update(attendance);
                    }
                }
                dbAttend.updateScheduleAttendStatus(true, sche.getSchedule_id());
                request.getSession().removeAttribute("students");
                request.getSession().removeAttribute("sche");
                response.sendRedirect("attend?action=attend");
                //response.sendRedirect("attend?action=detail&schedule=" + sche.getSchedule_id());
                break;
        }
    }

    protected void tracking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        ArrayList<Class> cs = dbAttend.listClass(u.getUser_id());
        request.getSession().setAttribute("cs", cs);
        //
        int class_id = 0;
        String raw_id = request.getParameter("class");
        if (raw_id == null && !cs.isEmpty()) {
            class_id = cs.get(0).getClass_id();
        } else if (raw_id != null && !cs.isEmpty()) {
            class_id = Integer.parseInt(raw_id);
        } else {
            request.getRequestDispatcher("view/attendance/attend_tracking.jsp").forward(request, response);
        }

        ArrayList<User> stus = dbAttend.listStudentByClass(class_id);
        ArrayList<Schedule> schedules = dbAttend.listScheduleByClass(class_id);
        ArrayList<Attendance> attends = dbAttend.list(class_id);

        for (User stu : stus) {
            stu.getAttends().clear();
            for (Attendance attend : attends) {
                if (attend.getTrainee().getUser_id() == stu.getUser_id()) {
                    stu.getAttends().add(attend);
                }
            }
        }

        request.setAttribute("students", stus);
        request.setAttribute("class_id", class_id);
        request.setAttribute("schedule", schedules);
        //
        request.getRequestDispatcher("view/attendance/attend_tracking.jsp").forward(request, response);
    }
    
    protected void attend(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        u = dbUser.get(u);
        ArrayList<Class> cs = dbAttend.listClass(u.getUser_id());
        
        ArrayList<Schedule> schedules = dbSche.getScheduleDaily(u.getUser_id());

        request.setAttribute("cs", cs);
        request.setAttribute("schedules", schedules);
        //
        request.getRequestDispatcher("view/attendance/take_attendance.jsp").forward(request, response);
    }

}
