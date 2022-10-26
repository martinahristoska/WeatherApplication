package com.example.weatherapplicationinterview.service;

import com.example.weatherapplicationinterview.model.WeatherClass;

import java.util.List;

public interface WeatherService {
     WeatherClass save(String city, String date, Double minTemperature, Double maxTemperature, String description, String main);
     List<WeatherClass> tempMoreThen20();
     List<WeatherClass> rainyDays(String main);
     List<WeatherClass> getDataForEveryCity(String city);
     Boolean checkIfExistInDatabase(String city, String date);
}
