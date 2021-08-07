package com.example.weatherrestapi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDto {

    private double temperature;
    private int pressure;
    private int humidity;
    private double windSpeed;
}
