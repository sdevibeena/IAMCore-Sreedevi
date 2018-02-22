package fr.epita.iam.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import fr.epita.logger.Logger;

/*
 * Definition of the Configuration class
 * which is helpful in storing the 
 * property key values in a configuration 
 * file.
 */
/**
 * Definition of the Configuration class which helps retrieving the  
 * property key values from a configuration file which can be found at
 * /IAMWindowApplicationBySreedeviBEENA/src/fr/epita/iam/service

 * @author Sreedevi BEENA
 *
 */
public class Configuration {
	
	/**
	 * Creating an object of Logger class and passing the Configuration attributes 
	 * to Logger class object in order to generate log details.
	 */
	private static final Logger logger = new Logger(Configuration.class);
	/**
	 * object of Configuration class
	 */
	private static Configuration instance;
	/**
	 * object of Properties class which stores the prperty values
	 */
	private final Properties properties;

	/**
	 * Creates a new object of Configuration class
	 * @return the Configuration object with the property values
	 */
	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration(); //Assigns if the value is null
		}
		return instance;
	}
	
	 
	/**
	 * Definition of constructor for Configuration class.
	 * Creates a new object of properties class and loads the key-values in to the object from the 
	 * configuration file ("Configuration.properties") found at /IAMWindowApplicationBySreedeviBEENA/src/fr/epita/iam/service
	 */
	private Configuration() {
		properties = new Properties();
		try {
			InputStream input = getClass().getResourceAsStream("Configuration.properties");
			properties.load(input);

		} catch (final IOException e) {
			logger.error("error while loading the configuration", e); //Exception Handling
		}
	}
	
	
	/**
	 * This function returns the value for the key passed as the argument. 
	 * The values are taken form the properties class object generated using getInstance()
	 * @param key The property for which the values has to be retrieved (e.g. userDB, passDB, db.Host, ..)
	 * @return Returns the value of the passed key
	 */
	public String getProperty(String key) {

		return properties.getProperty(key);

	}
	

	/**
	 * Returns the key value contained after testing
	 * if the specified object is a key in the hashtable
	 * @param key key The property for which the values has to be retrieved (e.g. userDB, passDB, db.Host, ..)
	 * @return Returns TRUE if a key value pair exists else returns FALSE.
	 */
	public boolean containsProperty(String key) {
		return properties.containsKey(key);
	}


}
