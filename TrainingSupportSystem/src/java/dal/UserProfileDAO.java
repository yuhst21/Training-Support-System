/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import model.User;

/**
 *
 * @author ACER
 */
public class UserProfileDAO extends DBContext<User> {

    public void ChangeAvatar(String filename, int id) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "\n"
                    + "avatar_url = ?\n"
                    + "\n"
                    + "WHERE user_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, filename);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void ChangePassword(String password, int id) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "\n"
                    + "password = ?\n"
                    + "\n"
                    + "WHERE user_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void SaveChange(String name, String address, String email, String company, String position, String note, int id) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "full_name = ?,\n"
                    + "email = ?,\n"
                    + "note = ?,\n"
                    + "company =?,\n"
                    + "position = ?,\n"
                    + "address = ?\n"
                    + "WHERE user_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, note);
            stm.setString(4, company);
            stm.setString(5, position);
            stm.setString(6, address);
            stm.setInt(7, id);
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public String Encode(String input) {
        Base64.Encoder encoder = Base64.getEncoder();
        String originalString = input;
        String encodedString = encoder.encodeToString(originalString.getBytes());
        return encodedString;
    }

    public String decode(String input) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(input);
        return new String(bytes);
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
