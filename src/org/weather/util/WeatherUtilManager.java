package org.weather.util;

import java.io.IOException;
import java.util.Properties;

public class WeatherUtilManager {
	
	static WeatherUtilManager manager;
	static Properties properties;
	
	private WeatherUtilManager() {

	}

	public void loadProperties() {
		if(properties==null) {
			properties=new Properties();
			try {
				properties.load(getClass().getResourceAsStream("/org/weather/resources/application.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static WeatherUtilManager getInstance() {
		return manager==null?new WeatherUtilManager():manager;
	}
	
	public static String getPropertiesData(String key) {
			return properties.getProperty(key);
	}
	
	
	
	

}
