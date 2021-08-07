package com.example.weatherrestapi.service;

import com.example.weatherrestapi.model.WeatherDto;
import com.example.weatherrestapi.webclient.weather.WeatherClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherDto getWeather() {
        return weatherClient.getWeatherForCity("warszawa");
    }

    public String getForecast() {
        return weatherClient.getForecastForCity(51.21, 21.01);
    }
}
