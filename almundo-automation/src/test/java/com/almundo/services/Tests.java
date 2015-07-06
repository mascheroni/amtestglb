package com.almundo.services;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.IOException;
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
		
	 	
		@Test(groups = { "include-test-one" })
	    public void testMethodOne() {
			
	        System.out.println("Test method one");
	        try {
				Assert.assertTrue(this.verificarServicio());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	    public boolean verificarServicio() throws MalformedURLException, IOException{
	        String request = "";
	        request = "https://apist.almundo.com/api/flights/itineraries?from=BUE,RIO&to=ZZZ,BUE&departure=2015-09-10,2015-09-17&site=ARG&language=ES";
	        
	        
	        
	        URL url = new URL(request);
	        URLConnection connection = null;
	        if (this.proxy) {
		       Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.http, Integer.parseInt(this.port)));
		       connection = (HttpURLConnection) url.openConnection(proxy);
		    } else {
		       connection = (HttpURLConnection) url.openConnection();
		    }
	        
	        connection.addRequestProperty("X-ApiKey", "5592f8fd99325b40cba48649");
	        connection.addRequestProperty("X-UOW", "glb-estefanie");
	        
//	        if (false) { //Autentication BASIC
//	            new Base64();
//				String basicAuth = "Basic " + new String(Base64.encode(url.getUserInfo().getBytes()));  
//	            connection.setRequestProperty("Authorization", basicAuth);
//	        }
	        
	        //Logging request
	        System.out.println("Servicio: ");
	        System.out.println("Peticion: " + request);
	        System.out.println("");
	        
	        Scanner scanner = null;
	        
	        try {
	        	scanner = new Scanner(connection.getInputStream());
	        } catch (IOException ioe) {
	        	System.out.println("API Key no existente o erronea");
	        	System.out.println("");
	        	return false;
	        }
	        String inputLine = "";
	        while (scanner.hasNext()){
	            inputLine = inputLine + scanner.nextLine() + "\n";
	        }
	            
	        //Validación de Status Code
	        int iniValue = inputLine.indexOf("\"code\":");
	        String a = inputLine.substring(iniValue + 7, iniValue + 10);
	        int code = Integer.parseInt(a);
	        
	        
	        
	        switch (code) {
	            case 200:
	                System.out.println(code + ": Servicio Estable");
	                return true;

	            case 501:
	                System.out.println(code + ": Parámetros incorrectos");
	                return false;

	            case 502:
	                System.out.println(code + ": No se pudieron obtener las ciudades");
	                return false;
	            default:
	            	System.out.println(code + ": Error no especificado");
	    	        return false;
	        }
	             
	    }
		
	}
