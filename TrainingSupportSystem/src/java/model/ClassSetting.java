/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author win
 */
public class ClassSetting {

    private int setting_id;
    private Setting type;
    private String setting_title;
    private String setting_value;
    private String display_order;
    private Class class_set;
    private int status;
    private String description;

    public ClassSetting(int setting_id, Setting type, String setting_title, String setting_value, String display_order, Class class_set, int status, String description) {
        this.setting_id = setting_id;
        this.type = type;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.class_set = class_set;
        this.status = status;
        this.description = description;
    }

    public ClassSetting() {
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
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

    public Class getClass_set() {
        return class_set;
    }

    public void setClass_set(Class class_set) {
        this.class_set = class_set;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
