package com.example.weatherapplicationinterview.service.implementation;

import com.example.weatherapplicationinterview.model.WeatherClass;
import com.example.weatherapplicationinterview.model.exception.ParameterException;
import com.example.weatherapplicationinterview.repository.WeatherRepository;
import com.example.weatherapplicationinterview.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImplementation implements WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherServiceImplementation(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public WeatherClass save(String city, String date, Double minTemperature,
                             Double maxTemperature, String description, String main) {
        WeatherClass weatherData = new WeatherClass(city, date, minTemperature, maxTemperature, description, main);
        if (city == null || date == null || minTemperature == null || maxTemperature == null || description == null || main == null) {
            throw new ParameterException();
         }
        else {
            return weatherRepository.save(weatherData);
        }
    }

    @Override
    public List<WeatherClass> tempMoreThen20() {
        List<WeatherClass> tempMoreThen20 = new ArrayList<>();
        for (WeatherClass data: weatherRepository.findAll())
        {
            if (data.getMaxTemperature()>20)
            {
                tempMoreThen20.add(data);
            }
        }
        return tempMoreThen20;
    }

    @Override
    public List<WeatherClass> rainyDays(String main) {
        return weatherRepository.getAllByMain(main);
    }

    @Override
    public List<WeatherClass> getDataForEveryCity(String city) {
        return weatherRepository.getAllByCity(city);
    }

    @Override
    public Boolean checkIfExistInDatabase(String city, String date) {
        return weatherRepository.findByCityAndDate(city, date) == null;
    }
}
