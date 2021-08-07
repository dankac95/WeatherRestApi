package com.example.weatherrestapi.webclient.weather;

import com.example.weatherrestapi.model.WeatherDto;
import com.example.weatherrestapi.webclient.weather.dto.OpenWeatherWeatherDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherClient {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "a9c1fbb47c36b1a18dca9718775035bc";
    private RestTemplate restTemplate = new RestTemplate();

    public WeatherDto getWeatherForCity(String city) {

        OpenWeatherWeatherDto openWeatherWeatherDto = callGetMethod("weather?q={city}&appid={apiKey}&units=metric&lang=pl"
                , OpenWeatherWeatherDto.class
                , city, API_KEY);

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
                , lat, lon, API_KEY);


    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url
                , responseType, objects);
    }
}
