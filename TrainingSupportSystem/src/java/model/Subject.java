/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HAICAO
 */
public class Subject {
    private int subject_id;
    private String subject_code;
    private String subject_name;
    private User manager;
    private User expert;
    private String status;
    private String body;
    private String img_url;

    public Subject(int subject_id, String subject_code) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
    }

    public Subject(String subject_code) {
        this.subject_code = subject_code;
    }

    public Subject(int subject_id) {
        this.subject_id = subject_id;
    }

    public Subject(int subject_id, String subject_code, String subject_name, User manager, User expert, String status, String body, String img_url) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.manager = manager;
        this.expert = expert;
        this.status = status;
        this.body = body;
        this.img_url = img_url;
    }
    
    

    public Subject(int subject_id, User expert, String status) {
        this.subject_id = subject_id;
        this.expert = expert;
        this.status = status;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Subject() {
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getExpert() {
        return expert;
    }

    public void setExpert(User expert) {
        this.expert = expert;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    
    
}
