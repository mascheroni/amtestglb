package com.almundo.automation.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.almundo.automation.services.Parameters;
import com.almundo.automation.utils.EncryptKey;
import com.almundo.automation.utils.PropertyReader;
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
	private String glbUserName = "glb-amascheroni";
	private PropertyReader pr = new PropertyReader();
	
	public void addHeaderValue(String key, String value) {
		this.urlConnection.addRequestProperty(key, value);
	}
	
	public void setSearchRequest(String data){
		String[] values = data.split("\\s+");
		String parameters = "";
		for(String param: values) {
			parameters = parameters + param + "&";
		}
		
		this.request = ITINERARI_SERVICE +
				parameters.substring(0, parameters.length() - 1);
		System.out.println(this.request);
	}
	
	public void setRequest(List<Parameters>parameters) {
		SearchParameters param = new SearchParameters();
		parameters = param.concatenateParametersRoundtrip(parameters);
		StringBuffer sb = new StringBuffer();
		Iterator<Parameters> iterator = parameters.iterator();
		sb.append(ITINERARI_SERVICE);
		while(iterator.hasNext()){
			Parameters paramTemp = iterator.next();
			sb.append(this.concatenarParametro(paramTemp));
			sb.append("&");
		}
		
		this.request = sb.toString();
		System.out.println("Trying with: "+this.request);
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
		
		this.openConection();
		this.addHeaderValue("X-ApiKey", EncryptKey.decryptText(pr.getPropertiesValues("API-Key", "conf/conf.properties")));
		this.addHeaderValue("X-UOW", this.glbUserName);
		StringBuilder plainResponse = new StringBuilder();
		try {
			plainResponse.append(this.postAction());
		} catch (IOException ioe) {
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
	
	private String concatenarParametro(Parameters parametro) {
		return parametro.parametro() + "="+parametro.valor();

	}
	

}
