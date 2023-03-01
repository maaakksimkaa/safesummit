package com.ssummit.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class AddTourPlanDto {

    Map<String, LocalDateTime> tourPlan;
}
