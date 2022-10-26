package com.example.weatherapplicationinterview.repository;

import com.example.weatherapplicationinterview.model.WeatherClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherClass, Long> {
    List<WeatherClass> getAllByMain(String main);
    List<WeatherClass> getAllByCity(String city);
    WeatherClass findByCityAndDate(String city, String date);
}
