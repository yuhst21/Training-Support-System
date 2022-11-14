/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Assignment {
    private int ass_id;
    private Subject subject_id;
    private String title;
    private String ass_body;
    private double eval_weight;
    private boolean is_team_work;
    private boolean is_ongoing;
    private boolean status;

    public Assignment() {
    }

    public Assignment(int ass_id, Subject subject_id, String title, String ass_body, double eval_weight, boolean is_team_work, boolean is_ongoing, boolean status) {
        this.ass_id = ass_id;
        this.subject_id = subject_id;
        this.title = title;
        this.ass_body = ass_body;
        this.eval_weight = eval_weight;
        this.is_team_work = is_team_work;
        this.is_ongoing = is_ongoing;
        this.status = status;
    }

    public int getAss_id() {
        return ass_id;
    }

    public void setAss_id(int ass_id) {
        this.ass_id = ass_id;
    }

    public Subject getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Subject subject_id) {
        this.subject_id = subject_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAss_body() {
        return ass_body;
    }

    public void setAss_body(String ass_body) {
        this.ass_body = ass_body;
    }

    public double getEval_weight() {
        return eval_weight;
    }

    public void setEval_weight(double eval_weight) {
        this.eval_weight = eval_weight;
    }

    public boolean isIs_team_work() {
        return is_team_work;
    }

    public void setIs_team_work(boolean is_team_work) {
        this.is_team_work = is_team_work;
    }

    public boolean isIs_ongoing() {
        return is_ongoing;
    }

    public void setIs_ongoing(boolean is_ongoing) {
        this.is_ongoing = is_ongoing;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
}
