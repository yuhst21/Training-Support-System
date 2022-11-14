/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;
import model.Schedule;
import model.TimeSlot;
import model.Class;
import model.Subject;

/**
 *
 * @author ACER
 */
public class ScheduleDAO extends DBContext<Schedule> {

    public ArrayList<TimeSlot> slotList() {
        ArrayList<TimeSlot> list = new ArrayList<>();
        try {
            String sql = "select setting_id,setting_title from class_setting where type_id=60";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TimeSlot tl = new TimeSlot();
                tl.setSlotid(rs.getInt(1));
                tl.setSlotname(rs.getString(2));
                list.add(tl);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Schedule> pagging(int class_id, String status, int slot_id, int page_size, int page_index) {
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "select schedule_id,s.class_id,s.slot_id,cs.setting_title,s.room_id,st.setting_title,s.training_date,s.from_time,s.to_time,s.status,s.is_attend,s.topic,s.note from schedule s \n"
                    + "inner join  class_setting cs on s.slot_id = cs.setting_id \n"
                    + "inner join  setting st on s.room_id=st.setting_id\n"
                    + "where s.class_id=?";

            HashMap<Integer, Object> params = new HashMap<>();
            Integer count = 1;
            if (!status.equals("-1")) {
                boolean check = false;
                if (status.equals("active")) {
                    check = true;
                }
                count++;
                sql += " and s.status=?";
                params.put(count, check);
            }

            if (slot_id != -1) {
                count++;
                sql += " and slot_id=?";
                params.put(count, slot_id);
            }

            sql += " limit ?,?";
            params.put(count + 1, page_size * (page_index - 1));
            params.put(count + 2, page_size);

            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt(1));
                Class c = new Class();
                c.setClass_id(rs.getInt(2));
                s.setClasss(c);
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
                s.setTopic(rs.getString(12));
                s.setNote(rs.getString(13));
                list.add(s);

            }

        } catch (Exception e) {
        }
        return list;

    }

    public int count(int class_id, String status, int slot_id) {
        try {
            String sql = "SELECT COUNT(*) as total FROM schedule where class_id=?";
            HashMap<Integer, Object> params = new HashMap<>();
            Integer count = 1;
            if (!status.equals("-1")) {
                boolean check = false;
                if (status.equals("active")) {
                    check = true;
                }
                count++;
                sql += " and status=?";
                params.put(count, check);
            }

            if (slot_id != -1) {
                count++;
                sql += " and slot_id=?";
                params.put(count, slot_id);
            }

            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
        }
        return -1;

    }

    public void changeStatus(int subject_id, String status) {
        try {
            String sql = "update schedule set status=? where schedule_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, subject_id);
            if (status.equals("true")) {
                stm.setBoolean(1, false);
            } else {
                stm.setBoolean(1, true);
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Class> listClass(int id) {
        ArrayList<Class> clist = new ArrayList<>();
        try {
            String sql = "select * from class where supporter_id=? or trainer_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class c = new Class();
                c.setClass_id(rs.getInt(1));
                c.setClass_name(rs.getString(2));
                clist.add(c);
            }

        } catch (Exception e) {
        }
        return clist;
    }

    public Schedule getById(int id) {
        try {
            String sql = "select schedule_id,s.class_id,c.class_code,s.slot_id,cs.setting_title,s.room_id,st.setting_title,s.training_date,s.from_time,s.to_time,s.status,s.is_attend,s.topic,s.note from schedule s \n"
                    + "                   inner join  class_setting cs on s.slot_id = cs.setting_id \n"
                    + "                    inner join  setting st on s.room_id=st.setting_id\n"
                    + "                    inner join class c on s.class_id=c.class_id\n"
                    + "                   where schedule_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt(1));
                Class c = new Class();
                c.setClass_id(rs.getInt(2));
                c.setClass_name(rs.getString(3));
                s.setClasss(c);
                TimeSlot tl = new TimeSlot();
                tl.setSlotid(rs.getInt(4));
                tl.setSlotname(rs.getString(5));
                s.setSlot(tl);
                Room r = new Room();
                r.setRoom_id(rs.getInt(6));
                r.setRoom_name(rs.getString(7));
                s.setRoom(r);
                s.setTraining_date(rs.getDate(8));
                s.setStatus(rs.getBoolean(11));
                s.setIs_attend(rs.getBoolean(12));
                s.setTopic(rs.getString(13));
                s.setNote(rs.getString(14));
                return s;

            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Room> rooms() {
        ArrayList<Room> list = new ArrayList<>();
        try {
            String sql = "select setting_id,setting_title from setting s where type_id=7;";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoom_id(rs.getInt(1));
                r.setRoom_name(rs.getString(2));
                list.add(r);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public void saveChange(Schedule s) {
        try {
            String sql = "UPDATE `schedule`\n"
                    + "SET\n"
                    + "`slot_id` = ?,\n"
                    + "`room_id` = ?,\n"
                    + "`training_date` =?,\n"
                    + "`from_time` =?,\n"
                    + "`to_time` = ?,\n"
                    + "`status` = ?,\n"
                    + "`topic` = ?,\n"
                    + "`note` = ?\n"
                    + "WHERE `schedule_id` = ?";
            PreparedStatement stm = conection.prepareCall(sql);
            stm.setInt(2, s.getRoom().getRoom_id());
            stm.setInt(1, s.getSlot().getSlotid());
            stm.setDate(3, s.getTraining_date());
            stm.setString(4, s.getFrom_time());
            stm.setString(5, s.getTo_time());
            stm.setBoolean(6, s.isStatus());
            stm.setString(7, s.getTopic());
            stm.setString(8, s.getNote());
            stm.setInt(9, s.getSchedule_id());

            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public Schedule CheckExisted(Schedule s, boolean checkroom) {
        try {
            String sql = "select * from schedule where slot_id=? and training_date=? and status=?";
            if (checkroom == false) {
                sql += " and class_id=?";

            } else {
                sql += " and room_id=?";
            }

            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, s.getSlot().getSlotid());
            stm.setDate(2, s.getTraining_date());
            stm.setBoolean(3, s.isStatus());

            if (checkroom == false) {
                stm.setInt(4, s.getClasss().getClass_id());
            } else {
                stm.setInt(4, s.getRoom().getRoom_id());
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule sh = new Schedule();
                sh.setSchedule_id(rs.getInt(1));
                return sh;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public Schedule CheckExisted2(Schedule s) {
        try {
            String sql = "select * from schedule where  slot_id=? and training_date=? and room_id=?";

            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, s.getSlot().getSlotid());
            stm.setDate(2, s.getTraining_date());
            stm.setInt(3, s.getRoom().getRoom_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule sh = new Schedule();
                sh.setSchedule_id(rs.getInt(1));
                return sh;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Subject> subjectList() {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM subject";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt(1));
                s.setSubject_name(rs.getString(2));
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addNew(Schedule s) {
        try {
            String sql = "INSERT INTO `schedule`\n"
                    + "(\n"
                    + "`class_id`,\n"
                    + "`slot_id`,\n"
                    + "`room_id`,\n"
                    + "`training_date`,\n"
                    + "`from_time`,\n"
                    + "`to_time`,\n"
                    + "`status`,\n"
                    + "`is_attend`,\n"
                    + "`topic`,\n"
                    + "`note`)\n"
                    + "VALUES\n"
                    + "(\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, s.getClasss().getClass_id());
            stm.setInt(2, s.getSlot().getSlotid());
            stm.setInt(3, s.getRoom().getRoom_id());
            stm.setDate(4, s.getTraining_date());
            stm.setString(5, s.getFrom_time());
            stm.setString(6, s.getTo_time());
            stm.setBoolean(7, s.isStatus());
            stm.setBoolean(8, false);
             stm.setString(9, s.getTopic());
            stm.setString(10, s.getNote());
            stm.executeUpdate();
        } catch (Exception e) {
        }

    }
    public ArrayList<Schedule> getScheduleDaily(int user) {

        try {
            ArrayList<Schedule> list = new ArrayList<>();
            String sql = "select schedule_id, c.class_code, st.setting_title as room, cs.setting_title as slot, training_date, subject_code,from_time, to_time,is_attend from schedule s \n"
                    + "inner join class c on s.class_id = c.class_id\n"
                    + "inner join  setting st on s.room_id=st.setting_id\n"
                    + "inner join  class_setting cs on s.slot_id = cs.setting_id \n"
                    + "inner join subject sj on sj.subject_id = c.subject_id\n"
                    + "where training_date = CURDATE() and (c.trainer_id=? or c.supporter_id=?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user);
            stm.setInt(2, user);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt("schedule_id"));
                s.setTraining_date(rs.getDate("training_date"));
                s.setFrom_time(rs.getString("from_time"));
                s.setTo_time(rs.getString("to_time"));
                s.setIs_attend(rs.getBoolean("is_attend"));
                Class c = new Class();
                c.setClass_name(rs.getString("class_code"));
                Subject sj = new Subject();
                sj.setSubject_code(rs.getString("subject_code"));
                c.setSubject(sj);
                s.setClasss(c);
                TimeSlot slot = new TimeSlot();
                slot.setSlotname(rs.getString("slot"));
                s.setSlot(slot);
                Room room = new Room();
                room.setRoom_name(rs.getString("room"));
                s.setRoom(room);
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Schedule> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Schedule get(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Schedule entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Schedule> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
