package com.example.weatherapplicationinterview.service.implementation;

import com.example.weatherapplicationinterview.service.ParsingJsonService;
import com.example.weatherapplicationinterview.service.WeatherService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ParsingJsonServiceImplementation implements ParsingJsonService {
    private final List<String> cities = Arrays.asList("Prilep", "Skopje", "Bitola");
    private final static String APIKEY = "c550bd8a655d225b73e3ba2024ca88e0";
    private final WeatherService weatherService;

    public ParsingJsonServiceImplementation(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void getDataFromApi() throws ParseException {
        for (String city: cities)
        {
            String URL = "https://api.openweathermap.org/data/2.5/forecast/daily?q=" + city + "&units=metric&cnt=16&appid=" + APIKEY;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
            if (response.getStatusCodeValue() == HttpStatus.OK.value())
            {
                String body = response.getBody();
                saveAndParseData(body);
            }
        }
    }
    public void saveAndParseData(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        JSONObject cityObject = (JSONObject) json.get("city");
        String city = cityObject.getAsString("name");
        ArrayList<JSONObject> listObjects = (ArrayList<JSONObject>) json.get("list");
        for (JSONObject element: listObjects)
        {
            Integer longDate = (Integer) element.get("dt");
            Date date = new Date((long) longDate * 1000);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringDate = dateFormat.format(date);
            JSONObject tempObject = (JSONObject) element.get("temp");
            Double minTemp = (Double) tempObject.get("min");
            Double maxTemp = (Double) tempObject.get("max");
            ArrayList<JSONObject> weatherObjects = (ArrayList<JSONObject>) element.get("weather");
            String description = (String) weatherObjects.get(0).get("description");
            String main = (String) weatherObjects.get(0).get("main");
            if (weatherService.checkIfExistInDatabase(city, stringDate))
            {
                weatherService.save(city, stringDate, minTemp, maxTemp, description, main);
            }
        }
    }
}
