package org.weather.controller;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.json.JsonObject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.weather.model.WeatherModel;
import org.weather.service.WeatherService;
import org.weather.util.WeatherUtilManager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
	
    @FXML
    private JFXTextField cityName;

    @FXML
    private JFXButton searchButton;

    @FXML
    void fetchCurrentWeather(ActionEvent event) {
    	
    	WeatherService service=new WeatherService();
    	service.setCityName(cityName.getText());
    	
    	service.restart();
    	//one way to prevent the Application thread from making the weather API call using Executor Service
    	//disadvantage is the absence of succeeded and fail method
    	/*ExecutorService executorService=Executors.newSingleThreadExecutor();
    	executorService.execute(()->{
    		
    	});
    	executorService.submit(()->{
        	String text = cityName.getText();
        	Client client=ClientBuilder.newClient();
       // 	WebTarget target = client.target("https://api.openweathermap.org/data/2.5/weather"+"?q="+text+"&appid=f21c9e7762bc3ddf46f9d6a0fa68595a");
        	WebTarget target = client.target(WeatherUtilManager.getPropertiesData("endpointURL"));
        	WebTarget queryParam=target.queryParam("q", text).queryParam("units",WeatherUtilManager.getPropertiesData("temperatureUnit")).queryParam("appid", WeatherUtilManager.getPropertiesData("apiKey"));
        	
        	System.out.println(queryParam.getUri());
        	JsonObject jsonObject = queryParam.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        	System.out.println(jsonObject.toString());
        	ObjectMapper mapper=new ObjectMapper();
        	WeatherModel model=new WeatherModel();
        	System.out.println("*********************");
        	try {
    			JsonNode readTree = mapper.readTree(jsonObject.toString());
    			
    			System.out.println("Read tree"+readTree);
    			
    			model.setCityName(readTree.get("name").textValue());
    			model.setLatitude(readTree.get("coord").get("lat").doubleValue());
    			model.setLongitude(readTree.get("coord").get("lon").doubleValue());
    			model.setVisibility(readTree.get("visibility").intValue());
    			model.setClouds(readTree.get("clouds").get("all").intValue());			
    			model.setTemperature(readTree.get("main").get("temp").doubleValue());
    			model.setPressure(readTree.get("main").get("pressure").doubleValue());
    			model.setHumidity(readTree.get("main").get("humidity").doubleValue());
    			model.setMinimumTemperature(readTree.get("main").get("temp_min").doubleValue());
    			model.setMaximumTemperature(readTree.get("main").get("temp_max").doubleValue());
    			model.setWindSpeed(readTree.get("wind").get("speed").doubleValue());
    			
    			long longValue = readTree.get("dt").longValue()*1000;

    			LocalDate date =Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDate();
    			LocalDateTime localTime = LocalDateTime.ofInstant( Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
    			
    			System.out.println(date.toString());
    			System.out.println(localTime.toString());
    			ArrayNode array=(ArrayNode)readTree.get("weather");
    			for (JsonNode jsonNode : array) {
    				model.setCurrentWeatherDescription(jsonNode.get("description").textValue());
    			}
    			
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	System.out.println(model); 
        	executorService.shutdown();
        	try {
        	    if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        	        executorService.shutdownNow();
        	    } 
        	} catch (InterruptedException e) {
        	    executorService.shutdownNow();
        	}
    		
    	});*/
    	
    	//second way to separate the application thread from making API calls is using Service class provided by JavaFX framework
/*    	Task<String> task=new Task<String>() {

			@Override
			protected String call() throws Exception {
				String text = cityName.getText();
	        	Client client=ClientBuilder.newClient();
	       // 	WebTarget target = client.target("https://api.openweathermap.org/data/2.5/weather"+"?q="+text+"&appid=f21c9e7762bc3ddf46f9d6a0fa68595a");
	        	WebTarget target = client.target(WeatherUtilManager.getPropertiesData("endpointURL"));
	        	WebTarget queryParam=target.queryParam("q", text).queryParam("units",WeatherUtilManager.getPropertiesData("temperatureUnit")).queryParam("appid", WeatherUtilManager.getPropertiesData("apiKey"));
	        	
	        	System.out.println(queryParam.getUri());
	        	JsonObject jsonObject = queryParam.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
	        	System.out.println(jsonObject.toString());
	        	ObjectMapper mapper=new ObjectMapper();
	        	WeatherModel model=new WeatherModel();
	        	System.out.println("*********************");
	        	try {
	    			JsonNode readTree = mapper.readTree(jsonObject.toString());
	    			
	    			System.out.println("Read tree"+readTree);
	    			
	    			model.setCityName(readTree.get("name").textValue());
	    			model.setLatitude(readTree.get("coord").get("lat").doubleValue());
	    			model.setLongitude(readTree.get("coord").get("lon").doubleValue());
	    			model.setVisibility(readTree.get("visibility").intValue());
	    			model.setClouds(readTree.get("clouds").get("all").intValue());			
	    			model.setTemperature(readTree.get("main").get("temp").doubleValue());
	    			model.setPressure(readTree.get("main").get("pressure").doubleValue());
	    			model.setHumidity(readTree.get("main").get("humidity").doubleValue());
	    			model.setMinimumTemperature(readTree.get("main").get("temp_min").doubleValue());
	    			model.setMaximumTemperature(readTree.get("main").get("temp_max").doubleValue());
	    			model.setWindSpeed(readTree.get("wind").get("speed").doubleValue());
	    			
	    			long longValue = readTree.get("dt").longValue()*1000;

	    			LocalDate date =Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDate();
	    			LocalDateTime localTime = LocalDateTime.ofInstant( Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    			
	    			System.out.println(date.toString());
	    			System.out.println(localTime.toString());
	    			ArrayNode array=(ArrayNode)readTree.get("weather");
	    			for (JsonNode jsonNode : array) {
	    				model.setCurrentWeatherDescription(jsonNode.get("description").textValue());
	    			}
	    			
	    			
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        	System.out.println(model);   		    	
				return null;
			}
    		
		};
    	
		new Thread(task).start();*/
    	
    	
}
    
}
