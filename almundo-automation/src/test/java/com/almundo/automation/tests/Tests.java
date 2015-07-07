package com.almundo.automation.tests;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {
	
		private boolean proxy = false;
		private String http = "proxy.corp.globant.com";
		private String port = "3128";
		private String glbUserName = "glb-amascheroni";
		private Scanner scanner;
		
	 	
		@Test(groups = { "include-test-one" })
	    public void testMethodOne() {
			
	        System.out.println("Test method one");
	        try {
				Assert.assertTrue(this.verificarServicio("BUE", "RIO", "2015-09-10", "2015-09-17", true));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	    public boolean verificarServicio(String origen, String destino, String fechaIda, 
	    		String fechaVuelta, boolean roundTrip) throws MalformedURLException, IOException{
	        
	    	String request = "";
	    	String service = "https://apist.almundo.com/api/flights/itineraries?";
	        request = service + "from=" + origen + "," + destino + "&to=" + destino + "," + origen +
	        		"&departure=" + fechaIda + "," + fechaVuelta + "&site=ARG&language=ES";
	        
	        
	        URL url = new URL(request);
	        URLConnection connection = null;
	        if (this.proxy) {
		       Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.http, Integer.parseInt(this.port)));
		       connection = (HttpURLConnection) url.openConnection(proxy);
		    } else {
		       connection = (HttpURLConnection) url.openConnection();
		    }
	        
	        connection.addRequestProperty("X-ApiKey", "5592f8fd99325b40cba48649");
	        connection.addRequestProperty("X-UOW", this.glbUserName);
	        
	        System.out.println("Ping to apist.almundo.com");
	        System.out.println("Request: " + request);
	        System.out.println("");
	        
	        InputStream is = null;
	        
	        try {
	        	is = connection.getInputStream();
	        } catch (IOException ioe) {
	        	System.out.println("API Key no existente o erronea");
	        	System.out.println("");
	        	return false;
	        }
	        
	        scanner = new Scanner(is);
	        
	        String inputLine = "";
	        
	        while (scanner.hasNext()){
	            inputLine = inputLine + scanner.nextLine() + "\n";
	        }
	        
	        System.out.println("Response: ");
	        System.out.println(inputLine);
	        System.out.println("");
	        
	        return true;
	             
	    }
		
	}
