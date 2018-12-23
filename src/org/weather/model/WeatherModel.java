package org.weather.model;

public class WeatherModel {

	private String latitude;
	private String longitude;
	private String cityName;

	private String temperature;
	private String currentWeather;
	private String currentWeatherDescription;
	private String pressure;
	private String humidity;
	private String minimumTemperature;
	private String maximumTemperature;
	private String visibility;
	private String clouds;
	private String windSpeed;
	
	@Override
	public String toString() {
		return "WeatherModel [latitude=" + latitude + ", longitude=" + longitude + ", cityName=" + cityName
				+ ", temperature=" + temperature + ", currentWeather=" + currentWeather + ", currentWeatherDescription="
				+ currentWeatherDescription + ", pressure=" + pressure + ", humidity=" + humidity
				+ ", minimumTemperature=" + minimumTemperature + ", maximumTemperature=" + maximumTemperature
				+ ", visibility=" + visibility + ", clouds=" + clouds + ", windSpeed=" + windSpeed + "]";
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getCurrentWeather() {
		return currentWeather;
	}
	public void setCurrentWeather(String currentWeather) {
		this.currentWeather = currentWeather;
	}
	public String getCurrentWeatherDescription() {
		return currentWeatherDescription;
	}
	public void setCurrentWeatherDescription(String currentWeatherDescription) {
		this.currentWeatherDescription = currentWeatherDescription;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getMinimumTemperature() {
		return minimumTemperature;
	}
	public void setMinimumTemperature(String minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}
	public String getMaximumTemperature() {
		return maximumTemperature;
	}
	public void setMaximumTemperature(String maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getClouds() {
		return clouds;
	}
	public void setClouds(String clouds) {
		this.clouds = clouds;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	
}
