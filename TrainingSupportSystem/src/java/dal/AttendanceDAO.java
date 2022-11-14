/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.User;
import model.Class;
import model.Room;
import model.Schedule;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author mavjp
 */
public class AttendanceDAO extends DBContext<Attendance> {

    @Override
    public ArrayList<Attendance> list() {
        try {
            ArrayList<Attendance> list = new ArrayList<>();
            String sql = "select * from attendance";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class cs = new Class();
                cs.setClass_id(rs.getInt("class_id"));
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt("schedule_id"));
                User trainee = new User();
                trainee.setUser_id(rs.getInt("trainee_id"));
                Attendance a = new Attendance(cs, s, trainee, rs.getBoolean("status"), rs.getString("comment"));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Attendance> list(int class_id) {
        try {
            ArrayList<Attendance> list = new ArrayList<>();
            String sql = "select * from attendance where class_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class cs = new Class();
                cs.setClass_id(rs.getInt("class_id"));
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt("schedule_id"));
                User trainee = new User();
                trainee.setUser_id(rs.getInt("trainee_id"));
                Attendance a = new Attendance(cs, s, trainee, rs.getBoolean("status"), rs.getString("comment"));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Attendance> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance entity) {
        try {

            String sql = "INSERT INTO `attendance`\n"
                    + "(`class_id`,\n"
                    + "`trainee_id`,\n"
                    + "`schedule_id`,\n"
                    + "`status`,\n"
                    + "`comment`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getClass_id().getClass_id());
            stm.setInt(2, entity.getTrainee().getUser_id());
            stm.setInt(3, entity.getSchedule().getSchedule_id());
            stm.setBoolean(4, entity.isStatus());
            stm.setString(5, entity.getComment());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        try {

            String sql = "UPDATE `attendance`\n"
                    + "SET\n"
                    + "`class_id` = ?,\n"
                    + "`trainee_id` = ?,\n"
                    + "`schedule_id` =?,\n"
                    + "`status` =?,\n"
                    + "`comment` = ?\n"
                    + "WHERE `class_id` = ? AND `trainee_id` = ? AND `schedule_id` = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getClass_id().getClass_id());
            stm.setInt(2, entity.getTrainee().getUser_id());
            stm.setInt(3, entity.getSchedule().getSchedule_id());
            stm.setBoolean(4, entity.isStatus());
            stm.setString(5, entity.getComment());
            stm.setInt(6, entity.getClass_id().getClass_id());
            stm.setInt(7, entity.getTrainee().getUser_id());
            stm.setInt(8, entity.getSchedule().getSchedule_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countSchedule(int class_id) {
        try {

            String sql = "SELECT COUNT(*) as total FROM schedule where class_id = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Class> listClass(int id) {
        try {
            ArrayList<Class> cs = new ArrayList<>();
            String sql = "select class_id,class_code from class where trainer_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                cs.add(c);
            }
            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Class> listClassByTrainee(int id) {
        try {
            ArrayList<Class> cs = new ArrayList<>();
            String sql = "SELECT c.class_id, class_code \n"
                    + "FROM  class_user cu inner join class c on cu.class_id = c.class_id \n"
                    + "where cu.user_id = ? ";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                cs.add(c);
            }
            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> listStudentByClass(int class_id) {
        ArrayList<User> users;
        try {
            users = new ArrayList<>();
            String sql = "select username,email, avatar_url, u.user_id, full_name from user u inner join user_role r on u.user_id = r.user_id \n"
                    + "					inner join class_user c on c.user_id = u.user_id\n"
                    + "where role_id = 15 and class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                u.setAvatar_url(rs.getString("avatar_url"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                users.add(u);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Schedule> listScheduleByClass(int class_id) {
        ArrayList<Schedule> schedules;
        try {
            schedules = new ArrayList<>();
            String sql = "select schedule_id,s.class_id,s.slot_id,cs.setting_title,s.room_id,st.setting_title,s.training_date,from_time,to_time,s.status,s.is_attend,subject_name \n"
                    + "from schedule s \n"
                    + "inner join  class_setting cs on s.slot_id = cs.setting_id \n"
                    + "inner join  setting st on s.room_id=st.setting_id\n"
                    + "inner join  class c on s.class_id = c.class_id\n"
                    + "inner join subject sj on sj.subject_id = c.subject_id\n"
                    + "where s.class_id= ? and s.status =1 order by training_date asc";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt(1));
                Class c = new Class();
                c.setClass_id(rs.getInt(2));
                s.setClasss(c);
                Subject sj = new Subject();
                sj.setSubject_name(rs.getString("subject_name"));
                c.setSubject(sj);
                TimeSlot tl = new TimeSlot();
                tl.setSlotid(rs.getInt(3));
                tl.setSlotname(rs.getString(4));
                s.setSlot(tl);
                Room r = new Room();
                r.setRoom_id(rs.getInt(5));
                r.setRoom_name(rs.getString(6));
                s.setRoom(r);
                s.setTraining_date(rs.getDate(7));
                s.setFrom_time(rs.getString(8));
                s.setTo_time(rs.getString(9));
                s.setStatus(rs.getBoolean(10));
                s.setIs_attend(rs.getBoolean(11));
                schedules.add(s);
            }
            return schedules;

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    public Schedule getSchedule(int sche_id) {
        try {
            Schedule sche = new Schedule();
            String sql = "select schedule_id,c.class_id,class_code \n"
                    + "from schedule s inner join class c on s.class_id = c.class_id where schedule_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, sche_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                sche.setSchedule_id(rs.getInt("schedule_id"));
                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                sche.setClasss(c);
            }
            return sche;

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateScheduleAttendStatus(boolean status, int schedule_id) {
        try {

            String sql = "UPDATE `schedule`\n"
                    + "SET\n"
                    + "`is_attend` = ?\n"
                    + "WHERE `schedule_id` = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, schedule_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isExist(Attendance a) {
        try {

            String sql = "select * from attendance where class_id = ? and trainee_id = ? and schedule_id = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, a.getClass_id().getClass_id());
            stm.setInt(2, a.getTrainee().getUser_id());
            stm.setInt(3, a.getSchedule().getSchedule_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
