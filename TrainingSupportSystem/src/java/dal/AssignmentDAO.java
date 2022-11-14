/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Subject;

/**
 *
 * @author Admin
 */
public class AssignmentDAO extends DBContext<Object> {

    public List<Assignment> getAll() {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT ass_id,assignment.subject_id,subject_code,title,ass_body,eval_weight,is_team_work,is_ongoing,assignment.status FROM assignment inner join subject on assignment.subject_id = subject.subject_id;";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                a.setAss_id(rs.getInt("ass_id"));
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                a.setSubject_id(s);
                a.setTitle(rs.getString("title"));
                a.setAss_body(rs.getString("ass_body"));
                a.setEval_weight(rs.getDouble("eval_weight"));
                a.setIs_team_work(rs.getBoolean("is_team_work"));
                a.setIs_ongoing(rs.getBoolean("is_ongoing"));
                a.setStatus(rs.getBoolean("status"));
                list.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
    public List<Assignment> getAllByManager(int user_id) {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT ass_id,assignment.subject_id,subject_code,title,ass_body,eval_weight,is_team_work,is_ongoing,assignment.status FROM assignment inner join subject on assignment.subject_id = subject.subject_id where subject.manager_id = ?;";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                a.setAss_id(rs.getInt("ass_id"));
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                a.setSubject_id(s);
                a.setTitle(rs.getString("title"));
                a.setAss_body(rs.getString("ass_body"));
                a.setEval_weight(rs.getDouble("eval_weight"));
                a.setIs_team_work(rs.getBoolean("is_team_work"));
                a.setIs_ongoing(rs.getBoolean("is_ongoing"));
                a.setStatus(rs.getBoolean("status"));
                list.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Assignment> getListByPage(List<Assignment> list, int start, int end) {
        ArrayList<Assignment> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void insert(Assignment a) {
        try {
            String sql = "INSERT INTO `assignment` (`ass_id`, `subject_id`, `title`, `ass_body`, `eval_weight`, `is_team_work`, `is_ongoing`, `status`) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, a.getAss_id());
            stm.setInt(2, a.getSubject_id().getSubject_id());
            stm.setString(3, a.getTitle());
            stm.setString(4, a.getAss_body());
            stm.setDouble(5, a.getEval_weight());
            stm.setBoolean(6, a.isIs_team_work());
            stm.setBoolean(7, a.isIs_ongoing());
            stm.setBoolean(8, a.isStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Subject> getAllSubject() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject;";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Subject> getAllSubjectByManager(int user_id) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject where manager_id = ?;";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int countID() {
        try {
            String sql = "select * from assignment order by ass_id desc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("ass_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public String getCodeById(int id) {
        try {
            String sql = "SELECT subject_code FROM  subject where subject_id = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("subject_code");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void Edit(Assignment a) {
        try {
            String sql = "UPDATE `assignment` SET `subject_id` = ?, `title` = ?, `ass_body` = ?, `eval_weight` = ?, `is_team_work` = ?, `is_ongoing` = ?, `status` = ? WHERE (`ass_id` = ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, a.getSubject_id().getSubject_id());
            stm.setString(2, a.getTitle());
            stm.setString(3, a.getAss_body());
            stm.setDouble(4, a.getEval_weight());
            stm.setBoolean(5, a.isIs_team_work());
            stm.setBoolean(6, a.isIs_ongoing());
            stm.setBoolean(7, a.isStatus());
            stm.setInt(8, a.getAss_id());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public int getIdByCode(String code) {
        try {
            String sql = "SELECT subject_id FROM  subject where subject_code = ?;";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, code);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("subject_id");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    public void changeStatus(int ass_id, String status) {
        try {
            String sql = "update assignment set status= ? where ass_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, ass_id);
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
    public Assignment getAssignmentById(int id) {
        String sql = "SELECT * FROM assignment where ass_id=?;";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                a.setAss_id(rs.getInt("ass_id"));
                s.setSubject_id(rs.getInt("subject_id"));
                
                a.setSubject_id(s);
                a.setTitle(rs.getString("title"));
                a.setAss_body(rs.getString("ass_body"));
                a.setEval_weight(rs.getDouble("eval_weight"));
                a.setIs_team_work(rs.getBoolean("is_team_work"));
                a.setIs_ongoing(rs.getBoolean("is_ongoing"));
                a.setStatus(rs.getBoolean("status"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Assignment> Search(String key,String subject,String status,int user_id) {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT ass_id,assignment.subject_id,subject_code,title,ass_body,eval_weight,is_team_work,is_ongoing,assignment.status FROM assignment inner join subject on assignment.subject_id = subject.subject_id where manager_id = ?";
        
        if(key!=""){
          sql+=" and title like " +"'%"+key+"%'";
        }
        if(subject!=""){
            sql+=" and assignment.subject_id = "+"'"+subject+"'";
        }if(status!=""){
            sql+= " and assignment.status = "+status;
        }
        
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user_id);
//            stm.setString(1, "%" + key + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                a.setAss_id(rs.getInt("ass_id"));
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                a.setSubject_id(s);
                a.setTitle(rs.getString("title"));
                a.setAss_body(rs.getString("ass_body"));
                a.setEval_weight(rs.getDouble("eval_weight"));
                a.setIs_team_work(rs.getBoolean("is_team_work"));
                a.setIs_ongoing(rs.getBoolean("is_ongoing"));
                a.setStatus(rs.getBoolean("status"));
                list.add(a);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
   
    

    @Override
    public ArrayList<Object> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Object> list(String identity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(Object entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Object entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object entity
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
