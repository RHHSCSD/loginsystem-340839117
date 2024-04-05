/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.*;

/**
 *
 * @author Jaden Wickens
 */
public class LoginSystem {

    private static final String DELIMITER = ",";
    private static final File f = new File("UserInfo.txt");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         register("new", "Wickens", "gresgsfd", "grsegfdsg", "myEmail");
         register("new", "Wickens", "hello", "bonjour", "myEmail");
         register("new", "Wickens", "im not really sure", "i think so",
         "myEmail"); register("new", "Wickens", "gfsdgfsdgre", "bfgwdfsgf",
         "myEmail"); register("new", "Wickens", "thisIsMyUsername",
         "myPassword", "myEmail"); register("new", "Wickens", "rgewgesdgf",
         "myPassyturhword", "myEmail"); register("new", "Wickens",
         "gsdrggers", "hrtdgfhdg", "myEmail"); register("new", "Wickens",
         "gserfgers", "sergsfdg", "myEmail");
         
          * if (login("myPassword","thisIsMyUsername")){
          System.out.println("success"); }else{ System.out.println("bad"); }
         
         
         * if (isUnique("thisIsMyUsername")) { System.out.println("is unique");
         * } else { System.out.println("not unique"); } if
         * (isStrongPassword("Ykppfw4m")) { System.out.println("is strong"); }
         * else { System.out.println("not strong"); }
         
        
        System.out.println(register("new", "Wickens", "34083dfgbgbsdfdfgf91177fdsafdsafdasfdaf8696789","Ykppfw4m", "myEmail"));
        
        if (isStrongPassword("Ykppfw4m")){
            System.out.println("it is strong");
        }
        if(isBanned("Ykppfw4m")){
            System.out.println("it is banned");
        }
        if(isDelimiterFree("Ykppfw4m")){
            System.out.println("is d free");
        }
        if (!isDelimiterFree("Ykppfw4m")){
            System.out.println("not d free");
        }
        //if (isBanned("abacination")){
      //      System.out.println("banned");
      //  }else{
      //      System.out.println("not banned");
     //   }
      */
        
    }

    public static int register(String firstName, String lastName, String username, String password, String email) {
        if (!isUnique(username)) {
            return 1;
        } else if (!isStrongPassword(password)|| isBanned(password) || !isDelimiterFree(password)) {
            return 2;
        } else {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(f, true));
                pw.println(firstName + DELIMITER + lastName + DELIMITER + username + DELIMITER + encrypt(password) + DELIMITER + email);
                pw.close();
                System.out.println("Registration successful.");
                return 3;
            } catch (IOException e) {
                System.err.println("Error occured wrighting to the file");
                return 4;
            }
        }
    }

    public static boolean login(String password, String username) {
        Scanner s = null;
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] userInfo = line.split(DELIMITER);
                if (userInfo[2].equals(username) && userInfo[3].equals(encrypt(password))) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading File");
            return false;
        }
        if (s != null) {
            s.close();
        }
        return false;
    }

    public static String encrypt(String password) {
        try {
//java helper class to perform encryption 
            MessageDigest md = MessageDigest.getInstance("SHA-256"); //give the helper function the password 
            md.update(password.getBytes());
//perform the encryption 
            byte byteData[] = md.digest();
//To express the byte data as a hexadecimal number (the normal way) 
            String encryptedPassword = "";
            for (int i = 0; i < byteData.length; ++i) {
                encryptedPassword += (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error encrypting code");
            return "ERROR";
        }
    }

    public static boolean isUnique(String username) {
        Scanner s = null;
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] userInfo = line.split(DELIMITER);
                if (userInfo[2].equals(username)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading File");
            return false;
        }
        if (s != null) {
            s.close();
        }
        return true;
    }

    public static boolean isStrongPassword(String password) {
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                uppercase = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                lowercase = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                number = true;
            }
        }
        return (password.length() >= 8 && uppercase && lowercase && number);
    }

    public static boolean isBanned(String password) {
        try {
            File bannedPass = new File("dictbadpass.txt");
            Scanner s = new Scanner(bannedPass);
            while (s.hasNext()) {
                if (s.nextLine().equals(password)) {
                    return true;
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println("File not found");
            return true;
        } //} public void loaduser(){
        return false;
    }

    public static boolean isDelimiterFree(String password) {
        for(int i=0; i<password.length();i++){
            if (password.contains(DELIMITER)){
                return false;
            }
        }
        return true;
    }

}
