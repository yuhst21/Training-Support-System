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
import model.Permission;
import model.Setting;

/**
 *
 * @author mavjp
 */
public class SystemPermissionDAO extends DBContext<Setting> {

    @Override
    public ArrayList<Setting> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Setting> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Setting get(Setting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Setting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Setting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Setting entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Permission> listPermission() {
        ArrayList<Permission> pers = new ArrayList<>();
        try {
            String sql = "select * from permission";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int role_id = rs.getInt("role_id");
                Setting role = new Setting();
                role.setSetting_id(role_id);
                int screen_id = rs.getInt("screen_id");
                Setting screen = new Setting();
                screen.setSetting_id(screen_id);
                boolean get_data = rs.getBoolean("get_all_data");
                boolean can_delete = rs.getBoolean("can_delete");
                boolean can_add = rs.getBoolean("can_add");
                boolean can_edit = rs.getBoolean("can_edit");
                Permission p = new Permission(role, screen, get_data, can_delete, can_add, can_edit);
                pers.add(p);
            }
            return pers;
        } catch (SQLException ex) {
            Logger.getLogger(SystemPermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Setting> listSettingByTypeID(int typeid) {
        ArrayList<Setting> sets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM setting where type_id = ? and status =1 order by display_order asc";

            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, typeid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("setting_id");
                int type = rs.getInt("type_id");
                String title = rs.getString("setting_title");
                String value = rs.getString("setting_value");
                int order = rs.getInt("display_order");
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Setting s = new Setting(id, type, title, value, order, status, des);
                sets.add(s);
            }
            return sets;
        } catch (SQLException ex) {
            Logger.getLogger(SystemPermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void saveChanges(ArrayList<Permission> pers) {
        try {
            conection.setAutoCommit(false);
            for (Permission per : pers) {
                //UPDATE
                if (checkPermissionExist(per.getRole().getSetting_id(), per.getScreen().getSetting_id())) {
                    String updateSql = "UPDATE `permission`\n"
                            + "SET\n"
                            + "`get_all_data` = ?,\n"
                            + "`can_delete` = ?,\n"
                            + "`can_add` = ?,\n"
                            + "`can_edit` = ?\n"
                            + "WHERE `screen_id` = ? AND `role_id` = ?";
                    PreparedStatement stm = conection.prepareStatement(updateSql);
                    stm.setInt(5, per.getScreen().getSetting_id());
                    stm.setInt(6, per.getRole().getSetting_id());
                    stm.setBoolean(1, per.isGet_all_data());
                    stm.setBoolean(2, per.isCan_delete());
                    stm.setBoolean(3, per.isCan_add());
                    stm.setBoolean(4, per.isCan_edit());
                    stm.executeUpdate();
                } //INSERT
                else {
                    String sql = "INSERT INTO `permission`\n"
                            + "(`screen_id`,\n"
                            + "`role_id`,\n"
                            + "`get_all_data`,\n"
                            + "`can_delete`,\n"
                            + "`can_add`,\n"
                            + "`can_edit`)\n"
                            + "VALUES\n"
                            + "(?,?,?,?,?,?);";
                    PreparedStatement stm = conection.prepareStatement(sql);
                    stm.setInt(1, per.getScreen().getSetting_id());
                    stm.setInt(2, per.getRole().getSetting_id());
                    stm.setBoolean(3, per.isGet_all_data());
                    stm.setBoolean(4, per.isCan_delete());
                    stm.setBoolean(5, per.isCan_add());
                    stm.setBoolean(6, per.isCan_edit());
                    stm.executeUpdate();
                }
            }
            conection.commit();
        } catch (SQLException ex) {
            try {
                conection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SystemPermissionDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SystemPermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SystemPermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean checkPermissionExist(int role, int screen) {
        try {
            String sql = "select * from permission where screen_id = ? and role_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, screen);
            stm.setInt(2, role);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SystemPermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
