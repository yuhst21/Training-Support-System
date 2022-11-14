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
import model.User;

/**
 *
 * @author HAICAO
 */
public class UserDAO extends DBContext<User> {

    public String getPassword(int id) {
        try {
            String sql = "select password from user where user_id=?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public ArrayList<Setting> listRoles() {
        try {
            ArrayList<Setting> list = new ArrayList<>();
            String sql = "SELECT setting_id, setting_title FROM setting\n"
                    + "where type_id = 1 and setting_id != 11 and status = 1\n"
                    + "order by display_order asc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setSetting_tiltle(rs.getString("setting_title"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getUserByRoleId(int role_id) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "select user.user_id, full_name, email, mobile, avatar_url , status, note, address, position, company,role_id from user \n"
                    + "inner join user_role on user.user_id = user_role.user_id \n"
                    + "where role_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, role_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getInt("user_id") == 5) {
                    continue;
                }
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setAvatar_url(rs.getString("avatar_url"));
                u.setFull_name(rs.getString("full_name"));
                u.setStatus(rs.getBoolean("status"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setNote(rs.getString("note"));
                u.setAddress(rs.getString("address"));
                u.setCompany(rs.getString("company"));
                u.setPosition(rs.getString("position"));
                list.add(u);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePass(String password, int user_id) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "\n"
                    + "password = ?\n"
                    + "\n"
                    + "WHERE user_id = ?";
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, password);
            st.setInt(2, user_id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getIdByEmail(String email) {

        String sql = "select user_id from user where email =? ";
        try {

            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");

            }
        } catch (Exception e) {

        }
        return -1;
    }

    public int getRoleId(int user_id) {
        String sql = "select * from user_role where user_id=?";
        try {

            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(2);

            }
        } catch (Exception e) {

        }
        return -1;
    }

    public boolean checkRoleID(int user_id, int role_id) {
        ArrayList<Integer> roleList = new ArrayList<>();
        String sql = "select * from user_role where user_id=? ";
        try {

            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                roleList.add(rs.getInt(2));

            }

        } catch (Exception e) {

        }
        for (Integer i : roleList) {
            if (i == role_id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<User> list() {
        ArrayList<User> user = new ArrayList<>();
        try {

            String sql = "select * from user";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setAvatar_url(rs.getString("avatar_url"));
                u.setFull_name(rs.getString("full_name"));
                u.setStatus(rs.getBoolean("status"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setNote(rs.getString("note"));
                u.setAddress(rs.getString("address"));
                u.setCompany(rs.getString("company"));
                u.setPosition(rs.getString("position"));
                user.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public ArrayList<User> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getRoleName(int id) {
        try {
            String sql = "select setting_title from setting where setting_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getString("setting_title");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User get(User entity) {
        try {
            //String sql = "select user_id, full_name, email, mobile, avatar_url , status, note, address, position, company from user where user_id = ?";
            String sql = "select user.user_id, full_name, email, mobile, avatar_url , status, note, address, position, company,role_id from user \n"
                    + "inner join user_role on user.user_id = user_role.user_id \n"
                    + "where user.user_id = ?\n"
                    + "order by role_id asc";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            ResultSet rs = stm.executeQuery();
            User u = new User();
            if (rs.next()) {
                u.setUser_id(entity.getUser_id());
                u.setAvatar_url(rs.getString("avatar_url"));
                u.setFull_name(rs.getString("full_name"));
                u.setStatus(rs.getBoolean("status"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setNote(rs.getString("note"));
                u.setAddress(rs.getString("address"));
                u.setCompany(rs.getString("company"));
                u.setPosition(rs.getString("position"));
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("role_id"));
                u.getList().add(s);
            }
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("role_id"));
                u.getList().add(s);
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(User entity) {
        try {
            String sql = "INSERT INTO `user`\n"
                    + "VALUES\n"
                    + "(?,?,?,null,null,null,1,null,null,null,null,null);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            stm.setString(2, entity.getFull_name());
            stm.setString(3, entity.getEmail());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getWithNoRole(User entity) {
        try {
            //String sql = "select user_id, full_name, email, mobile, avatar_url , status, note, address, position, company from user where user_id = ?";
            String sql = "select * from user u where u.user_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            ResultSet rs = stm.executeQuery();
            User u = new User();
            while (rs.next()) {
                u.setUser_id(entity.getUser_id());
                u.setAvatar_url(rs.getString("avatar_url"));
                u.setFull_name(rs.getString("full_name"));
                u.setStatus(rs.getBoolean("status"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setNote(rs.getString("note"));
                u.setAddress(rs.getString("address"));
                u.setCompany(rs.getString("company"));
                u.setPosition(rs.getString("position"));
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateUserRole(User entity) {
        try {
            String sql = "UPDATE `user_role`\n"
                    + "SET\n"
                    + "`role_id` = 15\n"
                    + "WHERE `user_id` = ? ;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRoleForNewUser(User entity) {
        try {
            String sql = "UPDATE `user_role`\n"
                    + "SET\n"
                    + "`role_id` = 16\n"
                    + "WHERE `user_id` = ? ;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertRoleForNewUser(User entity) {
        try {
            String sql = "INSERT INTO `user_role`\n"
                    + "VALUES\n"
                    + "(?,16,null);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertForRegiesterUser(User entity) {
        try {
            String sql = "INSERT INTO `user_role`\n"
                    + "VALUES\n"
                    + "(?,15,null);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getUser_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countID() {
        try {
            String sql = "select * from user order by user_id desc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<User> pagging(int page_size, int page_index, String txt, Integer role, String status) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "select u.user_id, full_name, email, mobile, avatar_url, u.status, note, address, company, username, position, setting_id, setting_title from user u \n"
                    + "inner join user_role r on u.user_id = r.user_id\n"
                    + "inner join setting s on r.role_id = s.setting_id\n"
                    + "where u.user_id!=1 and (full_name like ? or username like ? or email like ?)";

            Integer count = 3;
            HashMap<Integer, Object> params = new HashMap<>();
            if (role != -1) {
                count++;
                sql += "AND setting_id = ?\n";
                params.put(count, role);
            }
            if (!status.equals("-1")) {
                count++;
                sql += "AND u.status = ?\n";
                params.put(count, status);
            }
            sql += "order by u.user_id asc\n"
                    + "limit ? , ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + txt + "%");
            stm.setString(2, "%" + txt + "%");
            stm.setString(3, "%" + txt + "%");
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            stm.setInt(count + 1, page_size * (page_index - 1));
            stm.setInt(count + 2, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUser_id(rs.getInt(1));
                u.setFull_name(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setMobile(rs.getString(4));
                u.setAvatar_url(rs.getString(5));
                u.setStatus(rs.getBoolean(6));
                u.setNote(rs.getString(7));
                u.setAddress(rs.getString(8));
                u.setCompany(rs.getString(9));
                u.setUsername(rs.getString(10));
                u.setPosition(rs.getString(11));
                Setting s = new Setting();
                s.setSetting_id(rs.getInt(12));
                s.setSetting_tiltle(rs.getString(13));
                u.getList().add(s);
                list.add(u);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int count(String txt, String status, Integer role) {
        try {
            String sql = "SELECT COUNT(*) as total FROM user where (full_name like ? or username like ?)\n";
            Integer count = 2;
            HashMap<Integer, Object> params = new HashMap<>();
            if (role != -1) {
                count++;
                sql += "AND setting_id = ?\n";
                params.put(count, role);
            }
            if (!status.equals("-1")) {
                count++;
                sql += "AND status = ?\n";
                params.put(count, status);
            }
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + txt + "%");
            stm.setString(2, "%" + txt + "%");
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
            String sql = "update user set status= ? where user_id= ?";
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
}
