package com.almundo.automation.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	private static final String PATH = "src/main/resources/com/almundo/automation/data/";
	private InputStream inputStream;
	
	public String getSearchPropertiesValues(String testID) 
			throws IOException, FileNotFoundException{
		
		try {
			Properties properties = new Properties();
			String propLocation = PATH + "search.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propLocation);
			
			if(inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException(propLocation + "was not found in the classpath");
			}
			
			return properties.getProperty(testID);
			
		} catch (Exception e) {
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		
		return null;
	}

}
