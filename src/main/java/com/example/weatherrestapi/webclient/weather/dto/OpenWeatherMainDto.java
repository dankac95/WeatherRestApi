package com.example.weatherrestapi.webclient.weather.dto;

import lombok.Getter;

@Getter
public class OpenWeatherMainDto {

    private double temp;
    private int pressure;
    private int humidity;
}
