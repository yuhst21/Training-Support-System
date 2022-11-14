/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MileStoneDAO;
import dal.ScheduleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Schedule;
import model.TimeSlot;
import model.Week;
import model.Class;
import model.Room;
import model.Subject;
import model.User;

/**
 *
 * @author ACER
 */
public class ScheduleController extends HttpServlet {

    private String mess1 = "";

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
            out.println("<title>Servlet ScheduleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p id=\"noti\" style=\"color: red\">nhap it thoi</p>");

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
            case "list": {
                UserDAO udb = new UserDAO();
                ScheduleDAO sdb = new ScheduleDAO();

                User user = (User) request.getSession().getAttribute("account");

                if (udb.getRoleId(user.getUser_id()) == 14 || udb.getRoleId(user.getUser_id()) == 13) {

                    ArrayList<Class> clist = sdb.listClass(user.getUser_id());
                    request.setAttribute("clist", clist);
                    String raw_class = request.getParameter("class_id");
                    String raw_status = request.getParameter("status");
                    String raw_slot = request.getParameter("slot_id");

                    if (raw_class == null || raw_class.equals("")) {
                        if (clist.size() > 0) {
                            raw_class = clist.get(0).getClass_id() + "";
                        } else {
                            raw_class = "-1";
                        }
                    }
                    if (raw_status == null || raw_status.equals("all") || raw_status.equals("")) {
                        raw_status = "-1";
                    }

                    if (raw_slot == null || raw_slot.equals("")) {
                        raw_slot = "-1";
                    }

                    String raw_page = request.getParameter("page");
                    if (raw_page == null || raw_page.equals("")) {
                        raw_page = "1";
                    }
                    int page_index = Integer.parseInt(raw_page);
                    int page_size = 4;
                    int count = sdb.count(Integer.parseInt(raw_class), raw_status, Integer.parseInt(raw_slot));
                    String status_id = request.getParameter("change_status");
                    if (status_id != null) {
                        String[] arr = status_id.split("_");
                        String id = arr[0];
                        String status = arr[1];
                        sdb.changeStatus(Integer.parseInt(id), status);
                    }

                    int total_page = (count % page_size == 0) ? (count / page_size) : (count / page_size) + 1;
                    ArrayList<Schedule> slist = sdb.pagging(Integer.parseInt(raw_class), raw_status, Integer.parseInt(raw_slot), page_size, page_index);
                    ArrayList<TimeSlot> tlist = sdb.slotList();
                    request.setAttribute("page_size", page_size);
                    request.setAttribute("page_index", page_index);
                    request.setAttribute("total_page", total_page);
                    request.setAttribute("slist", slist);
                    request.setAttribute("tlist", tlist);
                    request.setAttribute("count", count);
                    request.setAttribute("class_id", raw_class);

                    request.setAttribute("slot_id", raw_slot);
                    request.setAttribute("status", raw_status);
                    request.getRequestDispatcher("view/schedule/schedule_view.jsp").forward(request, response);

                } else {
                    response.getWriter().print("access denied");

                }
                break;
            }
            case "edit": {
                UserDAO udb = new UserDAO();
                ScheduleDAO sdb = new ScheduleDAO();

                User user = (User) request.getSession().getAttribute("account");

                if (udb.getRoleId(user.getUser_id()) == 14 || udb.getRoleId(user.getUser_id()) == 13) {
                    MileStoneDAO mdb = new MileStoneDAO();
                    int id = Integer.parseInt(request.getParameter("id"));
                    ArrayList<TimeSlot> tlist = sdb.slotList();
                    ArrayList<Room> rlist = sdb.rooms();
                    Schedule s = sdb.getById(id);
                    request.setAttribute("mess1", mess1);
                    request.setAttribute("schedule", s);
                    request.setAttribute("tlist", tlist);
                    request.setAttribute("rlist", rlist);
                    request.getRequestDispatcher("view/schedule/schedule_edit.jsp").forward(request, response);
                } else {
                    response.getWriter().print("you dont have permission to do this function");
                }
                break;
            }

            case "add": {

                UserDAO udb = new UserDAO();
                ScheduleDAO sdb = new ScheduleDAO();

                User user = (User) request.getSession().getAttribute("account");

                if (udb.getRoleId(user.getUser_id()) == 14 || udb.getRoleId(user.getUser_id()) == 13) {

                    user = (User) request.getSession().getAttribute("account");
                    MileStoneDAO mdb = new MileStoneDAO();
                    ArrayList<TimeSlot> tlist = sdb.slotList();
                    ArrayList<Room> rlist = sdb.rooms();

                    ArrayList<Class> clist = sdb.listClass(user.getUser_id());
                    ArrayList<Subject> slist = sdb.subjectList();
                    request.setAttribute("clist", clist);
                    request.setAttribute("slist", slist);
                    request.setAttribute("rlist", rlist);
                    request.setAttribute("tlist", tlist);

                    request.getRequestDispatcher("view/schedule/schedule_add.jsp").forward(request, response);

                } else {
                    response.getWriter().print("you dont have permission to do this function");
                }
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

            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                int class_id = Integer.parseInt(request.getParameter("class_id"));
                String class_code = request.getParameter("class_code");
                int slot_id = Integer.parseInt(request.getParameter("slot_id"));
                String subject_code = request.getParameter("subject_code");
                Date date = Date.valueOf(request.getParameter("date"));
                int room = Integer.parseInt(request.getParameter("room"));
                String from_time = request.getParameter("from_time");
                String to_time = request.getParameter("to_time");
                String topic = request.getParameter("topic");
                String note = request.getParameter("note");
                boolean status = false;
                if (request.getParameter("status").equals("active")) {
                    status = true;
                }
                Schedule s = new Schedule();
                s.setSchedule_id(id);
                Class c = new Class();
                c.setClass_id(class_id);
                s.setClasss(c);
                TimeSlot slot = new TimeSlot();
                slot.setSlotid(slot_id);
                s.setSlot(slot);
                s.setTraining_date(date);
                Room r = new Room();
                r.setRoom_id(room);
                s.setRoom(r);
                s.setFrom_time(from_time);
                s.setTo_time(to_time);
                s.setStatus(status);
                s.setTopic(topic);
                s.setNote(note);
                ScheduleDAO sdb = new ScheduleDAO();
                sdb.saveChange(s);
                response.sendRedirect("schedule?action=edit&id=" + id+"#demo-modal2");

                break;

            }
            case "add": {
//                int id = Integer.parseInt(request.getParameter("id"));
                int class_id = Integer.parseInt(request.getParameter("class_id"));
                int slot_id = Integer.parseInt(request.getParameter("slot_id"));
//                String subject_code = request.getParameter("subject_code");
                Date date = Date.valueOf(request.getParameter("date"));
                int room = Integer.parseInt(request.getParameter("room"));
                String from_time = request.getParameter("from_time");
                String to_time = request.getParameter("to_time");
//                String room_name = request.getParameter("room_name");
                String topic = request.getParameter("topic");
                String note = request.getParameter("note");
                boolean status = false;
                if (request.getParameter("status").equals("active")) {
                    status = true;
                }
                Schedule s = new Schedule();

                Class c = new Class();
                c.setClass_id(class_id);
                s.setClasss(c);
                TimeSlot slot = new TimeSlot();
                slot.setSlotid(slot_id);
                s.setSlot(slot);
                s.setTraining_date(date);
                Room r = new Room();
                r.setRoom_id(room);
                s.setRoom(r);
                s.setFrom_time(from_time);
                s.setTo_time(to_time);
                s.setStatus(status);
                s.setTopic(topic);
                s.setNote(note);
                ScheduleDAO sdb = new ScheduleDAO();
                sdb.addNew(s);
                response.sendRedirect("schedule?action=list#demo-modal2");
//                if(from_time.compareTo(to_time)>0){
//                    
//                } else if ()

            }

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
