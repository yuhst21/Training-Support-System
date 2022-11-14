/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author win
 */
public class WebContact {

   private int contact_id;
    private Category category;
    private User supporter;
    private String name;
    private String email;
    private String mobile;
    private String message;
    private String respones;
    private boolean status;
    private Date dateSend;
    private Date dateResponse;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public Date getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse(Date dateResponse) {
        this.dateResponse = dateResponse;
    }

    public WebContact() {
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSupporter() {
        return supporter;
    }

    public void setSupporter(User supporter) {
        this.supporter = supporter;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRespones() {
        return respones;
    }

    public void setRespones(String respones) {
        this.respones = respones;
    }

    @Override
    public String toString() {
        return contact_id + " " + category.getCategory_name() + " "  + " " + name + " "
                + email + " " + mobile + " " + message + " " + respones;
    }


}
