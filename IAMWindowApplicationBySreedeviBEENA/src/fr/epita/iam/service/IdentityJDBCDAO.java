package fr.epita.iam.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityCreationException;
import fr.epita.iam.exceptions.IdentityDeletionException;
import fr.epita.iam.exceptions.IdentityUpdateException;
import fr.epita.logger.Logger;

/**
 * Class IdentityJDBCDAO is implements the interface IdentityDAO. 
 * This class contains all the functions required to perform JDBC operations such as 
 * Create, update , delete and search. 
 * @author Sreedevi BEENA
 *
 */
public class IdentityJDBCDAO implements IdentityDAO {

	/**
	 * Logger object created to record the details happening in this class
	 */
	private static final Logger LOGGER = new Logger(IdentityJDBCDAO.class);

	/* (non-Javadoc)
	 * 
	 * @see fr.epita.iam.service.IdentityDAO#create(fr.epita.iam.datamodel.Identity)
	 */
	public void create(Identity id) throws IdentityCreationException {
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO IDENTITIES(UID, DISPLAY_NAME, EMAIL) values (?,?,?) ");
			preparedStatement.setString(1, id.getUid());
			preparedStatement.setString(2, id.getDisplayName());
			preparedStatement.setString(3, id.getEmail());
			preparedStatement.execute();
			LOGGER.info("Identity created : " + id.getUid());
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("error in create method :" + e.getMessage());
			final IdentityCreationException businessException = new IdentityCreationException(id, e);
			// Handling the exception
			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close(); // To close the connection
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.epita.iam.service.IdentityDAO#search(fr.epita.iam.datamodel.Identity)
	 */
	public List<Identity> search(Identity criteria) {
		final List<Identity> identities = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement preparedStatement;
			if(criteria == null) {
				preparedStatement = connection.prepareStatement("select UID, DISPLAY_NAME, EMAIL FROM IDENTITIES ");
			}
			else {
				preparedStatement = connection
						.prepareStatement("select UID, DISPLAY_NAME, EMAIL FROM IDENTITIES WHERE UID = ? OR DISPLAY_NAME = ? OR EMAIL = ? ");
				preparedStatement.setString(1, criteria.getUid());
				preparedStatement.setString(2, criteria.getDisplayName());
				preparedStatement.setString(3, criteria.getEmail());
			}
			// Writing out the search results
			final ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				final Identity identity = new Identity();
				identity.setDisplayName(resultSet.getString(2));
				identity.setEmail(resultSet.getString(3));
				identity.setUid(resultSet.getString(1));
				identities.add(identity);
			}
			return identities;
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("error while searching", e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				LOGGER.error("unresolved error", e);
			}
		}

		return identities;
	}

	
	/**
	 * Retrieves details for the derby connection from the Configuration.properties file
	 * using the Configuration properties in order to set values for url, username and password
	 * 
	 * @return Returns connection object with all the user details to create a database connection.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		final Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("db.host"),
				Configuration.getInstance().getProperty("username"), Configuration.getInstance().getProperty("password"));
		return connection;
	}

	/* (non-Javadoc)
	 * @see fr.epita.iam.service.IdentityDAO#update(fr.epita.iam.datamodel.Identity)
	 */
	public void update(Identity identity) throws IdentityUpdateException {
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE IDENTITIES set DISPLAY_NAME = ? , EMAIL = ?   where UID = ? ");
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
			preparedStatement.execute();
			LOGGER.info("Identity updated (UID is) : " + identity.getUid());
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("error in update method :" + e.getMessage());
			final IdentityUpdateException businessException = new IdentityUpdateException(identity, e);
			// Handling the exception
			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close(); // To close the connection
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	
	/* (non-Javadoc)
	 * @see fr.epita.iam.service.IdentityDAO#delete(fr.epita.iam.datamodel.Identity)
	 */
	public void delete(Identity identity) throws IdentityDeletionException {
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE from IDENTITIES where UID = ? ");
			preparedStatement.setString(1, identity.getUid());
			preparedStatement.execute();
			LOGGER.info("Identity deleted and the id is : " + identity.getUid());
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("error in delete method :" + e.getMessage());
			final IdentityDeletionException businessException = new IdentityDeletionException(identity, e);
			// Handling the exception
			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close(); // To close the connection
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
