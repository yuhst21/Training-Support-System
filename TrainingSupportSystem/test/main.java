
import dal.ScheduleDAO;
import dal.WebContactDAO;
import model.WebContact;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class main {
    public static void main(String[] args) {
//        WebContactDAO wdb = new WebContactDAO();
//        WebContact w = new WebContact();
//        w.setContact_id(1);
//        w = wdb.get(w);
//        System.out.println(w.getName());
        String input = "01212424";
        if(input.matches("^[0-9]+$")) System.out.println("true");
        else System.out.println("fale");
        
    }
}
