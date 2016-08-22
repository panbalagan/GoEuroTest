package de.goeuro.casestudy.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.goeuro.casestudy.model.City;

/**
 * Utility class for the handling CSV file For reading and writing in different
 * formats
 *
 * @author Pradeep Anbalagan
 *
 */
public class CSVUtil {
	private static final char SEPERATOR = ';';
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtil.class);

	public static void writeLine(Writer w, List<City> cityDetails, StringBuilder builder) {

		/* Writes the header line to CSV */
		try {
			// Iterate through the list and append to string builder to write to
			// CSV
			for (City cityDetail : cityDetails) {
				builder.append(cityDetail.getId()).append(SEPERATOR).append(cityDetail.getName()).append(SEPERATOR)
						.append(cityDetail.getType()).append(SEPERATOR).append(cityDetail.getLongitude())
						.append(SEPERATOR).append(cityDetail.getLatitude()).append(SEPERATOR);
				builder.append("\n");
			}

			w.append(builder.toString());
			w.flush();
			w.close();
		} catch (IOException ex) {
			LOGGER.error("Error occured while writing to CSV file , %s ", ex.getMessage());
		}
	}
}
