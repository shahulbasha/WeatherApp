package org.weather.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.weather.model.WeatherModel;
import org.weather.util.WeatherUtilManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class WeatherProvider implements WeatherSupplier {

	public WeatherModel get(String cityName) {
		
		String text = cityName;
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
			
			Instant ofEpochSecond = Instant.ofEpochSecond(longValue/1000);
			Date date=new Date(longValue);
			System.out.println(date.getTime());
			//System.out.println();
			//System.out.println(ofEpochSecond.);
			//LocalDate date =Instant.ofEpochMilli(longValue).atZone(ZoneId.Of).toLocalDate();
			LocalDateTime localTime = LocalDateTime.ofInstant( Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
			
			model.setLocalTime(localTime);
			ArrayNode array=(ArrayNode)readTree.get("weather");
			for (JsonNode jsonNode : array) {
				model.setCurrentWeatherDescription(jsonNode.get("description").textValue());
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(model); 
		return model;
	}

}
