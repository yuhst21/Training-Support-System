/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ACER
 */
public class Week {
    private LocalDate startdate;
    private LocalDate endate;

    public Week() {
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEndate() {
        return endate;
    }

    public void setEndate(LocalDate endate) {
        this.endate = endate;
    }
    
    
    
}
