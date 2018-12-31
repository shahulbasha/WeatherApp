package org.weather.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.weather.controller.MainController;
import org.weather.model.WeatherModel;
import org.weather.util.WeatherUtilManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class WeatherService extends Service<WeatherModel>{

	private String cityName;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	protected Task<WeatherModel> createTask() {
		 		    	
		return new Task<WeatherModel>() {

			@Override
			protected WeatherModel call() throws Exception {
				return new WeatherProvider().get(cityName);
			}
			
/*			@Override
			public void succeeded() {
				WeatherModel weatherModel = getValue();
				//set the model on GUI
				MainController controller=new MainController();
				
			
			}
			
			public void failed() {
				//set Error model on GUI
				
				
			}*/
		};
	}

}
