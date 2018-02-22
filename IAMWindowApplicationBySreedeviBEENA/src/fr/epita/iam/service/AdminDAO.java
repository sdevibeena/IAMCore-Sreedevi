package fr.epita.iam.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.epita.iam.datamodel.User;
import fr.epita.logger.Logger;

/**
 * For accessing login details of admin-user from the ADMIN table in embedded derby
 * database (UserTable)
 * @author Sreedevi BEENA
 *
 */
public class AdminDAO {
	/**
	 * Logger object created to record the details happening in this class
	 */
	private static final Logger LOGGER = new Logger(AdminDAO.class);
	
	/**
	 * This function authenticates the user by taking information from the embedded derby database.
	 * Searches for the user in the embedded database. If a record exist, returns TRUE else returns FALSE .
	 * This database contains a table named ADMIN which stores the username, password and the privilege. 
	 * information of a user.
	 * @param userLocal This is the user class object which contains the username and the password
	 * @return Returns True if user exists
	 */
	public boolean authenticate(User userLocal) {
		Connection connection = null;
		try {
				connection = adminConnection();
				LOGGER.info("Connection Established");
				final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from ADMIN WHERE USER_NAME = ? AND PASSWORD = ? ");
				preparedStatement.setString(1, userLocal.getUsername());
				preparedStatement.setString(2, userLocal.getPassword());
				final ResultSet adminResultset = preparedStatement.executeQuery();
				if(adminResultset.next()) {
					LOGGER.info("Authentication Successful ");
					return true;
				}
				else {
					LOGGER.info("Authentication failed ");
					return false;
				}
		}catch (ClassNotFoundException | SQLException e) {
		LOGGER.error("error in authentication :" + e.getMessage());
	} finally {
		try {
			if (connection != null) {
				connection.close(); 				// To close the connection
			}
		} catch (final SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		return false;
	}
	/**
	 * Function to connect the embedded derby database. Takes in login details from the properties file which can be 
	 * found at /IAMWindowApplicationBySreedeviBEENA/src/fr/epita/iam/service.
	 * 
	 * @return Returns the connection object with all the retrieved values
	 * @throws ClassNotFoundException Throws class not found exception	
	 * @throws SQLException  Throws SQL exception 
	 */
	public Connection adminConnection() throws ClassNotFoundException, SQLException {
		/*
		 * org.apache.derby.jdbc.EmbeddedDriver used to work with the embedded derby database
		 */
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		final Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("adminDb.host"),
				Configuration.getInstance().getProperty("userDb"), Configuration.getInstance().getProperty("passDb"));
		return connection;
	}
	

}
