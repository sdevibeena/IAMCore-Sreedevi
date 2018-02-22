package fr.epita.iam.service;

import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityCreationException;
import fr.epita.iam.exceptions.IdentityDeletionException;
import fr.epita.iam.exceptions.IdentityUpdateException;

/**
 * Defining the interface for the class IdentityJDBCDAO which provides all the four services.
 * @author Sreedevi BEENA
 *
 */
public interface IdentityDAO {

	/**
	 * Creates an entry in to the database:
	 * 
	 * This function creates a connection to the database, inserts the passed Identy object in to the database. 
	 * Finally logs the details using the Logger object and closes the connection. 
	 * @param identity Identity object with the all the setvalues passed
	 * @throws IdentityCreationException
	 */
	public void create(Identity identity) throws IdentityCreationException;

	/**
	 * Updates an entry in the database:
	 * 
	 * This function receives an Identity object which contains the new values for an already existing entry,
	 * creates a connection, updates the database with the supplied new values from the Identity object.
	 * Finally logs the details using the Logger object and closes the connection. 
	 * @param identity Identity object with the all the newly setvalues passed
	 * @throws IdentityUpdateException
	 */
	public void update(Identity identity) throws IdentityUpdateException;
	
	/**
	 * Deletes an entry from the database based on the UID
	 * 
	 * This function receives an Identity object which contains the UID parameter, 
	 * creates a connection with the database and deletes the entry from the database.
	 * Finally logs the details using the Logger object and closes the connection. 
	 * @param identity Identity object with the UID values passed
	 * @throws IdentityDeletionException
	 */
	public void delete(Identity identity) throws IdentityDeletionException;

	/**
	 * Searches for the entry from the database.
	 * 
	 * This function receives an Identity object creates a connection, checks whether the object is NULL. 
	 * If NULL retrieves the resultSet with all the entries from the database else retrieves the values(rows) 
	 * as per the provided parameters in the Identity object. The resultSet is iterated and converted to a 
	 * list of Identity objects (LIST of Identity)
	 * Declaration of the function search
	 * @param criteria Identity object with the all the setvalues passed
	 * @return Returns the (LIST of Identity) generated from the resultSet
	 */
	public List<Identity> search(Identity criteria);

}
