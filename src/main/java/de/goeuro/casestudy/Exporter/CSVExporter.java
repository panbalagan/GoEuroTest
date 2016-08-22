package de.goeuro.casestudy.Exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.goeuro.casestudy.model.City;
import de.goeuro.casestudy.util.CSVUtil;

/**
 * 
 * Class used to export the city data into CSV format
 * 
 * @author Pradeep Anbalagan
 *
 */
public class CSVExporter extends AbstractExporter {

	private static final String FILENAME = "cityDetails.csv";
	private static final char SEPERATOR = ';';
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVExporter.class);

	public CSVExporter(String filePath, List<City> cityDetails) {
		super(filePath, cityDetails);
	}

	@Override
	public void exportData() {
		/* Construct the path for writing the data */
		String filePath = getFilePath() + FILENAME;
		// Iterate through the city details list

		try {
			FileWriter csvWriter = new FileWriter(new File(filePath));
			CSVUtil.writeLine(csvWriter, getCityDetails(), constructHeaderLine());
			LOGGER.info("City Details is exported to CSV successfully");
		} catch (IOException e) {
			LOGGER.error("Export to CSV is failed. %s", e.getMessage());
		}
	}

	/**
	 * Helper method to construct the header line for writing to CSV file
	 * 
	 * @return string builder
	 */
	private StringBuilder constructHeaderLine() {
		StringBuilder builder = new StringBuilder();
		builder.append("ID").append(SEPERATOR).append("NAME").append(SEPERATOR).append("TYPE").append(SEPERATOR)
				.append("LONGITUDE").append(SEPERATOR).append("LATITUDE").append(SEPERATOR).append("\n");
		return builder;
	}

}
