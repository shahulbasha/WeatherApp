package org.weather.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Properties;

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
import org.weather.util.WeatherUtilManager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
	
    @FXML
    private JFXTextField cityName;

    @FXML
    private JFXButton searchButton;

    @FXML
    void fetchCurrentWeather(ActionEvent event) {
    	String text = cityName.getText();
    	Client client=ClientBuilder.newClient();
   // 	WebTarget target = client.target("https://api.openweathermap.org/data/2.5/weather"+"?q="+text+"&appid=f21c9e7762bc3ddf46f9d6a0fa68595a");
    	WebTarget target = client.target(WeatherUtilManager.getPropertiesData("endpointURL"));
    	WebTarget queryParam=target.queryParam("q", text).queryParam("appid", WeatherUtilManager.getPropertiesData("apiKey"));
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
			System.out.println(readTree.get("clouds").get("all").textValue());
			System.out.println(readTree.get("main").get("temp").textValue());
			model.setClouds(readTree.get("clouds").get("all").textValue());
			model.setTemperature(readTree.get("main").get("temp").textValue());
			model.setPressure(readTree.get("main").get("pressure").textValue());
			model.setHumidity(readTree.get("main").get("humidity").textValue());
			model.setMinimumTemperature(readTree.get("main").get("temp_min").textValue());
			model.setMaximumTemperature(readTree.get("main").get("temp_max").textValue());
			System.out.println(readTree.get("visibility").intValue());
			model.setVisibility(readTree.get("visibility").textValue());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(model);   	
}
    
}
