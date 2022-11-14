/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Category;
import model.Setting;
import model.WebContact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author win
 */
public class SettingListDAO extends DBContext<Setting> {

    public int count(String title, Integer type, Integer status) {
        try {
            String sql = "SELECT COUNT(*) as total FROM setting where (1=1) and setting_id >10 ";

            Integer count = 0;
            HashMap<Integer, Object> params = new HashMap<>();
            if (type != -1) {
                count++;
                sql += "and type_id = ? \n ";
                params.put(count, type);
            }
            if (!title.equals("-1")) {
                count++;
                sql += "and setting_title like ? \n ";
                params.put(count, "%" + title + "%");
            }
            if (status != -1) {
                count++;
                sql += "and status = ? \n ";
                params.put(count, status);
            }
            PreparedStatement stm = conection.prepareStatement(sql);
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

    public ArrayList<Setting> listByType() {
        ArrayList<Setting> list = new ArrayList<>();
        try {

            String sql = "SELECT s.setting_id,s.setting_title FROM setting s\n"
                    + "where s.setting_id <11";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public ArrayList<Setting> list() {
        ArrayList<Setting> list = new ArrayList<>();
        try {

            String sql = "SELECT * FROM setting";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                s.setDisplay_order(rs.getInt("display_order"));
                s.setSetting_value(rs.getString("setting_value"));
                s.setStatus(rs.getInt("status"));
                s.setDescription(rs.getString("description"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public ArrayList<Setting> filter(String setting_title, Integer type_id, Integer status) {
        String sql = "SELECT * FROM setting where (1=1)";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (setting_title != null) {
            count++;
            sql += "and setting_title like ? \n";
            params.put(count, "%" + setting_title + "%");
        }
        if (type_id != null) {
            count++;
            sql += "and type_id = ? \n ";
            params.put(count, type_id);
        }
        if (status != null) {
            count++;
            sql += "and status = ? ;\n ";
            params.put(count, status);
        }
        ArrayList<Setting> setList = new ArrayList<>();
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                s.setDisplay_order(rs.getInt("display_order"));
                s.setSetting_value(rs.getString("setting_value"));
                s.setStatus(rs.getInt("status"));
                s.setDescription(rs.getString("description"));
                setList.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setList;
    }

    public ArrayList<Setting> paggingV2(Integer page_size, Integer page_index, Integer type, String title, Integer status) {
        String sql = "SELECT s1.*, s2.setting_title as name FROM setting s1 \n"
                + "inner join setting s2 on s1.type_id = s2.setting_id\n"
                + "where (1=1) and s1.setting_id >10 \n";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (type != -1) {
            count++;
            sql += "and s1.type_id = ? \n ";
            params.put(count, type);
        }
        if (!title.equals("-1")) {
            count++;
            sql += "and s1.setting_title like ? \n ";
            params.put(count, "%" + title + "%");
        }
        if (status != -1) {
            count++;
            sql += "and s1.status = ? \n ";
            params.put(count, status);
        }
        sql += "order by setting_id asc limit ?,?";
        ArrayList<Setting> list = new ArrayList<>();

        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            stm.setInt(count + 1, page_size * (page_index - 1));
            stm.setInt(count + 2, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                s.setDisplay_order(rs.getInt("display_order"));
                s.setSetting_value(rs.getString("setting_value"));
                s.setStatus(rs.getInt("status"));
                s.setDescription(rs.getString("description"));
                s.setType_name(rs.getString("name"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public Setting get(Setting entity) {
        try {
            String sql = "SELECT * FROM setting where setting_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSetting_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                s.setDisplay_order(rs.getInt("display_order"));
                s.setSetting_value(rs.getString("setting_value"));
                s.setStatus(rs.getInt("status"));
                s.setDescription(rs.getString("description"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Setting entity) {
        try {
            String sql = "insert into setting values (?,?,?,?,?,?,?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSetting_id());
            stm.setInt(2, entity.getType_id());
            stm.setString(3, entity.getSetting_tiltle());
            stm.setString(4, entity.getSetting_value());
            stm.setInt(5, entity.getDisplay_order());
            stm.setInt(6, entity.getStatus());
            stm.setString(7, entity.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Setting entity) {
        try {
            String sql = "UPDATE `setting`\n"
                    + "SET\n"
                    + "`type_id` =?,\n"
                    + "`setting_title` =?,\n"
                    + "`setting_value` =?,\n"
                    + "`display_order` =?,\n"
                    + "`status` =? ,\n"
                    + "`description` = ?\n"
                    + "WHERE `setting_id` = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getType_id());
            stm.setString(2, entity.getSetting_tiltle());
            stm.setString(3, entity.getSetting_value());
            stm.setInt(4, entity.getDisplay_order());
            stm.setInt(5, entity.getStatus());
            stm.setString(6, entity.getDescription());
            stm.setInt(7, entity.getSetting_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Setting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Setting> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int countID() {
        try {
            String sql = "select * from setting order by setting_id desc";
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

    public void changeStatus(int setting_id, String status) {
        try {
            String sql = "update setting set status = ? where setting_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, setting_id);
            if (status.equals("0")) {
                stm.setString(1, "1");
            } else {
                stm.setString(1, "0");
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
