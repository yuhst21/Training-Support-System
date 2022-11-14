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
import model.Setting;
import model.Subject;
import model.SubjectSetting;
import model.User;

/**
 *
 * @author mavjp
 */
public class SubjectSettingDAO extends DBContext<SubjectSetting> {

    @Override
    public ArrayList<SubjectSetting> list() {
        String sql = "SELECT setting_value, setting_title, subject_setting.status, subject_name, description \n"
                + "FROM subject_setting inner join subject on subject_setting.subject_id = subject.subject_id";
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SubjectSetting> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SubjectSetting get(SubjectSetting entity) {
        try {
            String sql = "SELECT st.display_order,s.setting_title as type, st.setting_id, st.setting_value, st.setting_title, st.status, subject_name, st.subject_id ,st.description\n"
                    + "                    FROM subject_setting st inner join subject sj on st.subject_id = sj.subject_id\n"
                    + "                    inner join setting s on st.type_id = s.setting_id\n"
                    + "                    WHERE st.setting_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_name(rs.getString("subject_name"));
                s.setSubject_id(rs.getInt("subject_id"));
                int id = rs.getInt("setting_id");
                String value = rs.getString("setting_value");
                String title = rs.getString("setting_title");
                boolean status = rs.getBoolean("status");
                String body = rs.getString("description");
                SubjectSetting set = new SubjectSetting(id, s, title, value, status, body);
                Setting st = new Setting();
                st.setSetting_tiltle(rs.getString("type"));
                set.setType(st);
                set.setDisplay_order(rs.getString("display_order"));
                return set;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countID() {
        try {
            String sql = "select setting_id from subject_setting order by setting_id desc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("setting_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void insert(SubjectSetting entity) {
        try {
            String sql = "INSERT INTO `subject_setting`\n"
                    + "(`setting_id`,\n"
                    + "`subject_id`,\n"
                    + "`type_id`,\n"
                    + "`setting_title`,\n"
                    + "`setting_value`,\n"
                    + "`display_order`,\n"
                    + "`status`,\n"
                    + "`description`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            stm.setInt(2, entity.getSubject().getSubject_id());
            stm.setInt(3, entity.getType().getSetting_id());
            stm.setString(4, entity.getSetting_title());
            stm.setString(5, entity.getSetting_value());
            stm.setString(6, entity.getDisplay_order());
            stm.setBoolean(7, entity.isStatus());
            stm.setString(8, entity.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(SubjectSetting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SubjectSetting entity) {
        try {
            String sql = "UPDATE `subject_setting`\n"
                    + "SET\n"
                    + "`setting_value` = ?,\n"
                    + "`status` = ?,\n"
                    + "`description` = ?\n"
                    + "WHERE `setting_id` = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, entity.getSetting_value());
            stm.setBoolean(2, entity.isStatus());
            stm.setString(3, entity.getDescription());
            stm.setInt(4, entity.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(SubjectSetting entity, int sid) {
        try {
            String sql = "UPDATE `subject_setting`\n"
                    + "SET\n"
                    + "`setting_value` = ?,\n"
                    + "`setting_title` = ?,\n"
                    + "`display_order` = ?,\n"
                    + "`status` = ?,\n"
                    + "`description` = ?\n"
                    + "WHERE `setting_id` = ? AND `subject_id` = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, entity.getSetting_value());
            stm.setString(2, entity.getSetting_title());
            stm.setString(3, entity.getDisplay_order());
            stm.setBoolean(4, entity.isStatus());
            stm.setString(5, entity.getDescription());
            stm.setInt(6, entity.getId());
            stm.setInt(7, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count(String txt, String title, Boolean status, String name) {
        try {
            String sql = "SELECT COUNT(*) as total \n"
                    + "FROM subject_setting inner join subject on subject_setting.subject_id = subject.subject_id\n"
                    + "WHERE subject_name like ? \n";

            Integer count = 1;
            HashMap<Integer, Object> params = new HashMap<>();
            if (title != null) {
                count++;
                sql += "AND setting_title = ?\n";
                params.put(count, title);
            }
            if (status != null) {
                count++;
                sql += "AND subject_setting.status = ?\n";
                params.put(count, status);
            }
            if (name != null) {
                count++;
                sql += "AND subject_name = ?\n";
                params.put(count, name);
            }
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + txt + "%");
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

    public void changeStatus(int id, String status) {
        try {
            String sql = "update subject_setting set status= ? where setting_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, id);
            if (status.equals("false")) {
                stm.setString(1, "1");
            } else {
                stm.setString(1, "0");
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<SubjectSetting> pagging(int page_size, int page_index, String txt, String title, Boolean status, String name) {

        ArrayList<SubjectSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT ss.display_order,setting.setting_title as type,ss.setting_id, ss.setting_value, ss.setting_title, ss.status, subject_name, ss.description \n"
                    + "				FROM subject_setting ss inner join subject on ss.subject_id = subject.subject_id\n"
                    + "									inner join setting on ss.type_id = setting.setting_id\n"
                    + "				WHERE ss.setting_title like ?\n";

            Integer count = 1;
            HashMap<Integer, Object> params = new HashMap<>();
            if (title != null) {
                count++;
                sql += "AND ss.setting_title = ?\n";
                params.put(count, title);
            }
            if (status != null) {
                count++;
                sql += "AND ss.status = ?\n";
                params.put(count, status);
            }
            if (name != null) {
                count++;
                sql += "AND subject_name = ?\n";
                params.put(count, name);
            }
            sql += "order by ss.display_order asc\n"
                    + "limit ? , ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + txt + "%");
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            stm.setInt(count + 1, page_size * (page_index - 1));
            stm.setInt(count + 2, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting set = new Setting();
                set.setSetting_tiltle(rs.getString("type"));
                int id = rs.getInt("setting_id");
                String value = rs.getString("setting_value");
                String set_title = rs.getString("setting_title");
                Subject subs = new Subject();
                subs.setSubject_name(rs.getString("subject_name"));
                boolean set_status = rs.getBoolean("status");
                String body = rs.getString("description");
                SubjectSetting s = new SubjectSetting(id, subs, set_title, value, set_status, body);
                s.setType(set);
                s.setDisplay_order(rs.getString("display_order"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkSettingExist(int subject, int type) {
        String sql = "SELECT * FROM subject_setting \n"
                + "inner join subject on subject_setting.subject_id = subject.subject_id\n"
                + "WHERE type_id = ? and subject.subject_id = ?";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, type);
            stm.setInt(2, subject);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
