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
import model.ClassSetting;
import model.Setting;
import model.User;

/**
 *
 * @author win
 */
public class ClassSettingDAO extends DBContext<ClassSetting> {

    public ArrayList<model.Class> listClass() {
        ArrayList<model.Class> list = new ArrayList<>();
        try {
            String sql = "select * from class";
            PreparedStatement stm = conection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class s = new model.Class();
                s.setClass_id(rs.getInt("class_id"));
                s.setClass_name(rs.getString("class_code"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<model.Class> listClassByUser(int user_id) {
        ArrayList<model.Class> list = new ArrayList<>();
        try {
            String sql = "select * from class c\n"
                    + "where (1=1) and (c.supporter_id = ? or c.trainer_id = ?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user_id);
             stm.setInt(2, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class s = new model.Class();
                s.setClass_id(rs.getInt("class_id"));
                s.setClass_name(rs.getString("class_code"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<ClassSetting> listType() {
        ArrayList<ClassSetting> list = new ArrayList<>();
        try {
            String sql = "select * from setting s where s.type_id =9";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassSetting c = new ClassSetting();
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                c.setType(s);
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int count(String title, Integer type, Integer status, Integer class_id, int user_id) {
        try {
            String sql = "SELECT COUNT(*) as total FROM class_setting cs \n"
                    + "inner join class c on c.class_id = cs.class_id\n"
                    + "where (1=1) and (c.supporter_id = ? or c.trainer_id = ?)";

            Integer count = 2;
            HashMap<Integer, Object> params = new HashMap<>();
            if (type != -1) {
                count++;
                sql += "and cs.type_id = ? \n ";
                params.put(count, type);
            }
            if (!title.equals("")) {
                count++;
                sql += "and cs.setting_title like ? \n ";
                params.put(count, "%" + title + "%");
            }
            if (status != -1) {
                count++;
                sql += "and cs.status = ? \n ";
                params.put(count, status);
            }
            if (class_id != -1) {
                count++;
                sql += "and cs.class_id = ? \n ";
                params.put(count, class_id );
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

    public ArrayList<ClassSetting> paggingV2(Integer page_size, Integer page_index, Integer type, String title, Integer status, Integer class_id, int user_id) {
        String sql = "SELECT cs.setting_id,cs.setting_title,cs.setting_value,cs.type_id,cs.display_order,cs.class_id,cs.status,cs.description,c.class_code,s.setting_title as title FROM class_setting cs inner join class c on cs.class_id = c.class_id\n"
                + "                inner join setting s on cs.type_id = s.setting_id where (1=1) and (c.supporter_id = ? or c.trainer_id = ?) \n";
        Integer count = 2;
        HashMap<Integer, Object> params = new HashMap<>();
        if (type != -1) {
            count++;
            sql += "and cs.type_id = ? \n ";
            params.put(count, type);
        }
        if (class_id != -1) {
            count++;
            sql += "and cs.class_id = ? \n ";
            params.put(count, class_id);
        }
        if (status != -1) {
            count++;
            sql += "and cs.status = ? \n ";
            params.put(count, status);
        }
        if (!title.equals("")) {
            count++;
            sql += "and cs.setting_title like ? \n ";
            params.put(count, "%" + title + "%");
        }

        sql += "order by cs.setting_id asc limit ?,?";
        ArrayList<ClassSetting> list = new ArrayList<>();

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
                ClassSetting cs = new ClassSetting();
                cs.setSetting_id(rs.getInt("setting_id"));
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("title"));
                cs.setType(s);
                cs.setSetting_title(rs.getString("setting_title"));
                cs.setSetting_value(rs.getString("setting_value"));
                cs.setDisplay_order(rs.getString("display_order"));
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                cs.setClass_set(c);
                cs.setStatus(rs.getInt("status"));
                cs.setDescription(rs.getString("description"));
                list.add(cs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override

    public ArrayList<ClassSetting> list() {
        ArrayList<ClassSetting> classList = new ArrayList<>();
        try {

            String sql = "SELECT cs.setting_id,cs.setting_title,cs.setting_value,cs.type_id,cs.display_order,cs.class_id,cs.status,cs.description,c.class_code,s.setting_title as title FROM class_setting cs inner join class c on cs.class_id = c.class_id\n"
                    + "                    inner join setting s on cs.type_id = s.setting_id where (1=1)";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassSetting cs = new ClassSetting();
                cs.setSetting_id(rs.getInt("setting_id"));
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("title"));
                cs.setType(s);
                cs.setSetting_title(rs.getString("setting_title"));
                cs.setSetting_value(rs.getString("setting_value"));
                cs.setDisplay_order(rs.getString("display_order"));
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                cs.setClass_set(c);
                cs.setStatus(rs.getInt("status"));
                cs.setDescription(rs.getString("description"));
                classList.add(cs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classList;
    }

    public ClassSetting get(ClassSetting entity) {
        try {

            String sql = "SELECT cs.setting_id,cs.setting_title,cs.setting_value,cs.type_id,cs.display_order,cs.class_id,cs.status,cs.description,c.class_code FROM class_setting cs inner join class c on cs.class_id = c.class_id\n"
                    + "inner join setting s on cs.type_id = s.setting_id where (1=1) and cs.setting_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSetting_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassSetting cs = new ClassSetting();
                cs.setSetting_id(rs.getInt("setting_id"));
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("type_id"));
                cs.setType(s);
                cs.setSetting_title(rs.getString("setting_title"));
                cs.setSetting_value(rs.getString("setting_value"));
                cs.setDisplay_order(rs.getString("display_order"));
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                cs.setClass_set(c);
                cs.setStatus(rs.getInt("status"));
                cs.setDescription(rs.getString("description"));
                return cs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkClassSettingExist(int txt) {
        String sql = "select * from class_setting where setting_id =?";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, txt);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int countID() {
        try {
            String sql = "select * from class_setting order by setting_id desc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("setting_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void insert(ClassSetting entity) {
        try {
            String sql = "insert into class_setting values (?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSetting_id());
            stm.setInt(2, entity.getType().getSetting_id());
            stm.setString(3, entity.getSetting_title());
            stm.setString(4, entity.getSetting_value());
            stm.setString(5, entity.getDisplay_order());
            stm.setInt(6, entity.getClass_set().getClass_id());
            stm.setInt(7, entity.getStatus());
            stm.setString(8, entity.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ClassSetting entity) {
        try {
            String sql = "UPDATE `class_setting`\n"
                    + "SET\n"
                    + "`type_id` =?,\n"
                    + "`setting_title` =?,\n"
                    + "`setting_value` =?,\n"
                    + "`display_order` =?,\n"
                    + "`class_id` = ?,\n"
                    + "`status` =? ,\n"
                    + "`description` = ?\n"
                    + "WHERE `setting_id` = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);

            stm.setInt(1, entity.getType().getSetting_id());
            stm.setString(2, entity.getSetting_title());
            stm.setString(3, entity.getSetting_value());
            stm.setString(4, entity.getDisplay_order());
            stm.setInt(5, entity.getClass_set().getClass_id());
            stm.setInt(6, entity.getStatus());
            stm.setString(7, entity.getDescription());
            stm.setInt(8, entity.getSetting_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(ClassSetting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void changeStatus(int setting_id, String status) {
        try {
            String sql = "update class_setting set status= ? where setting_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, setting_id);
            if (status.equals("0")) {
                stm.setString(1, "1");
            } else {
                stm.setString(1, "0");
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ClassSetting> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
