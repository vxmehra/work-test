package com.loyaltyone.homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.loyaltyone.homework.bo.WeatherData;

@Service
public class WeatherService {

	private RestTemplate restTemplate;

	public WeatherService(@Autowired RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
	
	public WeatherData getWeatherDetails(String cityName) {
		String uri = "http://api.openweathermap.org/data/2.5/weather?q="+ cityName + "&units=metric&APPID=YOUR_ID";
		ResponseEntity<WeatherData> data = null;
		try {
			data = restTemplate.getForEntity(uri, WeatherData.class);
		} catch(Exception e) {
			WeatherData weather = new WeatherData();
			weather.setCity(cityName);
			return weather;
		}
		return data.getBody();
	}
	
}
