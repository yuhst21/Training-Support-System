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
import model.User;

/**
 *
 * @author Admin
 */
public class SignUpDAO extends DBContext<Object> {

    public User checkUser(String email) {
        
        String sql = "select * from user where email =? ";
        try {
            
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
            
                return new User(rs.getInt("user_id"),email);

            }
        } catch (Exception e) {

        }
        return null;
    }
  

    public void signup(String full_name, String email, String password) {
        String sql = "insert into user values(?,?,?,0,?,0,1,0,0,0,0)";

        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, count() + 1);
            st.setString(2, full_name);
            st.setString(3, email);

            st.setString(4, password);
            
            st.executeUpdate();

        } catch (Exception e) {

        }
    }
    public void updatePass(String password,int user_id){
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "\n"
                    + "pass = ?\n"
                    + "\n"
                    + "WHERE user_id = ?";
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, password);  
            st.setInt(2, user_id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public int count() {
        String sql = "SELECT user_id from user order by user_id desc";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

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
