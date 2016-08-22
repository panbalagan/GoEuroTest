package de.goeuro.casestudy.model;

/**
 * Model class which is used to store all the values in the city
 * 
 * @author Pradeep Anbalagan
 *
 */
public class City {

	/*Id of the city*/
	private long id;
	
	/*City Name*/
	private String name;
	
	/*Type of the city*/
	private String type;
	
	/*Latitude of the city*/
	private double latitude;
	
	/*longitude of the city*/
	private double longitude;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
