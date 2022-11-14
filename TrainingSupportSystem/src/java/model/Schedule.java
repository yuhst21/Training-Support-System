/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 *
 *
 * @author mavjp
 */
public class Schedule {

    private int schedule_id;
    private Class classs;
    private Room room;
    private TimeSlot slot;
    private Date training_date;
    private String from_time;
    private String to_time;
    private boolean status;
    private boolean is_attend;
    private String topic;
    private String note;
    

    public Schedule() {
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Class getClasss() {
        return classs;
    }

    public void setClasss(Class classs) {
        this.classs = classs;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public Date getTraining_date() {
        return training_date;
    }

    public void setTraining_date(Date training_date) {
        this.training_date = training_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Schedule(int schedule_id, Class classs, Room room, TimeSlot slot, Date training_date, String from_time, String to_time, boolean status, boolean is_attend) {
        this.schedule_id = schedule_id;
        this.classs = classs;
        this.room = room;
        this.slot = slot;
        this.training_date = training_date;
        this.from_time = from_time;
        this.to_time = to_time;
        this.status = status;
        this.is_attend = is_attend;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }
    
    

    public boolean isIs_attend() {
        return is_attend;
    }

    public void setIs_attend(boolean is_attend) {
        this.is_attend = is_attend;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(training_date);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        return sdf.format(training_date);
    }

}
