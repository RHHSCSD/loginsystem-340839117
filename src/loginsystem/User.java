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
    private final String password;
    private String email;
    private String salt;

    /**
     * A constructor to create a new instance of User
     *
     * @param firstName the fist name of the user
     * @param lastName the last name of the user
     * @param username the users inputted username
     * @param password the users inputted password
     * @param email the users inputted email
     */
    public User(String firstName, String lastName, String username, String password, String email, String salt) {
        //Set all relevant fields based on user input
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
    }

    /**
     * A constructor to create a new instance of User form a string
     *
     * @param userInput a string of information to set the fields too
     */
    public User(String[] userInput) {
        //Set all relevant fields based on user input
        this.firstName = userInput[0];
        this.lastName = userInput[1];
        this.email = userInput[2];
        this.username = userInput[3];
        this.password = userInput[4];
        this.salt = userInput[5];
    }

    /**
     * Return the users first name
     *
     * @return the user's stored first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Return the users last name
     *
     * @return the user's stored last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Return the users username
     *
     * @return the user's stored username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the users email
     *
     * @return the user's stored email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Change the users set first name
     *
     * @param firstName the user's new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Change the users set last name
     *
     * @param lastName the user's new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Change the users set username
     *
     * @param username the user's new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Change the users set email
     *
     * @param email the user's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
