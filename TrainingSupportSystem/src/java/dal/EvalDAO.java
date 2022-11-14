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
import model.EvalCriteria;
import model.Milestone;
import model.Subject;

/**
 *
 * @author Admin
 */
public class EvalDAO extends DBContext<Object> {

    public List<Assignment> getAllAssignment() {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT * from assignment";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
    public List<Assignment> getAllAssignmentBySubject(String subject_id) {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT * from assignment where subject_id = ?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, subject_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<EvalCriteria> Search(String key, String subject, String status, String assignment) {
        List<EvalCriteria> list = new ArrayList<>();
        String sql = "SELECT e.criteria_id,e.ass_id,e.milestone_id,e.criteria_name,e.is_team_eval,e.max_loc,e.status,e.description,e.eval_weight,a.title,s.subject_id,s.subject_code FROM eval_criteria e inner join assignment a on e.ass_id = a.ass_id inner join milestone m on e.milestone_id = m.milestone_id inner join subject s on a.subject_id = s.subject_id where 1=1";
        if (key != "") {
            sql += " and e.criteria_name like " + "'%" + key + "%'";
        }
        if (subject != "") {
            sql += " and s.subject_id = " + "'" + subject + "'";
        }
        if (status != "") {
            sql += " and e.status = " + status;
        }
        if (assignment != "") {
            sql += " and e.ass_id = " + assignment;
        }

        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                EvalCriteria e = new EvalCriteria();
                Assignment a = new Assignment();
                Milestone m = new Milestone();
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                a.setSubject_id(s);
                a.setTitle(rs.getString("title"));
                a.setAss_id(rs.getInt("ass_id"));
                m.setMilestone_id(rs.getInt("milestone_id"));
                e.setMilestone(m);
                e.setAssignment(a);
                e.setCriteria_id(rs.getInt("criteria_id"));
                e.setCriteria_name(rs.getString("criteria_name"));
                e.setIs_team_eval(rs.getBoolean("is_team_eval"));
                e.setMax_loc(rs.getInt("max_loc"));
                e.setStatus(rs.getBoolean("status"));
                e.setDescription(rs.getString("description"));
                e.setEval_weight(rs.getInt("eval_weight"));

                list.add(e);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void changeStatus(int eval_id, String status) {
        try {
            String sql = "update eval_criteria set status= ? where criteria_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, eval_id);
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

    public List<EvalCriteria> getListByPage(List<EvalCriteria> list, int start, int end) {
        ArrayList<EvalCriteria> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public EvalCriteria getEvalById(int id) {
        String sql = "SELECT e.criteria_id,e.ass_id,e.milestone_id,e.criteria_name,e.is_team_eval,e.max_loc,e.status,e.description,e.eval_weight,a.title,s.subject_id,s.subject_code FROM eval_criteria e inner join assignment a on e.ass_id = a.ass_id inner join milestone m on e.milestone_id = m.milestone_id inner join subject s on a.subject_id = s.subject_id where e.criteria_id = ?";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                EvalCriteria e = new EvalCriteria();
                Assignment a = new Assignment();
                Milestone m = new Milestone();
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_code(rs.getString("subject_code"));
                a.setSubject_id(s);
                a.setTitle(rs.getString("title"));
                a.setAss_id(rs.getInt("ass_id"));
                m.setMilestone_id(rs.getInt("milestone_id"));
                e.setMilestone(m);
                e.setAssignment(a);
                e.setCriteria_id(rs.getInt("criteria_id"));
                e.setCriteria_name(rs.getString("criteria_name"));
                e.setIs_team_eval(rs.getBoolean("is_team_eval"));
                e.setMax_loc(rs.getInt("max_loc"));
                e.setStatus(rs.getBoolean("status"));
                e.setDescription(rs.getString("description"));
                e.setEval_weight(rs.getInt("eval_weight"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void Edit(String eval_name,boolean team_work,int max_loc,boolean status,String description,int evalweight,int eval_id) {
        try {
            String sql = "UPDATE `eval_criteria` SET `criteria_name` = ?, `is_team_eval` = ?, `max_loc` = ?, `status` = ?, `description` = ?, `eval_weight` = ? WHERE (`criteria_id` = ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, eval_name);
            stm.setBoolean(2, team_work);
            stm.setInt(3, max_loc);
            stm.setBoolean(4, status);
            stm.setString(5, description);
            stm.setInt(6, evalweight);
            stm.setInt(7, eval_id);
            stm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    public int countEval() {
        String sql = "SELECT criteria_id from eval_criteria order by criteria_id desc";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("criteria_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
    public void insertEval(String ass_id,String mile_id,String eval_name,String teamwork,String max_loc,String status,String description,String eval_weight) {
        try {
            String sql = "INSERT INTO `eval_criteria` (`criteria_id`, `ass_id`, `milestone_id`, `criteria_name`, `is_team_eval`, `max_loc`, `status`, `description`, `eval_weight`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1,countEval()+1);
            stm.setString(2,ass_id);
            stm.setString(3,mile_id);
            stm.setString(4,eval_name);
            stm.setString(5,teamwork);
            stm.setString(6,max_loc);
            stm.setString(7,status);
            stm.setString(8,description);
            stm.setString(9,eval_weight);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
