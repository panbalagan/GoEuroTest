package de.goeuro.casestudy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.goeuro.casestudy.Exporter.AbstractExporter;
import de.goeuro.casestudy.Exporter.CSVExporter;
import de.goeuro.casestudy.model.City;
import de.goeuro.casestudy.util.ConfigurationUtil;
import de.goeuro.casestudy.util.RESTAPIQueryResolver;

/**
 * 
 * Test class to test with different method to test the export
 * 
 * @author Pradeep Anbalagan
 *
 */
public class TestExportValidation {
	List<City> cityDetails = new ArrayList<>();
	@Before
	public void setUp() throws Exception {
		ConfigurationUtil.loadProperties();
		City city = new City();
		// Test Data
		city.setId(123456L);
		city.setName("Berlin");
		city.setType("location");
		city.setLongitude(13.12345);
		city.setLatitude(50.12345);
		cityDetails.add(city);
	}
	
	@Test
	@Ignore
	public void testExportCSV (){
		AbstractExporter exporter = new CSVExporter(ConfigurationUtil.getProp().getProperty("path"), cityDetails);
		exporter.exportData();
	}
	
	@Test
	public void testQueryRESTAPI(){
		RESTAPIQueryResolver resolver = new RESTAPIQueryResolver();
		List<City> details = resolver.getCityDetails("Stuttgart");
		AbstractExporter exporter = new CSVExporter(ConfigurationUtil.getProp().getProperty("path"), details);
		exporter.exportData();
	}
		
}
