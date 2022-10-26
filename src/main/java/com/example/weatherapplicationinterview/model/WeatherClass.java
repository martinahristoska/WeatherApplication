package com.example.weatherapplicationinterview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "WeatherData")
@NoArgsConstructor
public class WeatherClass {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String city;
        private String date;
        private Double minTemperature;
        private Double maxTemperature;
        private String description;
        @Column(name = "weather")
        private String main;

        public WeatherClass(String city, String date, Double minTemperature,
                            Double maxTemperature, String description, String main) {
                this.city = city;
                this.date = date;
                this.minTemperature = minTemperature;
                this.maxTemperature = maxTemperature;
                this.description = description;
                this.main = main;
        }
}

