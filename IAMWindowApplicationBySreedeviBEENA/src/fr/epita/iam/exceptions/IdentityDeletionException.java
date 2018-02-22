package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;

/**
 * An exception class is created which handles the exception occurring during the deletion of the identities.
 * @author Sreedevi BEENA
 *
 */
public class IdentityDeletionException extends Exception {
	Identity faultyIdentity;

	/**
	 * Constructor
	 * @param identity An identity class object  will be receieved
	 * @param originalCause The cause of exception
	 */
	public IdentityDeletionException(Identity identity, Exception originalCause) {
		faultyIdentity = identity;
		initCause(originalCause);

	}

	/* (non-Javadoc)
	 * A message will be displayed explaining the problem, if there exists one.
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "problem occured while deleting that identity from the system " + faultyIdentity.toString();
	}
}
