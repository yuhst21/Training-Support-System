/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author win
 */
public class Class {
    private int class_id;
    private String class_name;
    private Subject subject;
    private int trainer_id;
    private int supporter_id;
    private int term_id;
    private boolean status;
    private String description;
    private String trainee_name;
    private String supporter_name;
    private String term_name;

    public Class() {
    }

    public Class(int class_id, String class_name, Subject subject, int trainer_id, int supporter_id, int term_id, boolean status, String description) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.subject = subject;
        this.trainer_id = trainer_id;
        this.supporter_id = supporter_id;
        this.term_id = term_id;
        this.status = status;
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getSupporter_id() {
        return supporter_id;
    }

    public void setSupporter_id(int supporter_id) {
        this.supporter_id = supporter_id;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTrainee_name() {
        return trainee_name;
    }

    public void setTrainee_name(String trainee_name) {
        this.trainee_name = trainee_name;
    }

    public String getSupporter_name() {
        return supporter_name;
    }

    public void setSupporter_name(String supporter_name) {
        this.supporter_name = supporter_name;
    }

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }
    
    
}
