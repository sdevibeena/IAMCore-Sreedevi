package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;


/**
 * An exception class is created which handles the exception occurring during the creation of the identities.
 * @author Sreedevi BEENA
 *
 */
public class IdentityCreationException extends Exception {
	
	/**
	 * An object of Identity class is created
	 */
	Identity faultyIdentity;

	/**
	 * Constructor
	 * @param identity An identity class object  will be receieved 
	 * @param originalCause The cause of exception
	 */
	public IdentityCreationException(Identity identity, Exception originalCause) {
		faultyIdentity = identity;
		initCause(originalCause);

	}

	/* (non-Javadoc)
	 * A message will be displayed explaining the problem, if there exists one.
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "problem occured while creating that identity in the system " + faultyIdentity.toString();
	}

}
