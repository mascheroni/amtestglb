package com.almundo.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	private static final String PATH = "../src/main/resources/com/almundo/automation/data/";
	private FileInputStream inputStream;
	
	public String getPropertiesValues(String testID, String property) {
		try {
			Properties properties = new Properties();
			String propLocation = PATH + property;
			System.out.println(System.getProperty("user.dir"));
			inputStream = new FileInputStream(propLocation);
			
			if(inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException(propLocation + " was not found in the classpath");
			}
			
			return properties.getProperty(testID);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
