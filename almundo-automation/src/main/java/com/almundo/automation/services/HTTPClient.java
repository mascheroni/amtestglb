package com.almundo.automation.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HTTPClient {
	
	private final String ITINERARI_SERVICE = 
			"https://apist.almundo.com/api/flights/itineraries?";
	
	private URL url;
	private URLConnection urlConnection;
	private String request;
	private boolean proxy = false;
	private String http = "proxy.corp.globant.com";
	private String port = "3128";
	private Scanner scanner;
	
	public void addHeaderValue(String key, String value) {
		this.urlConnection.addRequestProperty(key, value);
	}
	
	public void setRequest() {
		//TODO Codigo para setear los parametros a la request
		
		//EL codigo siguiente es a manera de ejemplo
		this.request = this.ITINERARI_SERVICE + 
				"from=BUE,RIO&to=RIO,BUE&departure=2015-09-10,2015-09-17&site=ARG&language=ES";
	}
	
	public void openConection() {
		try {
			this.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void connect() throws MalformedURLException, IOException{
		this.url = new URL(this.request);
        if (this.proxy) {
	       Proxy proxy = new Proxy(Proxy.Type.HTTP, 
	    		   new InetSocketAddress(this.http, Integer.parseInt(this.port)));
	       this.urlConnection = (HttpURLConnection) url.openConnection(proxy);
	    } else {
	    	this.urlConnection = (HttpURLConnection) url.openConnection();
	    }
	}
	
	public Response post() {
		StringBuilder plainResponse = new StringBuilder();
		try {
			plainResponse.append(this.postAction());
		} catch (IOException ioe) {
        	System.out.println("Invalid or not present API-Key \n");
        	ioe.printStackTrace();
        }
		return new Response(plainResponse.toString());
		
	}
	
	private String postAction() throws IOException{
		InputStream is = this.urlConnection.getInputStream();
		scanner = new Scanner(is);
		String inputLine = "";
        while (scanner.hasNext()){
            inputLine = inputLine + scanner.nextLine() + "\n";
        }
        return inputLine;
	}
	

}
