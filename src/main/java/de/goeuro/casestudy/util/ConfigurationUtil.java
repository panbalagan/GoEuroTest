package de.goeuro.casestudy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper Class used to read the config properties
 * 
 * @author Pradeep Anbalagan
 *
 */
public class ConfigurationUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationUtil.class);

	private static Properties prop;

	/**
	 * Method to load the constant values from the properties file
	 */
	public static Properties loadProperties() {
		setProp(new Properties());
		InputStream input = null;

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			input = loader.getResourceAsStream("config.properties");
			// load a properties file
			getProp().load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return getProp();
	}

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		ConfigurationUtil.prop = prop;
	}

}
