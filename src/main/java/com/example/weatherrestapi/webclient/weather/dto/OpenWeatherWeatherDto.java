package com.example.weatherrestapi.webclient.weather.dto;

import lombok.Getter;

@Getter
public class OpenWeatherWeatherDto {

    private OpenWeatherMainDto main;
    private OpenWeatherWindDto wind;
}
