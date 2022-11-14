/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class EvalCriteria {
    private int criteria_id;
    private Assignment assignment;
    private Milestone milestone;
    private String criteria_name;
    private Boolean is_team_eval;
    private int eval_weight;
    private int max_loc;
    private Boolean status;
    private String description;

    public EvalCriteria(int criteria_id, Assignment assignment, Milestone milestone, String criteria_name, Boolean is_team_eval, int eval_weight, int max_loc, Boolean status, String description) {
        this.criteria_id = criteria_id;
        this.assignment = assignment;
        this.milestone = milestone;
        this.criteria_name = criteria_name;
        this.is_team_eval = is_team_eval;
        this.eval_weight = eval_weight;
        this.max_loc = max_loc;
        this.status = status;
        this.description = description;
    }

    public EvalCriteria() {
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public String getCriteria_name() {
        return criteria_name;
    }

    public void setCriteria_name(String criteria_name) {
        this.criteria_name = criteria_name;
    }

    public Boolean getIs_team_eval() {
        return is_team_eval;
    }

    public void setIs_team_eval(Boolean is_team_eval) {
        this.is_team_eval = is_team_eval;
    }

    public int getEval_weight() {
        return eval_weight;
    }

    public void setEval_weight(int eval_weight) {
        this.eval_weight = eval_weight;
    }

    public int getMax_loc() {
        return max_loc;
    }

    public void setMax_loc(int max_loc) {
        this.max_loc = max_loc;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
