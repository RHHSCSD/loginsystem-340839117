/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author Jaden Wickens
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    
    /**
     * A constructor to create a new instance of User
     * @param firstName the fist name of the user
     * @param lastName the last name of the user
     * @param username the users inputted id
     * @param password the users inputted password
     * @param email
     */
    public User(String firstName, String lastName, String username, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    /**
     * A constructor to create a new instance of User form a string
     * @param userInput a string of information to set the fields too
     */
    public User(String[] userInput){
        this.firstName = userInput[0];
        this.lastName = userInput[1];
        this.email = userInput[2];
        this.username = userInput[3];
        this.password = userInput[4];
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
}
