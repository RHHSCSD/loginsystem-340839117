/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;

import java.security.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Jaden Wickens
 */
public class LoginSystem {

    private static final String DELIMITER = ",";
    private static final File USER_INFO_FILE = new File("UserInfo.txt");
    private static final File BANNED_PASSWORD_FILE = new File("dictbadpass.txt");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Nothing is found in this meain method
        ArrayList<User> users = loadUser();
        
        
        if (true){
            System.out.println("im not really sure");
        }
    }

    /**
     * This method takes in new users info and checks that they are valid. If it
     * is, a new user is created. If not, the reason for failure is communicated
     * through the return
     *
     * @param firstName The users inputted first name
     * @param lastName The users inputted last name
     * @param username The users inputted username
     * @param password The users inputted password
     * @param email The users inputted email
     * @return an integer based on the result of attempting to register a new
     * user. 1 means the username is already in use. 2 means the password is
     * invalid. 3 means the registration was successful. 4 means there was an
     * error reading from the file of banned passwords.
     */
    public static int register(String firstName, String lastName, String username, String password, String email) {
        // if isUnique does not return true, return one
        if (!isUnique(username)) {
            return 1;
        } // if the password is not strong, banned or contains the delimiter, return two
        else if (!isStrongPassword(password) || isBanned(password) || !isDelimiterFree(password)) {
            return 2;
        } else {
            try {
                //Create an instance of print writer to append to the UserInfo file
                PrintWriter pw = new PrintWriter(new FileWriter(USER_INFO_FILE, true));
                // Write user information to the file with delimiters
                pw.println(firstName + DELIMITER + lastName + DELIMITER + username + DELIMITER + encrypt(password) + DELIMITER + email);
                pw.close();
                System.out.println("Registration successful.");
                //return 3 to indicate successful registration
                return 3;
            } catch (IOException e) {
                System.err.println("Error occured wrighting to the file");
                //return 4 to indicate errors
                return 4;
            }
        }
    }

    /**
     * This method takes in a username and password and checks that they are
     * valid credentials.
     *
     * @param password the users input attempt for a password
     * @param username the users input attempt for a username
     * @return true if username and password are valid, false if not.
     */
    public static boolean login(String password, String username) {
        try {
            //open new scanner from the UserInfo file
            Scanner s = new Scanner(USER_INFO_FILE);
            //while the file has a next line
            while (s.hasNext()) {
                //create a string array containing the elements from that line of the file
                String line = s.nextLine();
                String[] userInfo = line.split(DELIMITER);
                //Check if the inputted username & password matches a username & password from the file
                if (userInfo[2].equals(username) && userInfo[3].equals(encrypt(password))) {
                    //return true if a match is found
                    return true;
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Error Reading File");
            return false;// return false indicating login failure
        }
        return false; // return false if no match is found
    }

    /**
     * This method takes in a password and encrypts it
     *
     * @param password the users inputted password
     * @return the encrypted password
     */
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

    /**
     * This methods returns an array list of users from the User Info text file
     *
     * @return an array list of users
     */
    public static ArrayList<User> loadUser() {
        //Create an empty array list of users
        ArrayList<User> users = new ArrayList<User>();
        
        try {
            Scanner s = new Scanner(USER_INFO_FILE);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] userInfo = line.split(DELIMITER);
                users.add(new User(userInfo));
            }
        } catch (IOException e) {
            System.out.println("Error Reading From File");
        }
        return users;
    }

    /**
     * Checks that the inputted username is not already in use
     *
     * @param username the username to verify
     * @return true if the username is unique, false if not
     */
    public static boolean isUnique(String username) {
        Scanner s = null;
        try {
            s = new Scanner(USER_INFO_FILE);
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

    /**
     * Checks if an inputted password is strong by checking it includes
     * uppercase & lowercase letters, a number and a special character
     *
     * @param password
     * @return true if password is strong, false if not
     */
    public static boolean isStrongPassword(String password) {
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        boolean specialChar = false;
        String specialChars = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼½¾¿×÷";

        for (int i = 0; i < password.length(); i++) {
            char current = password.charAt(i);
            if (Character.isUpperCase(current)) {
                uppercase = true;
            }
            if (Character.isLowerCase(current)) {
                lowercase = true;
            }
            if (Character.isDigit(current)) {
                number = true;
            }
            if (specialChars.indexOf(current) != -1) {
                specialChar = true;
            }
        }
        return (password.length() >= 8 && uppercase && lowercase && number && specialChar);
    }

    /**
     * This method takes in a password and checks it against a list of banned
     * passwords found in a text file.
     *
     * @param password the users inputted passwords
     * @return true if the password is banned, false if not.
     */
    public static boolean isBanned(String password) {
        try {
            Scanner s = new Scanner(BANNED_PASSWORD_FILE);
            while (s.hasNext()) {
                if (s.nextLine().equals(password)) {
                    return true;
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println("File not found");
            return true;
        }
        return false;
    }

    /**
     * This method checks that an inputted password does not include the
     * delimiter used to the users inputted passwords stored info in the
     * UserInfo file.
     *
     * @param password the users inputted passwords
     * @return true if the password is delimiter free, false if not
     */
    public static boolean isDelimiterFree(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.contains(DELIMITER)) {
                return false;
            }
        }
        return true;
    }

}
