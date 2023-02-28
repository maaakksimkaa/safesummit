package com.ssummit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddCheckpointMarkDto {

    //Long checkpointMarkId;
    Long checkpointId;
    Long tourId;
    LocalDateTime scheduledMarkedTime;
}
