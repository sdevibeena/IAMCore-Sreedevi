package fr.epita.logger;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defining  a Logger class which records the log details of this Identity Management System
 * in a log file named "application.log"
 * @author Sreedevi BEENA
 *
 */
public class Logger {

	/**
	 * Creates a printWriter object which writes the log details into the application.log
	 */
	private static PrintWriter pw;
	/**
	 * Creates a FileWriter object
	 */
	private static FileWriter fw = null;
	/**
	 * Creates a BufferedWriter object for appending the log files even after closing and opening the application.
	 */
	private static BufferedWriter bw = null;

	static {
		try {
			fw = new FileWriter("application.log", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
		} catch (final Exception e) {
			System.out.println("error while initializing the log file");
		}
	}
	/**
	 * Class object created
	 */
	private final Class cls;

	/**
	 * Constructor defined
	 * @param loggingClass Receives the class whose log has to be generated and assigns it to the Class object cls
	 */
	public Logger(Class loggingClass) {
		cls = loggingClass;
	}

	/**
	 * Receives the string message to be printed to the log file.
	 * @param message Info message to be written as the info level in the log file.
	 */
	public void info(String message) {
		printMessage(message, "INFO");
	}

	/**
	 * Receives the string message to be printed to the log file.
	 * @param message Error message to be written as an error level in the log file
	 */
	public void error(String message) {
		printMessage(message, "ERROR");
	}

	/**
	 * Receives the string message as well as the exception to be printed to the log file.
	 * @param message Error message to be written as an ERROR level in the log file
	 * @param e Exception message to be written as an ERROR level in the log file
	 */
	public void error(String message, Exception e) {
		printMessage(message, "ERROR");
		e.printStackTrace(pw);
		pw.flush();
	}

	/**
	 * Receives the string message to be printed to the log file.
	 * @param message Debug message to be written as an DEBUG level in the log file
	 */
	public void debug(String message) {
		printMessage(message, "DEBUG");
	}


	/**
	 * This function write the log details in the log file in the format 
	 * Date and Time - level - message
	 * Date and time are in the timestamp format
	 * @param message String message to be written to the log file
	 * @param level Determines the log level
	 */
	private void printMessage(String message, String level) {
		final Date date = new Date();
		final String timestamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss.SSS").format(date);
		pw.println(timestamp + " - " + level + " - " + message);
		pw.flush();
	}



}
