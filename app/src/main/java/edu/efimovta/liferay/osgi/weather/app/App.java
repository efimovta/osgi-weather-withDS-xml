package edu.efimovta.liferay.osgi.weather.app;


import edu.efimovta.liferay.osgi.weather.dto.Weather;
import edu.efimovta.liferay.osgi.weather.printer.service.WeatherPrinter;
import edu.efimovta.liferay.osgi.weather.service.WeatherGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eta on 7/5/2017.
 */
public class App {

    private volatile List<WeatherGetter> weatherGetters = new ArrayList<>();
    private volatile WeatherPrinter weatherPrinter;

    protected void bindWeatherGetter(WeatherGetter weatherGetter) {
        System.out.println("bindWeatherGetter: " + weatherGetter.getClass());
        this.weatherGetters.add(weatherGetter);
    }

    protected void unbindWeatherGetter(WeatherGetter weatherGetter) {
        System.out.println("unbindWeatherGetter: " + weatherGetter.getClass());
        this.weatherGetters.remove(weatherGetter);
    }

    protected void bindWeatherPrinter(WeatherPrinter weatherPrinter) {
        System.out.println("bindWeatherPrinter: " + weatherPrinter.getClass());
        this.weatherPrinter = weatherPrinter;
    }

    protected void unbindWeatherPrinter(WeatherPrinter weatherPrinter) {
        System.out.println("unbindWeatherPrinter: " + weatherPrinter.getClass());
        this.weatherGetters = null;
    }

    public void start() throws Exception {
        System.out.println("ActivatorApp start(); 1");


        List<Weather> weathers = new ArrayList<>();
        for (WeatherGetter weatherGetter : weatherGetters) {
            Weather weather = weatherGetter.get();
            weathers.add(weather);
        }
        weatherPrinter.print(weathers);

        System.out.println("ActivatorApp start(); 2");
    }

}
