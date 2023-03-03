package com.ssummit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "settings")
@Component
@Data
public class TourSafetySettings {

    public static Double CRITICAL_WINDSPEED;
    public static String OPENWEATHER_API_KEY;
    public static int MAXIMUM_PARTICIPANTS_PER_ONE_GUIDE;
}
