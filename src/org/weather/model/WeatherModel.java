package org.weather.model;

public class WeatherModel {

	private double latitude;
	private double longitude;
	private String cityName;

	private double temperature;
	private String currentWeather;
	private String currentWeatherDescription;
	private double pressure;
	private double humidity;
	private double minimumTemperature;
	private double maximumTemperature;
	private int visibility;
	private int clouds;
	private double windSpeed;
	
	@Override
	public String toString() {
		return "WeatherModel [latitude=" + latitude + ", longitude=" + longitude + ", cityName=" + cityName
				+ ", temperature=" + temperature + ", currentWeather=" + currentWeather + ", currentWeatherDescription="
				+ currentWeatherDescription + ", pressure=" + pressure + ", humidity=" + humidity
				+ ", minimumTemperature=" + minimumTemperature + ", maximumTemperature=" + maximumTemperature
				+ ", visibility=" + visibility + ", clouds=" + clouds + ", windSpeed=" + windSpeed + "]";
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
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
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getMinimumTemperature() {
		return minimumTemperature;
	}
	public void setMinimumTemperature(double minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}
	public double getMaximumTemperature() {
		return maximumTemperature;
	}
	public void setMaximumTemperature(double maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public int getClouds() {
		return clouds;
	}
	public void setClouds(int clouds) {
		this.clouds = clouds;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	
}
