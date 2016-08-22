package de.goeuro.casestudy.Exporter;

import java.util.List;

import de.goeuro.casestudy.model.City;

/**
 * 
 * Base abstract class for all types of exporter
 * @author Pradeep Anbalagan
 *
 */
public abstract class AbstractExporter {
	
	protected static final String ID = "ID";
	protected static final String NAME = "CityName";
	protected static final String TYPE = "Type";
	protected static final String LON = "Longitude";
	protected static final String LAT = "Latitude";
	
	private final String filePath;
	private List<City> cityDetails;
	
	public AbstractExporter(String filePath,List<City> cityDetails){
		this.filePath = filePath;
		setCityDetails(cityDetails);
	}

	protected List<City> getCityDetails() {
		return cityDetails;
	}

	protected void setCityDetails(List<City> cityDetails) {
		this.cityDetails = cityDetails;
	}
	
	/**
	 * Abstract method which can be implemented in the child class
	 * for different types of exporting format (CSV,Database, XML) 
	 */
	public abstract void exportData();

	/**
	 * Gets the full file path
	 * @return
	 */
	protected String getFilePath() {
		return filePath;
	}
	
}
