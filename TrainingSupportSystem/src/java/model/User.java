/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.AttendanceDAO;
import java.util.ArrayList;

/**
 *
 * @author HAICAO
 */
public class User {

    private int user_id;
    private String full_name;
    private String email;
    private boolean status;
    private String note;
    private String mobile;
    private String password;
    private String avatar_url;
    private String address;
    private String position;
    private String company;
    private String username;
    private ArrayList<Setting> list = new ArrayList<>();
    private ArrayList<Attendance> attends = new ArrayList<>();
    
    public User() {
        
    }

    public User(int user_id, String full_name, String email, boolean status, String note, String mobile, String password, String avatar_url, String position, String address, String company) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.status = status;
        this.note = note;
        this.mobile = mobile;
        this.password = password;
        this.avatar_url = avatar_url;
        this.position = position;
        this.address = address;
        this.company = company;
    }
    public User(int user_id,String email, String pass) {
        this.user_id = user_id;
        this.email = email;
        this.password = pass;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User(int user_id, String full_name, String email, boolean status) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.status = status;
    }
    
    public User(String email, String pass) {
        this.email = email;
        this.password = pass;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    public boolean isAttend(Schedule s) {
        for(Attendance a: attends) {
            if(a.getSchedule().getSchedule_id()== s.getSchedule_id()) {
                if(a.isStatus()) return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public ArrayList<Attendance> getAttends() {
        return attends;
    }

    public void setAttends(ArrayList<Attendance> attends) {
        this.attends = attends;
    }
    
    public String getAbsent(int class_id) {
        AttendanceDAO db = new AttendanceDAO();
        float count = 0;
        for (Attendance a : attends) {
            if(!a.isStatus()) count++;
        }
        return (int)Math.ceil(count/db.countSchedule(class_id)*100) + "%";
    }
    
    public Attendance getAttend(int sche_id) {
        for (Attendance attend : attends) {
            if(attend.getSchedule().getSchedule_id() == sche_id) return attend;
        }
        return null;
    }
    public User(int user_id,String email) {
        this.user_id = user_id;
        this.email = email;
       // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public User(String email) {
        this.email = email; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User(String name, String email, String code) {
        this.full_name = name;
        this.email = email;
        this.password = code;
    }

    public User(int user_id) {
        this.user_id  = user_id;
    }

    

    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<Setting> getList() {
        return list;
    }

    public void setList(ArrayList<Setting> list) {
        this.list = list;
    }
    
    
}
