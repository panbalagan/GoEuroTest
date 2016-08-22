package de.goeuro.casestudy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.goeuro.casestudy.model.City;

/**
 * Class which is used to call the hosted REST API Parses the JSON Stream and
 * add the data to the model
 * 
 * @author Pradeep Anbalagan
 *
 */
public class RESTAPIQueryResolver {

	/* Base URL for REST API call */
	private final String BASEAPI = "http://api.goeuro.com/api/v2/position/suggest/en/";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTAPIQueryResolver.class);

	private static final String ID = "_id";
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String GEO = "geo_position";
	private static final String LON = "longitude";
	private static final String LAT = "latitude";

	public RESTAPIQueryResolver() {

	}

	/**
	 * Queries the hosted REST API using the city name
	 * Get the city details for the cityname
	 * 
	 * @param cityName
	 */
	public List<City> getCityDetails(final String cityName) {
		String connectionUrl = BASEAPI + cityName;
		connectionUrl = connectionUrl.replace(" ", "%20");
		List<City> cityDetails = new ArrayList<>();
		try {
			final URL url = new URL(connectionUrl);
			final HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			InputStreamReader stream = new InputStreamReader(urlConnection.getInputStream());
			cityDetails = parseJSONStream(stream, cityName);

		} catch (IOException ex) {
			LOGGER.error(String.format("Error occured while connecting to REST API for city name %s with "
					+ "erro message %s", cityName, ex.getMessage()));
		}
		return cityDetails;
	}

	/**
	 * Parses the JSON stream and returns coordinate positions of hotel
	 * 
	 * @param stream
	 * @param hotelAddress
	 * @return coordinate of hotel
	 */
	private List<City> parseJSONStream(final InputStreamReader stream, final String cityName) {
		StringBuilder builder = null;
		List<City> cityDetails = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(stream)) {
			if (reader != null) {
				builder = new StringBuilder();
				int read;
				while ((read = reader.read()) != -1) {
					builder.append((char) read);
				}
			}
			reader.close();
			if (builder != null) {
				final String content = builder.toString();
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(content);
				Iterator<Object> it = jsonArray.iterator();
				while (it.hasNext()) {
					City cityDetail = new City();
					JSONObject jsonObject = (JSONObject) it.next();
					cityDetail.setId((Long) jsonObject.get(ID));
					cityDetail.setName((String) jsonObject.get(NAME));
					cityDetail.setType((String) jsonObject.get(TYPE));
					JSONObject positions = (JSONObject) jsonObject.get(GEO);
					cityDetail.setLatitude((Double) positions.get(LAT));
					cityDetail.setLongitude((Double) positions.get(LON));
					cityDetails.add(cityDetail);
				}
			}
		} catch (Exception e) {
			LOGGER.error(String.format("Error parsing the JSON stream for the city '%s'"
					+ "with exception %s ", cityName, e.getMessage()));
		}
		return cityDetails;
	}

}
