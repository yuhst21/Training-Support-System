/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mavjp
 */
public class SubjectSetting {
    private int id;
    private Subject subject;
    private Setting type;
    private String setting_title;
    private String setting_value;
    private String display_order;
    private boolean status;
    private String description;

    public SubjectSetting(int id, Subject subject, Setting type, String setting_title, String setting_value, String display_order, boolean status, String description) {
        this.id = id;
        this.subject = subject;
        this.type = type;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
        this.description = description;
    }

    
    
    
    public SubjectSetting(int id, Subject subject, String setting_title, String setting_value, boolean status, String description) {
        this.id = id;
        this.subject = subject;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.status = status;
        this.description = description;
    }

    public SubjectSetting() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Setting getType() {
        return type;
    }

    public void setType(Setting type) {
        this.type = type;
    }

    public String getSetting_title() {
        return setting_title;
    }

    public void setSetting_title(String setting_title) {
        this.setting_title = setting_title;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
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
    
    
}
