package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;


/**
 * An exception class is created which handles the exception occurring during the updation of the identities.
 * @author Sree
 *
 */
public class IdentityUpdateException extends Exception {
	Identity nonExistingIdentity;

	/**
	 * Constructor definition
	 * @param identity An identity class object  will be receieved
	 * @param originalCause The cause of exception
	 */
	public IdentityUpdateException(Identity identity, Exception originalCause) {
		nonExistingIdentity = identity;
		initCause(originalCause);
	}
	
	/* (non-Javadoc)
	 * A message will be displayed explaining the problem, if there exists one.
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "problem occured while updating that identity in the system " + nonExistingIdentity.toString();
	}


}
