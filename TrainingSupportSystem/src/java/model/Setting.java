/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author win
 */
public class Setting {
    private int setting_id;
    private int type_id;
    private String setting_tiltle;
    private String setting_value;
    private int display_order;
    private int status;
    private String description;
    private String type_name;

    public Setting() {
    }


    public Setting(int setting_id, int type_id, String setting_tiltle, String setting_value, int display_order, int status, String description) {

        this.setting_id = setting_id;
        this.type_id = type_id;
        this.setting_tiltle = setting_tiltle;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
        this.description = description;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    


    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getSetting_tiltle() {
        return setting_tiltle;
    }

    public void setSetting_tiltle(String setting_tiltle) {
        this.setting_tiltle = setting_tiltle;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
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
