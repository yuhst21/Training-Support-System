/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Class;
import model.Class_user;
import model.Milestone;
import model.Team;
import model.Team_member;
import model.User;

/**
 *
 * @author Admin
 */
public class TeamDAO extends DBContext<Object> {

    public List<Team> getAllTeam(String class_id) {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT * FROM team where class_id = ?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, class_id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Class c = new Class();
                Team t = new Team();
                t.setTeam_id(rs.getInt("team_id"));
                c.setClass_id(rs.getInt("class_id"));
                t.setClass_id(c);
                t.setTeam_code(rs.getString("team_code"));
                t.setStatus(rs.getBoolean("status"));
                t.setTopic_name(rs.getString("topic_name"));
                t.setDescription(rs.getString("description"));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<User> getAllTrainee(String class_id) {
        List<User> list = new ArrayList<>();
        String sql = "select u.user_id,full_name,ur.role_id ,c.class_id from user u inner join user_role ur on u.user_id = ur.user_id inner join class_user c on u.user_id = c.user_id where class_id =?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, class_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setFull_name(rs.getString("full_name"));
                u.setUser_id(rs.getInt("user_id"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalMember(String class_id) {
        String sql = "select COUNT(*) as total from user u inner join user_role ur on u.user_id = ur.user_id inner join class_user c on u.user_id = c.user_id where class_id =?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, class_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    int z;
    int v;
    int y =0;
    public void inserTeam(int number_team, String class_id) {
        for (int i = 0; i < number_team; i++) {
            String sql = "INSERT INTO `team` (`team_id`, `class_id`, `team_code`, `topic_name`, `status`, `description`) VALUES (?, ?, ?, 'test', '1', 'test');";
            try {
                PreparedStatement st = conection.prepareStatement(sql);
                st.setInt(1, count() + 1);
                st.setString(2, class_id);
                st.setString(3, "G" + (y+1));
                y++;
                st.executeUpdate();

            } catch (Exception e) {

            }
        }
        y=0;
        v= count()-number_team+1;
    }public void inserTeamWaitingList( String class_id) {
        
            String sql = "INSERT INTO `team` (`team_id`, `class_id`, `team_code`, `topic_name`, `status`, `description`) VALUES (?, ?, ?, 'test', '1', 'test');";
            try {
                PreparedStatement st = conection.prepareStatement(sql);
                st.setInt(1, count() + 1);
                st.setString(2, class_id);
                st.setString(3, "Waiting list");
                
                st.executeUpdate();

            } catch (Exception e) {

            }
        
        
    }

   public void insertsubmit(int number_team, String class_id, String milestone) {
       
        for (int i = 0; i < number_team; i++) {
            String sql = "INSERT INTO `submit` (`submit_id`, `milestone_⁯id`, `class_id`, `team_id`) VALUES (?, ?, ?, ?);";
            try {
                PreparedStatement st = conection.prepareStatement(sql);
                z = countSubmit() + 1;
                st.setInt(1, z);
                st.setInt(2, Integer.parseInt(milestone));
                st.setInt(3, Integer.parseInt(class_id));
                
                st.setInt(4, v);
                v++;
                st.executeUpdate();

            } catch (Exception e) {

            }
        }

    }
   public void insertsubmitforWL( String class_id, String milestone) {
        
            String sql = "INSERT INTO `submit` (`submit_id`, `milestone_⁯id`, `class_id`, `team_id`) VALUES (?, ?, ?, ?);";
            try {
                PreparedStatement st = conection.prepareStatement(sql);
                z = countSubmit() + 1;
                st.setInt(1, z);
                st.setString(2, milestone);
                st.setString(3, class_id);
                
                st.setInt(4, v);
                v++;
                st.executeUpdate();

            } catch (Exception e) {

            }
        

    }
    
    public void insertsubmitfor1team(int team_id, int class_id, String milestone) {
        
            String sql = "INSERT INTO `submit` (`submit_id`, `milestone_⁯id`, `class_id`, `team_id`) VALUES (?, ?, ?, ?);";
            try {
                PreparedStatement st = conection.prepareStatement(sql);
                z = countSubmit() + 1;
                st.setInt(1, z);
                st.setString(2, milestone);
                st.setInt(3, class_id);
                
                st.setInt(4, team_id);
                
                st.executeUpdate();

            } catch (Exception e) {

            }
        

    }
    public void insertsubmitReuse(int start_team,int end_team, String class_id, String milestone) {
        for (int i = start_team; i < end_team+1; i++) {
            String sql = "INSERT INTO `submit` (`submit_id`, `milestone_⁯id`, `class_id`, `team_id`) VALUES (?, ?, ?, ?);";
            try {
                PreparedStatement st = conection.prepareStatement(sql);
                z = countSubmit() + 1;
                st.setInt(1, z);
                st.setString(2, milestone);
                st.setString(3, class_id);
                
                st.setInt(4, i);
                ;
                st.executeUpdate();

            } catch (Exception e) {

            }
        }

    }

    public void inserTeamMember(int team_id, int user_id,String leader) {

        String sql = "INSERT INTO `team_member` (`team_id`, `user_id`, `is_leader`, `is_active`) VALUES (?, ?, ?, '1');";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, team_id);
            st.setInt(2, user_id);
            st.setString(3,leader);
            st.executeUpdate();

        } catch (Exception e) {

        }

    }

    public List<Class> getAllClass(int user_id) {
        List<Class> list = new ArrayList<>();
        String sql = "SELECT class_id,class_code FROM class where trainer_id = ?;";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

//    public List<Team> getTeamByClass(String class_id,String team_code, String status) {
//        List<Team> list = new ArrayList<>();
//        String sql = "SELECT * FROM team where 1=1 ";
//        if(class_id !=""){
//            sql+= " and class_id = "+class_id;
//        }
//        if(team_code!=""){
//            sql+=" and team_code like " +"'%"+team_code+"%'";
//        }if(status!=""){
//            sql+= " and status = "+status;
//        }
//        try {
//            
//            PreparedStatement stm = conection.prepareStatement(sql);
////            stm.setString(1, class_id);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Team t = new Team();
//                Class c = new Class();
//                
//                c.setClass_id(rs.getInt("class_id"));
//                t.setClass_id(c);
//                t.setTeam_id(rs.getInt("team_id"));
//                t.setTeam_code(rs.getString("team_code"));
//                t.setTopic_name(rs.getString("topic_name"));
//                t.setStatus(rs.getBoolean("status"));
//                t.setDescription(rs.getString("description"));
//                
//                list.add(t);
//
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return list;
//    }
    public List<Team> getTeamByClass(String class_id, String team_code, String status, String milestone) {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT team.team_id,team.class_id,team.team_code,team.topic_name,team.status,team.description,submit.milestone_⁯id FROM team inner join submit on team.team_id = submit.team_id where 1=1  ";
        if (class_id != "") {
            sql += " and team.class_id = " + class_id;
        }
        if (team_code != "") {
            sql += " and team.team_code like " + "'%" + team_code + "%'";
        }
        if (status != "") {
            sql += " and team.status = " + status;
        }
        if (milestone != "") {
            sql += " and submit.milestone_⁯id = " + milestone;
        }
        try {

            PreparedStatement stm = conection.prepareStatement(sql);
//            stm.setString(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Team t = new Team();
                Class c = new Class();

                c.setClass_id(rs.getInt("class_id"));
                t.setClass_id(c);
                t.setTeam_id(rs.getInt("team_id"));
                t.setTeam_code(rs.getString("team_code"));
                t.setTopic_name(rs.getString("topic_name"));
                t.setStatus(rs.getBoolean("status"));
                t.setDescription(rs.getString("description"));

                list.add(t);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Team> getTeamByClass(String class_id, int user_role, int team_id, int user_id) {
        List<Team> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM team t inner join team_member tm on t.team_id = tm.team_id inner join class c on t.class_id = c.class_id\n"
                    + "inner join user_role u on tm.user_id = u.user_id where c.class_id = ? and role_id = ? and t.team_id = ? and u.user_id =?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, class_id);
            stm.setInt(2, user_role);
            stm.setInt(3, team_id);
            stm.setInt(4, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Team t = new Team();
                Class c = new Class();
                c.setClass_id(rs.getInt("class_id"));
                t.setClass_id(c);
                t.setTeam_id(rs.getInt("team_id"));
                t.setTeam_code(rs.getString("team_code"));
                t.setTopic_name(rs.getString("topic_name"));
                t.setStatus(rs.getBoolean("status"));
                t.setDescription(rs.getString("description"));
                list.add(t);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Team_member getTeamByUser(User u) {
        String sql = "SELECT * FROM team_member where user_id = ?";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, u.getUser_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Team_member tm = new Team_member();
                Team t = new Team();
                t.setTeam_id(rs.getInt("team_id"));
                tm.setTeam(t);
                return tm;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Team_member> getTeamMemberByTeam(String team_id) {
        List<Team_member> list = new ArrayList<>();
        try {
            String sql = "SELECT team_id,team_member.user_id,is_leader,is_active,full_name FROM team_member inner join user where team_member.user_id = user.user_id and team_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, team_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Team_member tm = new Team_member();
                Team t = new Team();
                User u = new User();
                t.setTeam_id(rs.getInt("team_id"));
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                tm.setTeam(t);
                tm.setUser(u);
                tm.setIs_leader(rs.getBoolean("is_leader"));
                tm.setIs_active(rs.getBoolean("is_active"));
                list.add(tm);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int count() {
        String sql = "SELECT team_id from team order by team_id desc";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("team_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
    public int endTeamInSubmit(String milestone) {
        String sql = "select team_id from submit where milestone_⁯id = ? order by team_id desc";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, milestone);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("team_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
    public int countTeamInSubmit(String milestone) {
        String sql = "select count(*) as total from submit where milestone_⁯id = ? ";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, milestone);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
    
    

    public int countTeam() {
        String sql = "SELECT team_id from team_member order by team_id desc";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("team_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int countSubmit() {
        String sql = "SELECT submit_id from submit order by submit_id desc";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("submit_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void deleteMember(String id) {
        String sql = "DELETE FROM `team_member` WHERE (`user_id` = '?')";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void changeStatus(int team_id, String status) {
        try {
            String sql = "update team set status= ? where team_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, team_id);
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

    public void changeStatusMember(int user_id, String status) {
        try {
            String sql = "update team_member set is_active= ? where user_id= ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(2, user_id);
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

    public void Edit(Team_member t) {
        try {
            String sql = "UPDATE `team_member` SET `team_id` = ?,`is_leader` = ?, `is_active` = ? WHERE (`user_id` = ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, t.getTeam().getTeam_id());
            stm.setBoolean(2, t.isIs_leader());
            stm.setBoolean(3, t.isIs_active());
            stm.setInt(4, t.getUser().getUser_id());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void EditLeader(Team_member t) {
        try {
            String sql = "UPDATE `team_member` SET `is_leader` = '0' WHERE (`user_id` = ?) and (`team_id` = ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
             stm.setInt(1, t.getUser().getUser_id());
            stm.setInt(2, t.getTeam().getTeam_id());
            
           
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Team_member getMemberById(int id) {
        String sql = "SELECT * FROM team_member where user_id=?;";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Team_member tm = new Team_member();
                Team t = new Team();
                t.setTeam_id(rs.getInt("team_id"));
                tm.setTeam(t);
                tm.setIs_active(rs.getBoolean("is_active"));
                tm.setIs_leader(rs.getBoolean("is_leader"));

                return tm;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }public int getTeamById(int id) {
        String sql = "SELECT * FROM team_member where user_id=?;";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("team_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public ArrayList<Class_user> list(int class_id) {
        ArrayList<Class_user> list = new ArrayList<>();
        try {
            String sql = "SELECT cu.class_id,cu.user_id,cu.status,cu.note,cu.ongoing_eval,cu.dropout_date,\n"
                    + "cu.final_eval,cu.topic_eval,u.full_name,u.email,c.class_code\n"
                    + " FROM class_user cu inner join user u on \n"
                    + "cu.user_id = u.user_id inner join class c on cu.class_id = c.class_id where c.class_id = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, class_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                model.Class c = new model.Class();
                c.setClass_id(rs.getInt("class_id"));
                c.setClass_name(rs.getString("class_code"));
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                Class_user cu = new Class_user();
                cu.setClass_id(c);
                cu.setUser(u);
                cu.setNote(rs.getString("note"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getString("ongoing_eval"));
                cu.setFinal_eval(rs.getString("final_eval"));
                cu.setTopic_eval(rs.getString("topic_eval"));
                list.add(cu);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String getClassCodeById(String class_id) {
        String sql = "SELECT * FROM class where class_id=?;";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, class_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("class_code");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insert(Team t) {
        try {
            String sql = "INSERT INTO `team` (`team_id`, `class_id`, `team_code`, `topic_name`, `status`, `description`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, t.getTeam_id());
            stm.setInt(2, t.getClass_id().getClass_id());
            stm.setString(3, t.getTeam_code());
            stm.setString(4, t.getTopic_name());
            stm.setBoolean(5, t.isStatus());
            stm.setString(6, t.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Team> getListByPage(List<Team> list, int start, int end) {
        ArrayList<Team> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public String getClassByUser(int user_id) {
        String sql = "SELECT * FROM class_user where user_id=?;";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("class_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Milestone> getAllMileStone() {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT * FROM milestone;";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Milestone m = new Milestone();
                m.setMilestone_id(rs.getInt("milestone_id"));
                m.setTittle(rs.getString("tittle"));
                list.add(m);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
     public List<Milestone> getAllMileStoneByAssignment(String ass_id) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT * FROM milestone where ass_id = ?";
        try {
            PreparedStatement st = conection.prepareStatement(sql);
            st.setString(1, ass_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Milestone m = new Milestone();
                m.setMilestone_id(rs.getInt("milestone_id"));
                m.setTittle(rs.getString("tittle"));
                list.add(m);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
    public List<Team_member> getTeamMemberById(int user_id,int team_id) {
        List<Team_member> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM team_member where team_id = ? and user_id != ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, team_id);
            stm.setInt(2, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Team_member tm = new Team_member();
                Team t = new Team();
                User u = new User();
                t.setTeam_id(rs.getInt("team_id"));
                u.setUser_id(rs.getInt("user_id"));
              
                tm.setTeam(t);
                tm.setUser(u);
                tm.setIs_leader(rs.getBoolean("is_leader"));
                tm.setIs_active(rs.getBoolean("is_active"));
                list.add(tm);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public Team getTeamByTeamId(String id) {
        String sql = "SELECT * FROM Team where team_id=?;";
        try {
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Team t = new Team();
                Class c = new Class();
                t.setTeam_id(rs.getInt("team_id"));
                c.setClass_id(rs.getInt("class_id"));
                t.setClass_id(c);
                t.setTeam_code(rs.getString("team_code"));
                t.setStatus(rs.getBoolean("status"));
                t.setTopic_name(rs.getString("topic_name"));
                t.setDescription(rs.getString("description"));
                
                return t;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
     public void EditTeam(String team_code,String topic_name,String status,String description,String team_id) {
        try {
            String sql = "UPDATE `team` SET `team_code` = ?, `topic_name` = ?, `status` = ?, `description` = ? WHERE (`team_id` = ?);";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, team_code);
            stm.setString(2, topic_name);
            stm.setString(3, status);
            stm.setString(4, description);
            stm.setString(5, team_id);
            
            stm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex);
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
