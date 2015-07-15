package com.almundo.automation.utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders {

	public DataProviders() {
		// TODO Auto-generated constructor stub
	}
	
	@DataProvider(name = "test1")
	public static Object[][] getSearchData() throws IOException {
		//llamada al metodo del XMLReader
		System.out.println("Nothing");
		XmlDataFactory factory = XmlDataFactory.getInstance();
		System.out.println("INtanced");
		factory.initialize("../src/main/resources/com/almundo/automation/smokeTest/");
		System.out.println("Inicialized");
		return factory.getArrayFromDataSet("APISearch");
	}

}
