package com.loyaltyone.homework.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherData {
	
	@JsonProperty("coord")
	private CityCoordinates cityCoord = new CityCoordinates();
	@JsonProperty("main")
	private CityTemperature cityTemp = new CityTemperature();
	
	@JsonProperty("name")
	private String city;
	
	public CityCoordinates getCityCoord() {
		return cityCoord;
	}
	public void setCityCoord(CityCoordinates cityCoord) {
		this.cityCoord = cityCoord;
	}
	public CityTemperature getCityTemp() {
		return cityTemp;
	}
	public void setCityTemp(CityTemperature cityTemp) {
		this.cityTemp = cityTemp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
