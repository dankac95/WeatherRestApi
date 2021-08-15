package com.example.weatherrestapi.webclient.weather;

import com.example.weatherrestapi.model.WeatherDto;
import com.example.weatherrestapi.webclient.weather.dto.OpenWeatherWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";

    @Value("${weather.catalog.query.apiKey}")
    private String apiKey; // TODO-purban: Wyodrebnic do pliku application.properties
    // Mozliwe rozwiazania: 1. @Value("${xx.yy}") String apiKey; 2. @ConfigurationProperties

    private final RestTemplate restTemplate;

    public WeatherDto getWeatherForCity(String city) {

        OpenWeatherWeatherDto openWeatherWeatherDto = callGetMethod("weather?q={city}&appid={APIkey}", OpenWeatherWeatherDto.class, city, apiKey);

        return WeatherDto.builder()
                .temperature(openWeatherWeatherDto.getMain().getTemp())
                .pressure(openWeatherWeatherDto.getMain().getPressure())
                .humidity(openWeatherWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherWeatherDto.getWind().getSpeed())
                .build();
    }

    public String getForecastForCity(double lat, double lon) {
        return callGetMethod("onecall?lat={lat}&lon={lon}&exclude=minutely,hourly,daily&appid={API key}&units=metric&lang=pl"
                , String.class
                , lat, lon, apiKey);
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url
                , responseType, objects);
    }
}
