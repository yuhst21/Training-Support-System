/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Team {
    private int team_id;
    private Class class_id;
    private String team_code;
    private String topic_name;
    private boolean status;
    private String description;
    private ArrayList<Team_member> listTeamMember;
    public Team() {
        listTeamMember = new ArrayList<>();
    }

    public Team(int team_id) {
        this.team_id = team_id;
    }
    

    public ArrayList<Team_member> getListTeamMember() {
        return listTeamMember;
    }

    public void setListTeamMember(ArrayList<Team_member> listTeamMember) {
        this.listTeamMember = listTeamMember;
    }

    public Team(int team_id, Class class_id, String team_code, String topic_name, boolean status, String description) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.team_code = team_code;
        this.topic_name = topic_name;
        this.status = status;
        this.description = description;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public Class getClass_id() {
        return class_id;
    }

    public void setClass_id(Class class_id) {
        this.class_id = class_id;
    }

    public String getTeam_code() {
        return team_code;
    }

    public void setTeam_code(String team_code) {
        this.team_code = team_code;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
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
