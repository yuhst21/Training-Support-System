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
import model.Subject;
import model.User;

/**
 *
 * @author HAICAO
 */
public class SubjectDAO extends DBContext<Subject> {

    public int countID() {
        try {
            String sql = "select * from subject order by subject_id desc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("subject_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean checkSubjectExist(String txt) {
        String sql = "select * from subject where subject_code=?";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, txt);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int count(String txt, Integer m, Integer e, String status) {
        try {
            String sql = "SELECT COUNT(*) as total FROM subject where (subject_name like ? or subject_code like ?)\n";

            Integer count = 2;
            HashMap<Integer, Object> params = new HashMap<>();
            if (m != -1) {
                count++;
                sql += "AND manager_id = ?\n";
                params.put(count, m);
            }
            if (e != -1) {
                count++;
                sql += "AND expert_id = ?\n";
                params.put(count, e);
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

    public ArrayList<Subject> pagging(int page_size, int page_index, String txt, Integer m, Integer e, String status) {
        UserDAO dbUser = new UserDAO();
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select subject_id,subject_code,subject_name, manager_id, expert_id, status,body,img_url \n"
                    + "                    from subject \n"
                    + "                    where (subject_name like ? or subject_code like ?)\n";

            Integer count = 2;
            HashMap<Integer, Object> params = new HashMap<>();
            if (m != -1) {
                count++;
                sql += "AND manager_id = ?\n";
                params.put(count, m);
            }
            if (e != -1) {
                count++;
                sql += "AND expert_id = ?\n";
                params.put(count, e);
            }
            if (!status.equals("-1")) {
                count++;
                sql += "AND status = ?\n";
                params.put(count, status);
            }
            sql += "order by subject_id asc\n"
                    + "limit ? , ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + txt + "%");
            stm.setString(2, "%" + txt + "%");
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            stm.setInt(count + 1, page_size * (page_index - 1));
            stm.setInt(count + 2, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setSubject_code(rs.getString("subject_code"));
                User manager = new User();
                manager.setUser_id(rs.getInt("manager_id"));
                manager = dbUser.get(manager);
                s.setManager(manager);
                User expert = new User();
                expert.setUser_id(rs.getInt("expert_id"));
                s.setExpert(dbUser.get(expert));
                s.setStatus(rs.getString("status"));
                s.setBody(rs.getString("body"));
                s.setImg_url(rs.getString("img_url"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Subject> list() {
        UserDAO dbUser = new UserDAO();
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select subject_id,subject_code,subject_name, manager_id, expert_id, status,body,img_url from subject";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setSubject_code(rs.getString("subject_code"));
                User manager = new User();
                manager.setUser_id(rs.getInt("manager_id"));
                manager = dbUser.get(manager);
                s.setManager(manager);
                User expert = new User();
                expert.setUser_id(rs.getInt("expert_id"));
                s.setExpert(dbUser.get(expert));
                s.setStatus(rs.getString("status"));
                s.setBody(rs.getString("body"));
                s.setImg_url(rs.getString("img_url"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Subject> list(int id) {
        UserDAO dbUser = new UserDAO();
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select subject_id,subject_code,subject_name, manager_id, expert_id, status,body,img_url from subject WHERE manager_id = ? or expert_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setSubject_code(rs.getString("subject_code"));
                User manager = new User();
                manager.setUser_id(rs.getInt("manager_id"));
                manager = dbUser.get(manager);
                s.setManager(manager);
                User expert = new User();
                expert.setUser_id(rs.getInt("expert_id"));
                s.setExpert(dbUser.get(expert));
                s.setStatus(rs.getString("status"));
                s.setBody(rs.getString("body"));
                s.setImg_url(rs.getString("img_url"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Subject> listSubjectByNameCode(String identity) {
        UserDAO dbUser = new UserDAO();
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM subject where subject_name like ? or subject_code like ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, "%" + identity + "%");
            stm.setString(2, "%" + identity + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setSubject_code(rs.getString("subject_code"));
                User manager = new User();
                manager.setUser_id(rs.getInt("manager_id"));
                manager = dbUser.get(manager);
                s.setManager(manager);
                User expert = new User();
                expert.setUser_id(rs.getInt("expert_id"));
                s.setExpert(dbUser.get(expert));
                s.setStatus(rs.getString("status"));
                s.setBody(rs.getString("body"));
                s.setImg_url(rs.getString("img_url"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Subject> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(Subject entity) {
        UserDAO dbUser = new UserDAO();
        try {
            String sql = "select subject_id,subject_code,subject_name, manager_id, expert_id, status,body,img_url from subject where subject_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSubject_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setSubject_code(rs.getString("subject_code"));
                User manager = new User();
                manager.setUser_id(rs.getInt("manager_id"));
                manager = dbUser.get(manager);
                s.setManager(manager);
                User expert = new User();
                expert.setUser_id(rs.getInt("expert_id"));
                s.setExpert(dbUser.get(expert));
                s.setStatus(rs.getString("status"));
                s.setBody(rs.getString("body"));
                s.setImg_url(rs.getString("img_url"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Subject entity) {
        try {
            String sql = "insert into subject values (?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSubject_id());
            stm.setString(2, entity.getSubject_code());
            stm.setString(3, entity.getSubject_name());
            stm.setInt(4, entity.getManager().getUser_id());
            stm.setInt(5, entity.getExpert().getUser_id());
            stm.setString(6, entity.getStatus());
            stm.setString(7, entity.getBody());
            stm.setString(8, entity.getImg_url());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject entity) {
        try {
            String sql = "update subject\n"
                    + "set subject_code = ?, subject_name = ? , manager_id = ?, expert_id=?, status = ?, body = ?\n"
                    + "where subject_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, entity.getSubject_code());
            stm.setString(2, entity.getSubject_name());
            stm.setInt(3, entity.getManager().getUser_id());
            stm.setInt(4, entity.getExpert().getUser_id());
            stm.setString(5, entity.getStatus());
            stm.setString(6, entity.getBody());
            stm.setInt(7, entity.getSubject_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateForManager(Subject entity) {
        try {
            String sql = "update subject\n"
                    + "set expert_id=?, status = ?\n"
                    + "where subject_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getExpert().getUser_id());
            stm.setString(2, entity.getStatus());
            stm.setInt(3, entity.getSubject_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeImg(String url, int id) {
        try {
            String sql = "update subject\n"
                    + "set img_url = ?\n"
                    + "where subject_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, url);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeStatus(int subject_id, String status) {
        try {
            String sql = "update subject set status= ? where subject_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, subject_id);
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
