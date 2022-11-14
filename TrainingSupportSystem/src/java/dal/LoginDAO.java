/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author Admin
 */
public class LoginDAO extends DBContext<Object> {

    public User check(String email, String pass) {
        String sql = "select * from user where email like ? and password like ?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, pass);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUser_id(rs.getInt("user_id"));
                s.setEmail(email);
                s.setPassword(pass);
                return s;
            }
            if (rs.next()) {
                return new User(rs.getInt("user_id"), email, pass, rs.getBoolean("status"));
            }

        } catch (Exception e) {

        }
        return null;
    }

    public String checkRole(int user_id) {
        String sql = "SELECT * FROM user_role where user_id =?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("role_id");

            }
        } catch (Exception e) {

        }
        return null;
    }

    public void insertRollNew(int user_id, int role) {
        String sql = "INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (?, ?)";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, role);
            st.executeUpdate();
        } catch (Exception e) {

        }
    }

//    rs.getInt("userID"), rs.getString("fullname"), email, rs.getBoolean("status"), rs.getString("note"), rs.getString("mobile"), pass, rs.getString("avatar_url")
//    @Override
//    public ArrayList<Object> list() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<Object> list(String identity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Object get(Object entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void insert(Object entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void delete(Object entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void update(Object entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    @Override
    public ArrayList<Object> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Object> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
