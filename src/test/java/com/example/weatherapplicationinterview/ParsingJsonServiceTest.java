package com.example.weatherapplicationinterview;

import com.example.weatherapplicationinterview.service.ParsingJsonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ParsingJsonServiceTest {

    @Mock
    private ParsingJsonService parsingJsonService;

    @Test
    public void getDataFromApiTest()
    {
        String URL = "https://api.openweathermap.org/data/2.5/forecast/daily?q=Prilep&units=metric&cnt=16&appid=c550bd8a655d225b73e3ba2024ca88e0";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }
}
