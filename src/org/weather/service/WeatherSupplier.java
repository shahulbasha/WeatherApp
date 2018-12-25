package org.weather.service;

import org.weather.model.WeatherModel;

public interface WeatherSupplier {
	
	public WeatherModel get(String cityName) ;

}
