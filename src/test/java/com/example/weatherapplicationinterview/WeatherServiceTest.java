package com.example.weatherapplicationinterview;

import com.example.weatherapplicationinterview.model.WeatherClass;
import com.example.weatherapplicationinterview.repository.WeatherRepository;
import com.example.weatherapplicationinterview.service.WeatherService;
import com.example.weatherapplicationinterview.service.implementation.WeatherServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    private WeatherService weatherService;

    @Mock
    private WeatherRepository weatherRepository;

    @Before
    public void init() {
        WeatherClass weatherClass = new WeatherClass("Prilep", "2022-10-27", 15.32, 21.69, "sky is clear", "Clear");
        Mockito.when(this.weatherRepository.save(Mockito.any(WeatherClass.class))).thenReturn(weatherClass);

        this.weatherService = Mockito.spy(new WeatherServiceImplementation(this.weatherRepository));
    }
    @Test
    public void saveMethodTest()
    {
        WeatherClass weatherClass = this.weatherService.save("Prilep", "2022-10-27", 15.32, 21.69, "sky is clear", "Clear");

        Mockito.verify(this.weatherService).save("Prilep", "2022-10-27", 15.32, 21.69, "sky is clear", "Clear");
        Assert.assertEquals("Prilep", weatherClass.getCity());
        Assert.assertEquals("2022-10-27", weatherClass.getDate());
    }
    @Test
    public void tempMoreThen20Test()
    {
        this.weatherService.tempMoreThen20();
        Mockito.verify(this.weatherService).tempMoreThen20();
    }

    @Test
    public void rainyDaysTest()
    {
        this.weatherService.rainyDays("Rain");
        Mockito.verify(this.weatherService).rainyDays("Rain");
    }
    @Test
    public void getDataForEveryCityTest()
    {
        this.weatherService.getDataForEveryCity("Prilep");
        Mockito.verify(this.weatherService).getDataForEveryCity("Prilep");
    }
    @Test
    public void checkIfExistInDatabaseTest()
    {
        Boolean condition = this.weatherService.checkIfExistInDatabase("Prilep", "2022-10-27");
        Mockito.verify(this.weatherService).checkIfExistInDatabase("Prilep", "2022-10-27");
        Assert.assertTrue(condition);
    }
}
