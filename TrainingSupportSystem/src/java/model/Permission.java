/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mavjp
 */
public class Permission {
    private Setting role;
    private Setting screen;
    private boolean get_all_data;
    private boolean can_delete;
    private boolean can_add;
    private boolean can_edit;

    public Permission() {
    }

    public Permission(Setting role, Setting screen, boolean get_all_data, boolean can_delete, boolean can_add, boolean can_edit) {
        this.role = role;
        this.screen = screen;
        this.get_all_data = get_all_data;
        this.can_delete = can_delete;
        this.can_add = can_add;
        this.can_edit = can_edit;
    }

    public Setting getRole() {
        return role;
    }

    public void setRole(Setting role) {
        this.role = role;
    }

    public Setting getScreen() {
        return screen;
    }

    public void setScreen(Setting screen) {
        this.screen = screen;
    }

    public boolean isGet_all_data() {
        return get_all_data;
    }

    public void setGet_all_data(boolean get_all_data) {
        this.get_all_data = get_all_data;
    }

    public boolean isCan_delete() {
        return can_delete;
    }

    public void setCan_delete(boolean can_delete) {
        this.can_delete = can_delete;
    }

    public boolean isCan_add() {
        return can_add;
    }

    public void setCan_add(boolean can_add) {
        this.can_add = can_add;
    }

    public boolean isCan_edit() {
        return can_edit;
    }

    public void setCan_edit(boolean can_edit) {
        this.can_edit = can_edit;
    }
    
    
}
