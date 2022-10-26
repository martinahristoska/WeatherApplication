package com.example.weatherapplicationinterview.controller;

import com.example.weatherapplicationinterview.model.WeatherClass;
import com.example.weatherapplicationinterview.service.ParsingJsonService;
import com.example.weatherapplicationinterview.service.WeatherService;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class WeatherController {

    private final ParsingJsonService parsingJsonService;
    private final WeatherService weatherService;

    public WeatherController(ParsingJsonService parsingJsonService, WeatherService weatherService) {
        this.parsingJsonService = parsingJsonService;
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getHomePage(Model model) throws ParseException {
        parsingJsonService.getDataFromApi();
        model.addAttribute("bodyContent", "homepage");
        return "master-template";
    }
    @GetMapping(value = "/moreThen20Degrees")
    public String getWarmDaysDataPage(Model model) {
        List<WeatherClass> daysWithTempMoreThen20 = this.weatherService.tempMoreThen20();
        model.addAttribute("moreThen20Degrees", daysWithTempMoreThen20);
        model.addAttribute("bodyContent", "moreThen20Degrees");
        return "master-template";
    }
    @GetMapping(value = "/rainyDays")
    public String getRainyDaysDataPage(Model model) {
        List<WeatherClass> rainyDays = this.weatherService.rainyDays("Rain");
        model.addAttribute("rainyDays", rainyDays);
        model.addAttribute("bodyContent", "rainyDays");
        return "master-template";
    }
    @GetMapping(value = "/bitola")
    public String getDataBitola(Model model) {
        List<WeatherClass> dataBitola = this.weatherService.getDataForEveryCity("Bitola");
        model.addAttribute("dataBitola", dataBitola);
        model.addAttribute("bodyContent", "bitola");
        return "master-template";
    }
    @GetMapping(value = "/prilep")
    public String getDataPrilep(Model model) {
        List<WeatherClass> dataPrilep = this.weatherService.getDataForEveryCity("Prilep");
        model.addAttribute("dataPrilep", dataPrilep);
        model.addAttribute("bodyContent", "prilep");
        return "master-template";
    }
    @GetMapping(value = "/skopje")
    public String getDataSkopje(Model model) {
        List<WeatherClass> dataSkopje = this.weatherService.getDataForEveryCity("Skopje");
        model.addAttribute("dataSkopje", dataSkopje);
        model.addAttribute("bodyContent", "skopje");
        return "master-template";
    }
}
