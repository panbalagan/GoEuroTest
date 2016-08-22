package de.goeuro.casestudy;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.goeuro.casestudy.Exporter.AbstractExporter;
import de.goeuro.casestudy.Exporter.CSVExporter;
import de.goeuro.casestudy.model.City;
import de.goeuro.casestudy.util.ConfigurationUtil;
import de.goeuro.casestudy.util.RESTAPIQueryResolver;

/**
 * 
 * Main Application which is used to launch the application
 * 
 * @author Pradeep Anbalagan
 *
 */
public class MainApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);
	private final String[] args;

	public MainApplication(String[] args) {
		this.args = args;
	}

	private String[] getArgs() {
		return args;
	}

	/**
	 * main method for the argument passing
	 * 
	 * @param args
	 *            arguments from command prompt
	 */
	public static void main(String args[]) {
		LOGGER.info("Application has started to run");
		MainApplication app = new MainApplication(args);
		app.processCommandLineArguments();
	}

	/**
	 * Method to process the command line arguments and validate it
	 */
	private void processCommandLineArguments() {
		final String[] args = getArgs();

		if (args != null && args.length < 1) {
			LOGGER.info("Please check the parameters passed");
			System.exit(-1);
		}

		// Load the properties from config file
		ConfigurationUtil.loadProperties();
		
		/*If the CSV write path is not valid*/
		/*CSV Path is mentioned in the config.properties file in resources*/
		if (!isValidateFilePath()){
			System.exit(-1);
		}
		
		final String argument = args[0].toLowerCase();
		RESTAPIQueryResolver resolver = new RESTAPIQueryResolver();
		List<City> cityDetails = resolver.getCityDetails(argument);
		
		/* Initialize the parameters for exporting the data to CSV Format*/
		AbstractExporter exporter = new CSVExporter(ConfigurationUtil.getProp().getProperty("path"), cityDetails);
		exporter.exportData();
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isValidateFilePath(){
		Properties prop = ConfigurationUtil.getProp();
		String fileDirectory = prop.getProperty("path");
		if (!new File(fileDirectory).isDirectory()) {
			LOGGER.error("The directory is not a valid directory. Please "
					+ "give valid directory path in the config.properties");
			return false;
		}
		return true;
		
	}
}
