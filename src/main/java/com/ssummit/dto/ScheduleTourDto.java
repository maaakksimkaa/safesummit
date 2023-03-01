package com.ssummit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleTourDto {

    String title;
    String description;
    Long routeId;
    LocalDateTime startDate;
}
