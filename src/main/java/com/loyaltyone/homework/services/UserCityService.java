package com.loyaltyone.homework.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loyaltyone.homework.bo.UserMessages;
import com.loyaltyone.homework.bo.WeatherData;
import com.loyaltyone.homework.entities.User;
import com.loyaltyone.homework.exception.MyResourceNotFoundException;
import com.loyaltyone.homework.exception.ServerException;

@Service
public class UserCityService {

	private UserService userService;
	private WeatherService weatherService;

	@Autowired
	public UserCityService(UserService userService, WeatherService weatherService) {
		super();
		this.userService = userService;
		this.weatherService = weatherService;
	}

	public UserMessages getUserCity(@NotEmpty String userName, @NotEmpty String city) {
		final UserMessages userMsg = new UserMessages();

		CompletableFuture<User> databaseStuff = CompletableFuture.supplyAsync(() -> {
			return userService.getUserByName(userName);
		});

		CompletableFuture<WeatherData> apiStuff = CompletableFuture.supplyAsync(() -> {
			return weatherService.getWeatherDetails(city);
		});

		CompletableFuture<UserMessages> combinedFuture = databaseStuff.thenCombine(apiStuff, (user, weather) -> {
			userMsg.setUser(user);
			userMsg.setWeatherData(weather);
			return userMsg;
		}).handle((res, err) -> {
			if (err != null)
				throw new RuntimeException(err);
			return res;
		});

		UserMessages user;
		try {
			user = combinedFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			if (ExceptionUtils.indexOfType(e, MyResourceNotFoundException.class) != -1) {
				throw new MyResourceNotFoundException(ExceptionUtils.getRootCause(e));
			} else
				throw new ServerException(e);
		}
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

}
