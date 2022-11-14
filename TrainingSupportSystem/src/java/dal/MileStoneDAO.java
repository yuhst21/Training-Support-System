/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Milestone;
import model.Class;
import model.TimeSlot;

/**
 *
 * @author ACER
 */
public class MileStoneDAO extends DBContext<Milestone> {

    public ArrayList<Milestone> pagging(int trainer_id, int classid, int ass_id, String status, String tittle, int page_size, int page_index) {
        ArrayList<Milestone> mlist = new ArrayList<>();
        try {
            String sql = "SELECT m.milestone_id,m.tittle,a.title,m.from_date,m.to_date,m.status FROM milestone m\n"
                    + "                    inner join assignment a on m.ass_id=a.ass_id\n"
                    + "                    inner join class c on m.class_id=c.class_id\n"
                    + "                    where c.trainer_id=?";
            HashMap<Integer, Object> params = new HashMap<>();
            Integer count = 1;
            if (classid != -1) {
                count++;
                sql += " and  m.class_id=?";
                params.put(count, classid);
            }

            if (ass_id != -1) {
                count++;
                sql += " and  m.ass_id=?";
                params.put(count, ass_id);
            }

            if (!status.equals("-1")) {
                boolean check = false;
                if (status.equals("active")) {
                    check = true;
                }
                count++;
                sql += " and m.status=?";
                params.put(count, check);
            }
            if (!tittle.equals("-1")) {

                count++;
                sql += " and m.tittle like ?";
                params.put(count, "%" + tittle + "%");
            }
            sql += " limit ?,?";
            params.put(count + 1, page_size * (page_index - 1));
            params.put(count + 2, page_size);
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, trainer_id);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Milestone m = new Milestone();
                m.setMilestone_id(rs.getInt(1));
                Assignment a = new Assignment();
                a.setTitle(rs.getString(3));
                m.setAssignment(a);
                m.setTittle(rs.getString(2));
                m.setFrom_date(rs.getDate(4));
                m.setTo_date(rs.getDate(5));
                m.setStatus(rs.getBoolean(6));
                mlist.add(m);

            }
        } catch (Exception e) {
        }
        return mlist;
    }

    public String getClassName(int classid) {
        try {
            String sql = "select * from class where class_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, classid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "loz";
    }

    public void changeStatus(int subject_id, String status) {
        try {
            String sql = "update milestone set status=? where milestone_id=?";
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

    public int count(int trainer_id, int classid, int ass_id, String status, String tittle) {
        try {
            String sql = "SELECT COUNT(*) as total FROM milestone m\n"
                    + "                    inner join assignment a on m.ass_id=a.ass_id\n"
                    + "                    inner join class c on m.class_id=c.class_id\n"
                    + "                    where c.trainer_id=?";
            HashMap<Integer, Object> params = new HashMap<>();
            Integer count = 1;
            if (classid != -1) {
                count++;
                sql += " and  m.class_id=?";
                params.put(count, classid);
            }

            if (ass_id != -1) {
                count++;
                sql += " and  m.ass_id=?";
                params.put(count, ass_id);
            }

            if (!status.equals("-1")) {
                boolean check = false;
                if (status.equals("active")) {
                    check = true;
                }
                count++;
                sql += " and m.status=?";
                params.put(count, check);
            }
            if (!tittle.equals("-1")) {

                count++;
                sql += " and m.tittle like ?";
                params.put(count, "%" + tittle + "%");
            }
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, trainer_id);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Milestone getById(int id) {
        try {
            String sql = "select * from milestone where milestone_id=?";
            PreparedStatement stm = conection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Milestone m = new Milestone();
                m.setMilestone_id(rs.getInt(1));
                Assignment a = new Assignment();
                a.setAss_id(rs.getInt(2));
                m.setAssignment(a);
                m.setClass_id(rs.getInt(3));
                m.setFrom_date(rs.getDate(4));
                m.setTo_date(rs.getDate(5));
                m.setTittle(rs.getString(6));
                m.setAss_body(rs.getString(7));
                m.setDescription(rs.getString(8));
                m.setStatus(rs.getBoolean(9));
                return m;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void Update(Milestone m) {
        try {
            String sql = "UPDATE `milestone`\n"
                    + "SET\n"
                    + "`ass_id` = ?,\n"
                    + "`from_date` =?,\n"
                    + "`to_date` =?,\n"
                    + "`tittle` = ?,\n"
                    + "`ass_body` =?,\n"
                    + "`status` = ?\n"
                    + "WHERE `milestone_id` =?";
            PreparedStatement stm = conection.prepareCall(sql);
            Date fromdate = m.getFrom_date();
            stm.setInt(1, m.getAssignment().getAss_id());
            stm.setDate(2, fromdate);
            Date todate = m.getTo_date();
            stm.setDate(3, todate);
            stm.setString(4, m.getTittle());
            stm.setString(5, m.getAss_body());
            stm.setBoolean(6, m.isStatus());
            stm.setInt(7, m.getMilestone_id());
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM milestone\n"
                    + "WHERE milestone_id=?";
            PreparedStatement stm = conection.prepareCall(sql);
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public ArrayList<Assignment> listAss() {
        ArrayList<Assignment> alist = new ArrayList<>();
        try {
            String sql = "select * from assignment";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAss_id(rs.getInt(1));
                a.setTitle(rs.getString(3));
                a.setAss_body(rs.getString(4));
                alist.add(a);
            }
        } catch (Exception e) {
        }
        return alist;
    }

    public ArrayList<Assignment> listAssByClassID(int class_id) {
        ArrayList<Assignment> alist = new ArrayList<>();
        try {
            String sql = "select a.ass_id,a.title from milestone m inner join assignment a on m.ass_id=a.ass_id\n"
                    + "where class_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAss_id(rs.getInt(1));
                a.setTitle(rs.getString(2));
                alist.add(a);
            }
        } catch (Exception e) {
        }
        return alist;
    }

    public ArrayList<Assignment> listAssbyCid(int class_id) {
        ArrayList<Assignment> alist = new ArrayList<>();
        try {
            String sql = "select a.ass_id,a.title from milestone m inner join assignment a on a.ass_id=m.ass_id where class_id=? group by a.ass_id,a.title";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAss_id(rs.getInt(1));
                a.setTitle(rs.getString(2));
                alist.add(a);
            }
        } catch (Exception e) {
        }
        return alist;
    }

    public void add(Milestone m) {
        try {
            String sql = "INSERT INTO milestone\n"
                    + "(\n"
                    + "ass_id,\n"
                    + "class_id,\n"
                    + "from_date,\n"
                    + "to_date,\n"
                    + "tittle,\n"
                    + "ass_body,\n"
                    + "status)\n"
                    + "VALUES\n"
                    + "(\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, m.getAssignment().getAss_id());
            stm.setInt(2, m.getClass_id());
            stm.setDate(3, m.getFrom_date());
            stm.setDate(4, m.getTo_date());
            stm.setString(5, m.getTittle());
            stm.setString(6, m.getAssignment().getAss_body());
            stm.setBoolean(7, m.isStatus());

            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public Assignment getAssbyId(int id) {
        try {
            String sql = "select * from assignment where ass_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAss_id(rs.getInt(1));
                a.setTitle(rs.getString(3));
                a.setAss_body(rs.getString(4));
                return a;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Class> listClass(int trainer_id) {
        ArrayList<Class> clist = new ArrayList<>();

        try {
            String sql = "select * from class where trainer_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, trainer_id);
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

    public String getAssbody(int ass_id) {
        try {
            String sql = "select * from assignment where ass_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, ass_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getString(4);

            }
        } catch (Exception e) {
        }
        return "";
    }

    @Override
    public ArrayList<Milestone> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Milestone> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Milestone get(Milestone entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Milestone entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Milestone entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Milestone entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
