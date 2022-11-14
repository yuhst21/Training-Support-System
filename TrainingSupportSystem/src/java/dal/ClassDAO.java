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

/**
 *
 * @author win
 */
public class ClassDAO extends DBContext<Class> {

    public int countID() {
        try {
            String sql = "select * from class order by class_id desc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("class_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<model.Class> listAllClass() {
        ArrayList<model.Class> listClass = new ArrayList<>();
        try {
            String sql = "SELECT c.*,s.subject_code,se.setting_title,u.full_name as supporter_name,us.full_name as trainername FROM class c inner join subject s\n"
                    + "on c.subject_id =s.subject_id inner join setting se\n"
                    + "on c.term_id = se.setting_id inner join user u \n"
                    + "on c.supporter_id = u.user_id inner join user us on c.trainer_id = us.user_id";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class cl = new model.Class();
                cl.setClass_id(rs.getInt("class_id"));
                cl.setClass_name(rs.getString("class_code"));
                cl.setSupporter_id(rs.getInt("supporter_id"));
                cl.setTrainer_id(rs.getInt("trainer_id"));
                cl.setTerm_id(rs.getInt("term_id"));
                cl.setStatus(rs.getBoolean("status"));
                cl.setDescription(rs.getString("description"));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                cl.setSubject(s);
                cl.setTerm_name(rs.getString("setting_title"));
                cl.setSupporter_name(rs.getString("supporter_name"));
                cl.setTrainee_name(rs.getString("trainername"));
                cl.setTerm_name(rs.getString("setting_title"));
                listClass.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClass;

    }

    public ArrayList<model.Class> listTermType() {
        ArrayList<model.Class> list = new ArrayList<>();
        try {
            String sql = "select * from setting s where s.type_id =6";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setTerm_id(rs.getInt("setting_id"));
                c.setTerm_name(rs.getString("setting_title"));
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<model.Class> listAllClassByManager(int manager_id) {
        ArrayList<model.Class> listClass = new ArrayList<>();
        try {
            String sql = "SELECT c.*,s.subject_code,se.setting_title,u.full_name as supporter_name,us.full_name as trainername FROM class c inner join subject s\n"
                    + "                    on c.subject_id =s.subject_id inner join setting se\n"
                    + "                    on c.term_id = se.setting_id inner join user u \n"
                    + "                    on c.supporter_id = u.user_id inner join user us on c.trainer_id = us.user_id \n"
                    + "                    where s.manager_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, manager_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class cl = new model.Class();
                cl.setClass_id(rs.getInt("class_id"));
                cl.setClass_name(rs.getString("class_code"));
                cl.setSupporter_id(rs.getInt("supporter_id"));
                cl.setTrainer_id(rs.getInt("trainer_id"));
                cl.setTerm_id(rs.getInt("term_id"));
                cl.setStatus(rs.getBoolean("status"));
                cl.setDescription(rs.getString("description"));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                cl.setSubject(s);
                cl.setTerm_name(rs.getString("setting_title"));
                cl.setSupporter_name(rs.getString("supporter_name"));
                cl.setTrainee_name(rs.getString("trainername"));
                cl.setTerm_name(rs.getString("setting_title"));
                listClass.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClass;

    }

    public int count(Integer supporter_id, Integer trainer_id, Integer class_id, Integer status,int manager_id) {

        String sql = "SELECT COUNT(*) as total FROM class c inner join subject s\n"
                + "on c.subject_id = s.subject_id \n"
                + " where (1=1) and s.manager_id = ?";

        Integer count = 1;
        HashMap<Integer, Object> params = new HashMap<>();
        if (supporter_id != -1) {
            count++;
            sql += "and supporter_id = ? \n ";
            params.put(count, supporter_id);
        }
        if (trainer_id != -1) {
            count++;
            sql += "and trainer_id = ? \n ";
            params.put(count, trainer_id);
        }
        if (class_id != -1) {
            count++;
            sql += "and class_id = ? \n ";
            params.put(count, class_id);
        }
        if (status != -1) {
            count++;
            sql += "and status = ? \n ";
            params.put(count, status);
        }

        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, manager_id);
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

    public ArrayList<model.Class> paggingV2(Integer page_size, Integer page_index, Integer supporter_id, Integer trainer_id, Integer class_id, Integer status,int manager_id) {
        String sql = "SELECT c.*,s.subject_code,se.setting_title,u.full_name as supporter_name,us.full_name as trainername FROM class c inner join subject s\n"
                + "on c.subject_id =s.subject_id inner join setting se\n"
                + "on c.term_id = se.setting_id inner join user u \n"
                + "on c.supporter_id = u.user_id inner join user us on c.trainer_id = us.user_id where (1=1) and s.manager_id = ? ";
        Integer count = 1;
        HashMap<Integer, Object> params = new HashMap<>();
        if (supporter_id != -1) {
            count++;
            sql += "and c.supporter_id = ? \n ";
            params.put(count, supporter_id);
        }
        if (trainer_id != -1) {
            count++;
            sql += "and c.trainer_id = ? \n ";
            params.put(count, trainer_id);
        }
        if (class_id != -1) {
            count++;
            sql += "and c.class_id = ? \n ";
            params.put(count, class_id);
        }
        if (status != -1) {
            count++;
            sql += "and c.status = ? \n ";
            params.put(count, status);
        }

        sql += "order by c.class_id asc limit ?,?";
        ArrayList<model.Class> list = new ArrayList<>();

        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, manager_id);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            stm.setInt(count + 1, page_size * (page_index - 1));
            stm.setInt(count + 2, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class cl = new model.Class();
                cl.setClass_id(rs.getInt("class_id"));
                cl.setClass_name(rs.getString("class_code"));
                cl.setSupporter_id(rs.getInt("supporter_id"));
                cl.setTrainer_id(rs.getInt("trainer_id"));
                cl.setTerm_id(rs.getInt("term_id"));
                cl.setStatus(rs.getBoolean("status"));
                cl.setDescription(rs.getString("description"));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                cl.setSubject(s);
                cl.setTerm_name(rs.getString("setting_title"));
                cl.setSupporter_name(rs.getString("supporter_name"));
                cl.setTrainee_name(rs.getString("trainername"));
                list.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void changeStatus(int setting_id, String status) {
        try {
            String sql = "update class set status= ? where class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, setting_id);
            if (status.equals("false")) {
                stm.setString(1, "1");
            } else {
                stm.setString(1, "0");
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(model.Class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Class> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class get(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public model.Class getByClass(model.Class entity) {
        try {
            String sql = "SELECT c.*,s.subject_code,se.setting_title,u.full_name as supporter_name,us.full_name as trainername FROM class c inner join subject s\n"
                    + "on c.subject_id =s.subject_id inner join setting se\n"
                    + "on c.term_id = se.setting_id inner join user u \n"
                    + "on c.supporter_id = u.user_id inner join user us on c.trainer_id = us.user_id where c.class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getClass_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.Class cl = new model.Class();
                cl.setClass_id(rs.getInt("class_id"));
                cl.setClass_name(rs.getString("class_code"));
                cl.setSupporter_id(rs.getInt("supporter_id"));
                cl.setTrainer_id(rs.getInt("trainer_id"));
                cl.setTerm_id(rs.getInt("term_id"));
                cl.setStatus(rs.getBoolean("status"));
                cl.setDescription(rs.getString("description"));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                cl.setSubject(s);
                cl.setTerm_name(rs.getString("setting_title"));
                cl.setSupporter_name(rs.getString("supporter_name"));
                cl.setTrainee_name(rs.getString("trainername"));
                return cl;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updatebyClass(model.Class entity) {
        try {
            String sql = "UPDATE `class`\n"
                    + "SET\n"
                    + "`class_code` = ?,\n"
                    + "`supporter_id` = ?,\n"
                    + "`trainer_id` =?,\n"
                    + "`term_id` = ?,\n"
                    + "`status` = ?,\n"
                    + "`description` =?,\n"
                    + "`subject_id` = ?\n"
                    + "WHERE `class_id` = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);

            stm.setString(1, entity.getClass_name());
            stm.setInt(2, entity.getSupporter_id());
            stm.setInt(3, entity.getTrainer_id());
            stm.setInt(4, entity.getTerm_id());
            stm.setBoolean(5, entity.isStatus());
            stm.setString(6, entity.getDescription());
            stm.setInt(7, entity.getSubject().getSubject_id());
            stm.setInt(8, entity.getClass_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertByClass(model.Class entity) {
        try {
            String sql = "insert into class values (?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getClass_id());
            stm.setString(2, entity.getClass_name());
            stm.setInt(3, entity.getSupporter_id());
            stm.setInt(4, entity.getTrainer_id());
            stm.setInt(5, entity.getTerm_id());
            stm.setBoolean(6, entity.isStatus());
            stm.setString(7, entity.getDescription());
            stm.setInt(8, entity.getSubject().getSubject_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Class> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
