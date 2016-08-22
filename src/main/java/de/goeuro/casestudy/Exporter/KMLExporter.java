package de.goeuro.casestudy.Exporter;

import java.util.List;

import de.goeuro.casestudy.model.City;

/**
 * KML - keyhole markup language
 * 
 * Class used to export the City data with coordinate positions
 * After exporting to KML, the city details can be visualized in Google Earth
 * 
 * @author Pradeep Anbalagan
 *
 */
public class KMLExporter extends AbstractExporter {

	public KMLExporter(String filePath, List<City> cityDetails) {
		super(filePath, cityDetails);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exportData() {
		// TODO Auto-generated method stub
		
	}

}
