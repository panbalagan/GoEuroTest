# GoEuroTest

Java Developer Test
==============================

Project Structure

1. Main Application.Java

    Start up class which contains main method and accepts a parameter (CityName as mandatory)
    
2. Model Package

    Model for storing the city detail
    
3. Exporter Package

    This package contains details about the different exporter format. The software is architected in a form that it can also be used to
    export in different formats
    
4. Util Package

   Helper classes and methods that are used to run this application
   
5. Test Suite

   And finally test suite to test the application

How to Run this Application 
==========================

Make sure to give the proper file path in config.properties file in resources folder. (this path is CSV export path)

java -jar GoEuroTest-0.0.1.jar "CITY_NAME"

The jar is attached in the target folder
