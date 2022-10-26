package com.example.weatherapplicationinterview.service;

import net.minidev.json.parser.ParseException;

public interface ParsingJsonService {
    void getDataFromApi() throws ParseException;
    void saveAndParseData(String data) throws ParseException;
}
