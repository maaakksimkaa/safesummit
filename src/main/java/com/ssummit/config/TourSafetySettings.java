package com.ssummit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "safety")
public class TourSafetySettings {

    @Value( "${safety.critical-wind-speed}" )
    public static Double criticalWindSpeed;
    @Value( "${safety.openweather-api-key}" )
    public static String openweatherApiKey;
    @Value( "${safety.maximum-participants-per-one-guide}" )
    public static int maximumParticipantsPerOneGuide;

}
