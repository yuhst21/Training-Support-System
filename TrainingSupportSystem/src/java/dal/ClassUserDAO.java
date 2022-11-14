/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Class_user;
import model.Setting;
import model.User;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

/**
 *
 * @author win
 */
public class ClassUserDAO extends DBContext<Class_user> {

    @Override
    public ArrayList<Class_user> list() {
        ArrayList<Class_user> list = new ArrayList<>();
        try {
            String sql = "SELECT cu.class_id,cu.user_id,cu.status,cu.note,cu.ongoing_eval,cu.dropout_date,\n"
                    + "cu.final_eval,cu.topic_eval,u.full_name,u.email,c.class_code\n"
                    + " FROM class_user cu inner join user u on \n"
                    + "cu.user_id = u.user_id inner join class c on cu.class_id = c.class_id";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                Class_user cu = new Class_user();
                cu.setClass_id(c);
                cu.setUser(u);
                cu.setNote(rs.getString("note"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getString("ongoing_eval"));
                cu.setFinal_eval(rs.getString("final_eval"));
                cu.setTopic_eval(rs.getString("topic_eval"));
                list.add(cu);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Class_user> list(int entity) {
        ArrayList<Class_user> list = new ArrayList<>();
        try {
            String sql = "SELECT cu.class_id,cu.user_id,cu.status,cu.note,cu.ongoing_eval,cu.dropout_date,\n"
                    + "                   cu.final_eval,cu.topic_eval,u.full_name,u.email,c.class_code,ur.role_id\n"
                    + "                    FROM class_user cu inner join user u on \n"
                    + "                   cu.user_id = u.user_id inner join class c on cu.class_id = c.class_id\n"
                    + "                   inner join user_role ur on ur.user_id = u.user_id where cu.class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("role_id"));
                u.getList().add(s);
                Class_user cu = new Class_user();
                cu.setClass_id(c);
                cu.setUser(u);
                cu.setNote(rs.getString("note"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getString("ongoing_eval"));
                cu.setFinal_eval(rs.getString("final_eval"));
                cu.setTopic_eval(rs.getString("topic_eval"));
                list.add(cu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int count(Integer class_id, Integer status, String name, int user_id) {
        try {
            String sql = "SELECT COUNT(*) as total FROM class_user cu inner join user u on cu.user_id = u.user_id\n"
                    + "                    inner join class c on cu.class_id = c.class_id\n"
                    + "                     where (1=1) and (c.supporter_id = ? or c.trainer_id = ?) ";
            Integer count = 2;
            HashMap<Integer, Object> params = new HashMap<>();
            if (class_id != -1) {
                count++;
                sql += "and cu.class_id = ? \n ";
                params.put(count, class_id);
            }
            if (status != -1) {
                count++;
                sql += "and cu.status = ? \n ";
                params.put(count, status);
            }
            if (!name.equals("")) {
                count++;
                sql += "and u.full_name like ? \n ";
                params.put(count, "%" + name + "%");
            }
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user_id);
            stm.setInt(2, user_id);
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
            Logger.getLogger(SettingListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Class_user> paggingV2(Integer page_size, Integer page_index, Integer class_id, Integer status, String name, int user_id) {
        String sql = "SELECT cu.class_id,cu.user_id,cu.status,cu.note,cu.ongoing_eval,cu.dropout_date,\n"
                + "cu.final_eval,cu.topic_eval,u.full_name,u.email,c.class_code\n"
                + " FROM class_user cu inner join user u on \n"
                + "cu.user_id = u.user_id inner join class c on cu.class_id = c.class_id where (1=1)"
                + " and (c.supporter_id = ? or c.trainer_id = ?) ";

        Integer count = 2;
        HashMap<Integer, Object> params = new HashMap<>();
        if (class_id != -1) {
            count++;
            sql += "and cu.class_id = ? \n ";
            params.put(count, class_id);
        }
        if (status != -1) {
            count++;
            sql += "and cu.status = ? \n ";
            params.put(count, status);
        }
        if (!name.equals("")) {
            count++;
            sql += "and u.full_name like ? \n ";
            params.put(count, "%" + name + "%");
        }
        sql += "order by cu.class_id asc limit ?,?";
        ArrayList<Class_user> list = new ArrayList<>();

        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user_id);
            stm.setInt(2, user_id);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            stm.setInt(count + 1, page_size * (page_index - 1));
            stm.setInt(count + 2, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                Class_user cu = new Class_user();
                cu.setClass_id(c);
                cu.setUser(u);
                cu.setNote(rs.getString("note"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getString("ongoing_eval"));
                cu.setFinal_eval(rs.getString("final_eval"));
                cu.setTopic_eval(rs.getString("topic_eval"));
                list.add(cu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<Class_user> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class_user get(Class_user entity) {
        try {
            String sql = "SELECT cu.class_id,cu.user_id,cu.status,cu.note,cu.ongoing_eval,cu.dropout_date,\n"
                    + "cu.final_eval,cu.topic_eval,u.full_name,u.email,c.class_code\n"
                    + " FROM class_user cu inner join user u on \n"
                    + "cu.user_id = u.user_id inner join class c on cu.class_id = c.class_id where (1=1) and cu.user_id = ? and cu.class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser().getUser_id());
            stm.setInt(2, entity.getClass_id().getClass_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                Class_user cu = new Class_user();
                cu.setClass_id(c);
                cu.setUser(u);
                cu.setNote(rs.getString("note"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getString("ongoing_eval"));
                cu.setFinal_eval(rs.getString("final_eval"));
                cu.setTopic_eval(rs.getString("topic_eval"));
                return cu;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkUserExist(Class_user entity) {
        try {
            String sql = "SELECT cu.class_id,cu.user_id,cu.status,cu.note,cu.ongoing_eval,cu.dropout_date,\n"
                    + "cu.final_eval,cu.topic_eval,u.full_name,u.email,c.class_code\n"
                    + " FROM class_user cu inner join user u on \n"
                    + "cu.user_id = u.user_id inner join class c on cu.class_id = c.class_id where (1=1) and cu.user_id = ? and cu.class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser().getUser_id());
            stm.setInt(2, entity.getClass_id().getClass_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void insert(Class_user entity) {
        try {
            String sql = "INSERT INTO `class_user`\n"
                    + "VALUES\n"
                    + "(?,?,1,null,null,null,null,null);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getClass_id().getClass_id());
            stm.setInt(2, entity.getUser().getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Class_user entity) {
        try {
            String sql = "DELETE FROM class_user\n"
                    + "WHERE class_id = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getClass_id().getClass_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Class_user entity) {
        try {
            String sql = "UPDATE `class_user`\n"
                    + "SET\n"
                    + "`status` = ?,\n"
                    + "`note` = ?,\n"
                    + "`dropout_date` = ?\n"
                    + "WHERE `class_id` = ? AND `user_id` = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setBoolean(1, entity.isStatus());
            stm.setString(2, entity.getNote());
            stm.setDate(3, entity.getDropout_date());
            stm.setInt(4, entity.getClass_id().getClass_id());
            stm.setInt(5, entity.getUser().getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateStatus(int user_id, int class_id, Boolean status, Date date) {
        try {
            String sql = "UPDATE `class_user`\n"
                    + "SET\n"
                    + "`status` = ?\n,"
                    + "`dropout_date` = ?\n"
                    + "WHERE `class_id` = ? AND `user_id` = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            if (status) {
                stm.setBoolean(1, false);
            } else {
                stm.setBoolean(1, true);
            }
            stm.setDate(2, date);
            stm.setInt(3, class_id);
            stm.setInt(4, user_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateToTrainee(Class_user entity) {
        try {
            String sql = "UPDATE `user_role`\n"
                    + "SET\n"
                    + "`role_id` = 15\n"
                    + "WHERE `user_id` = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser().getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatePassTrainee(Class_user entity, String randompass) {
        try {
            String sql = "UPDATE `user`\n"
                    + "SET\n"
                    + "`password` = ?\n"
                    + "WHERE `user_id` = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, randompass);
            stm.setInt(2, entity.getUser().getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
