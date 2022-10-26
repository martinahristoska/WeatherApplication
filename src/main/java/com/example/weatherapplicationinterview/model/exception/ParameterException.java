package com.example.weatherapplicationinterview.model.exception;

public class ParameterException  extends RuntimeException {

    public ParameterException()
    {
        super("Parameter is not valid");
    }
}
