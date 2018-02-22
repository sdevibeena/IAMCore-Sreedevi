package fr.epita.iam.datamodel;

/* User class definition
 * with the properties: username 
 * & password, for authentication
 * purpose 
 */


/**
 * This class creates a user for authentication purpose
 * @author Sreedevi BEENA
 *
 */
public class User {
	/**
	 * String value for username
	 */
	private String username;
	/**
	 * String value for password
	 */
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Constructor function to define the parameters of user
	 * @param username username of the user
	 * @param password  password of the user
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/** 
	 * @return Returns the Username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username Sets Username  
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return Returns the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password Sets the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

}
