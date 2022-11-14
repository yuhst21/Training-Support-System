/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import model.Category;
import model.WebContact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author win
 */
public class WebContactDAO extends DBContext<WebContact> {
    
    public int count(String name, Integer category, Integer suppoter_id, Integer status) {
        
        try {
            String sql = "SELECT COUNT(*) as total FROM web_contact c inner join category ca on c.category_id = ca.category_id \n"
                    + "                inner join user u on u.user_id = c.supporter_id where (1=1) ";
            Integer count = 0;
            HashMap<Integer, Object> params = new HashMap<>();
            if (!name.equals("")) {
                count++;
                sql += " and c.fullname like ? \n ";
                params.put(count, "%" + name + "%");
            }
            if (category != -1) {
                count++;
                sql += "  and c.category_id = ?  \n";
                params.put(count, category);
            }
//            if (suppoter_id != null) {
//                count++;
//                sql += "   and c.supporter_id = ? \n";
//                params.put(count, suppoter_id);
//            }
            if (status != -1) {
                count++;
                sql += " and c.status = ? \n";
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
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<WebContact> paggingV2(Integer page_size, Integer page_index, String name, Integer category, Integer suppoter_id, Integer status) {
        String sql = "SELECT c.contact_id,c.category_id,c.supporter_id,c.fullname,c.emai,c.moblie,c.message,c.status,c.response,ca.category_name,u.full_name\n"
                + "                FROM web_contact c inner join category ca on c.category_id = ca.category_id \n"
                + "                inner join user u on u.user_id = c.supporter_id where (1=1) ";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (!name.equals("")) {
            count++;
            sql += "and c.fullname like ? \n";
            params.put(count, "%" + name + "%");
        }
        if (category != -1) {
            count++;
            sql += " and c.category_id = ?  \n";
            params.put(count, category);
        }
        
        if (suppoter_id != -1) {
            count++;
            sql += " and c.supporter_id = ? \n";
            params.put(count, suppoter_id);
        }
        if (status != -1) {
            count++;
            sql += "and c.status = ? \n";
            params.put(count, status);
        }
        sql += "order by contact_id asc limit ?,?";
        ArrayList<WebContact> list = new ArrayList<>();
        
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
                WebContact w = new WebContact();
                w.setContact_id(rs.getInt("contact_id"));
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                w.setCategory(c);
                User u = new User();
                u.setUser_id(rs.getInt("supporter_id"));
                u.setFull_name(rs.getString("full_name"));
                w.setSupporter(u);
                w.setName(rs.getString("fullname"));
                w.setEmail(rs.getString("emai"));
                w.setMobile(rs.getString("moblie"));
                w.setMessage(rs.getString("message"));
                w.setStatus(rs.getBoolean("status"));
                list.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    @Override
    
    public ArrayList<WebContact> list() {
        ArrayList<WebContact> listWeb = new ArrayList<>();
        try {
            
            String sql = "SELECT c.contact_id,c.category_id\n"
                    + ",c.supporter_id,c.fullname,c.emai,c.moblie,c.message,ca.category_name,c.status\n"
                    + "FROM web_contact c inner join category ca where c.category_id = ca.category_id;";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WebContact w = new WebContact();
                w.setContact_id(rs.getInt("contact_id"));
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                w.setCategory(c);
                w.setName(rs.getString("fullname"));
                w.setEmail(rs.getString("emai"));
                w.setMobile(rs.getString("moblie"));
                w.setMessage(rs.getString("message"));
                w.setStatus(rs.getBoolean("status"));
                listWeb.add(w);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listWeb;
    }
    
    public ArrayList<WebContact> listbyFullName(String identity) {
        ArrayList<WebContact> listWeb = new ArrayList<>();
        try {
            String sql = "SELECT c.contact_id,c.category_id\n"
                    + ",c.supporter_id,c.fullname,c.emai,c.moblie,c.message,ca.category_name FROM web_contact c inner join category ca where c.category_id = ca.category_id and c.fullname like ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + identity + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WebContact w = new WebContact();
                w.setContact_id(rs.getInt("contact_id"));
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                w.setCategory(c);
                w.setName(rs.getString("fullname"));
                w.setEmail(rs.getString("emai"));
                w.setMobile(rs.getString("moblie"));
                w.setMessage(rs.getString("message"));
                listWeb.add(w);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listWeb;
    }
    
    public ArrayList<WebContact> filter(String name, Integer category) {
        
        String sql = "SELECT c.contact_id,c.category_id,c.supporter_id,c.fullname,c.emai,c.moblie,c.message,c.response,ca.category_name \n"
                + "FROM web_contact c inner join category ca where (1=1) and c.category_id = ca.category_id \n";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (name != null) {
            count++;
            sql += "and c.fullname  like '%' + ? + '%' \n ";
            params.put(count, name);
        }
        if (category != null) {
            count++;
            sql += "and c.category_id = ? \n ";
            params.put(count, category);
        }
        ArrayList<WebContact> webList = new ArrayList<>();
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WebContact w = new WebContact();
                w.setContact_id(rs.getInt("contact_id"));
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                w.setCategory(c);
                
                w.setName(rs.getString("fullname"));
                w.setEmail(rs.getString("emai"));
                w.setMobile(rs.getString("moblie"));
                w.setMessage(rs.getString("message"));
                webList.add(w);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return webList;
    }
    
    public ArrayList<WebContact> filterv2(String name, Integer category, Integer suppoter_id, Integer status) {
        
        String sql = "SELECT c.contact_id,c.category_id,c.supporter_id,c.fullname,c.emai,c.moblie,c.message,c.status,c.response,ca.category_name,u.full_name\n"
                + "                FROM web_contact c inner join category ca on c.category_id = ca.category_id \n"
                + "                inner join user u on u.user_id = c.supporter_id where (1=1) ";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (!name.equals("-1")) {
            count++;
            sql += "and c.fullname like ? \n ";
            params.put(count, "%" + name + "%");
        }
        if (category != -1) {
            count++;
            sql += "and c.category_id = ? \n ";
            params.put(count, category);
        }
        if (suppoter_id != -1) {
            count++;
            sql += " and c.supporter_id = ? \n";
            params.put(count, suppoter_id);
        }
        if (status != -1) {
            count++;
            sql += "and c.status = ? \n";
            params.put(count, status);
        }
        ArrayList<WebContact> webList = new ArrayList<>();
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WebContact w = new WebContact();
                w.setContact_id(rs.getInt("contact_id"));
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                w.setCategory(c);
                User u = new User();
                u.setUser_id(rs.getInt("supporter_id"));
                u.setFull_name(rs.getString("full_name"));
                w.setSupporter(u);
                w.setName(rs.getString("fullname"));
                w.setEmail(rs.getString("emai"));
                w.setMobile(rs.getString("moblie"));
                w.setMessage(rs.getString("message"));
                w.setStatus(rs.getBoolean("status"));
                webList.add(w);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return webList;
    }
    
    @Override
    public WebContact get(WebContact entity) {
        try {
            
            String sql = "SELECT c.contact_id,c.category_id,c.supporter_id,c.fullname,c.emai,c.moblie,c.message,c.status,c.response,ca.category_name,u.full_name,c.dateSend,c.dateResponse\n"
                    + "                              FROM web_contact c inner join category ca on c.category_id = ca.category_id \n"
                    + "                 inner join user u on u.user_id = c.supporter_id where (1=1) and c.contact_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getContact_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WebContact w = new WebContact();
                w.setContact_id(rs.getInt("contact_id"));
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                w.setCategory(c);
                User u = new User();
                u.setUser_id(rs.getInt("supporter_id"));
                u.setFull_name(rs.getString("full_name"));
                w.setSupporter(u);
                w.setName(rs.getString("fullname"));
                w.setEmail(rs.getString("emai"));
                w.setMobile(rs.getString("moblie"));
                w.setMessage(rs.getString("message"));
                w.setStatus(rs.getBoolean("status"));
                w.setRespones(rs.getString("response"));
                w.setDateSend(rs.getDate("dateSend"));
                w.setDateResponse(rs.getDate("dateResponse"));
                return w;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void insert(WebContact entity) {
        try {
            String sql = "INSERT INTO`web_contact`\n"
                    + "VALUES\n"
                    + "(0,?,3,?,?,?,?,null,0,?,null);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getCategory().getCategory_id());
            stm.setString(2, entity.getName());
            stm.setString(3, entity.getEmail());
            stm.setString(4, entity.getMobile());
            stm.setString(5, entity.getMessage());
            stm.setDate(6, entity.getDateSend());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(WebContact entity) {
        try {
            String sql = "UPDATE web_contact\n"
                    + "SET\n"
                    + "`fullname` = ?,\n"
                    + "`emai` =?,\n"
                    + "`moblie` =?\n"
                    + "WHERE `contact_id` = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, entity.getName());
            stm.setString(2, entity.getEmail());
            stm.setString(3, entity.getMobile());
            stm.setInt(4, entity.getContact_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateResponse(WebContact entity,int user_id) {
        try {
            String sql = "UPDATE web_contact\n"
                    + "SET\n"
                    + "`supporter_id` = ?,\n"
                    + "`response` = ?,\n"
                    + "`dateResponse` = ?,\n"
                    + "`status` = 1\n"
                    + "WHERE `contact_id` = ?;";
            LocalDate now = LocalDate.now();
            Date date = Date.valueOf(now);
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user_id);
            stm.setString(2, entity.getRespones());
            stm.setDate(3, date);
            stm.setInt(4, entity.getContact_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(WebContact entity) {
        try {
            String sql = "DELETE FROM `course`.`web_contact`\n"
                    + "WHERE contact_id = ? ;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getContact_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WebContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ArrayList<WebContact> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void changeStatus(int contact_id, String status) {
        try {
            String sql = "update webcontact set status= ? where contact_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, contact_id);
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
