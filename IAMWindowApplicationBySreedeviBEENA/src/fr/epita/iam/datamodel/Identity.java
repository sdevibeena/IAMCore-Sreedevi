package fr.epita.iam.datamodel;


/**
 * This class creates the identity class object which holds all the information regarding a single person 
 * @author Sreedevi BEENA
 *
 */
public class Identity {
	
	/**
	 * String value for Display Name
	 */
	private String displayName;
	/**
	 * String value for email
	 */
	private String email;
	/**
	 * String value for uid
	 */
	private String uid;

	public Identity() {
	}

	
	/**
	 * Defining the constructor for the Identity class object
	 * @param displayName  Name of the identity 
	 * @param email  Email id of the identity
	 * @param uid  UID of the identity. This is a unique string for each identity created
	 */
	public Identity(String displayName, String email, String uid) {
		this.displayName = displayName;
		this.email = email;
		this.uid = uid;
	}

	 
	/**
	 * Returns the displayName
	 * @return Returns the Name of the identity
	 */
	public String getDisplayName() {
		return displayName;
	}
	  
	/**
	 * Sets displayName
	 * @param displayName Sets the Name for the identity object
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Returns the email 
	 * @return Returns the email id 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email
	 * @param email Sets the email id for the identity
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Returns the Uid
	 * @return Returns the UID for the identity
	 */
	public String getUid() {
		return uid;
	}
	
	/**
	 * Sets the Uid
	 * @param uid Sets the UID for the 
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/* (non-Javadoc)
	 * To display the identity object
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Identity [displayName=" + displayName + ", email=" + email + ", uid=" + uid + "]";
	}

	
	/* (non-Javadoc)
	 * Hash code generation
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (displayName == null ? 0 : displayName.hashCode());
		result = prime * result + (email == null ? 0 : email.hashCode());
		result = prime * result + (uid == null ? 0 : uid.hashCode());
		return result;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Identity other = (Identity) obj;
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (uid == null) {
			if (other.uid != null) {
				return false;
			}
		} else if (!uid.equals(other.uid)) {
			return false;
		}
		return true;
	}




}
