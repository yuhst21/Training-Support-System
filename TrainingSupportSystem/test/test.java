
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.Cipher;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ACER
 */
public class test {

    public static void main(String[] args) {
//        Encoder encoder = Base64.getEncoder();
//        String originalString = "MTIzNDU2";
//        String encodedString = encoder.encodeToString(originalString.getBytes());
//        
//        
//
//        System.out.println(encodedString);
        Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode("MTIzNDU2");

        System.out.println(new String(bytes));
    }

}
