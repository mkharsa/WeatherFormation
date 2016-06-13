package mkharsa.com.weatherformation;

import java.util.Date;

/**
 * Created by mkharsa on 11/06/16.
 */
public class WeatherInformation {
    static String  currentCity;
    static String currentCountry;
    static int currentCode;
    static int currentTemp;
    static Date currentDate;

    public static Date getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(Date currentDate) {
        WeatherInformation.currentDate = currentDate;
    }

    public static String getCurrentCountry() {
        return currentCountry;
    }

    public static void setCurrentCountry(String currentCountry) {
        WeatherInformation.currentCountry = currentCountry;
    }

    public static int getCurrentCode() {
        return currentCode;
    }

    public static void setCurrentCode(int currentCode) {
        WeatherInformation.currentCode = currentCode;
    }

    public static int getCurrentTemp() {
        return currentTemp;
    }

    public static void setCurrentTemp(int currentTemp) {
        WeatherInformation.currentTemp = currentTemp;
    }

    public static String getCurrentCity() {
        return currentCity;
    }

    public static void setCurrentCity(String currentCity) {
        WeatherInformation.currentCity = currentCity;
    }


}
