/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Team_member {
    private Team team;
    private User user;
    private boolean is_leader;
    private boolean is_active;

    public Team_member() {
    }

    public Team_member(Team team, User user, boolean is_leader, boolean is_active) {
        this.team = team;
        this.user = user;
        this.is_leader = is_leader;
        this.is_active = is_active;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isIs_leader() {
        return is_leader;
    }

    public void setIs_leader(boolean is_leader) {
        this.is_leader = is_leader;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
}
