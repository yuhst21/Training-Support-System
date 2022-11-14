/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class Class_user implements Comparator<Class_user>{

    private Class class_id;
    private User user;
    private boolean status;
    private String note;
    private Date dropout_date;
    private String ongoing_eval;
    private String final_eval;
    private String topic_eval;

    public Class_user() {
    }

    public Class_user(Class class_id, User user, boolean status, String note, Date dropout_date, String ongoing_eval, String final_eval, String topic_eval) {
        this.class_id = class_id;
        this.user = user;
        this.status = status;
        this.note = note;
        this.dropout_date = dropout_date;
        this.ongoing_eval = ongoing_eval;
        this.final_eval = final_eval;
        this.topic_eval = topic_eval;
    }

    public Class_user(Class class_id, User user, boolean status, String note, Date dropout_date) {
        this.class_id = class_id;
        this.user = user;
        this.status = status;
        this.note = note;
        this.dropout_date = dropout_date;
    }
    public Class getClass_id() {
        return class_id;
    }

    public void setClass_id(Class class_id) {
        this.class_id = class_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Date getDropout_date() {
        return dropout_date;
    }

    public void setDropout_date(Date dropout_date) {
        this.dropout_date = dropout_date;
    }

    public String getOngoing_eval() {
        return ongoing_eval;
    }

    public void setOngoing_eval(String ongoing_eval) {
        this.ongoing_eval = ongoing_eval;
    }

    public String getFinal_eval() {
        return final_eval;
    }

    public void setFinal_eval(String final_eval) {
        this.final_eval = final_eval;
    }

    public String getTopic_eval() {
        return topic_eval;
    }

    public void setTopic_eval(String topic_eval) {
        this.topic_eval = topic_eval;
    }

    @Override
    public int compare(Class_user o1, Class_user o2) {
        if(o1.getUser().getUser_id() > o2.getUser().getUser_id()){
            return 1;
        }else if (o1.getUser().getUser_id() < o2.getUser().getUser_id()){
            return -1;
        }else {
            return 0;
        }      
    }

}
