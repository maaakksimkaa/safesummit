package com.ssummit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MarkCheckpointDto {

    Long checkpointMarkId;

    LocalDateTime actualMarkedTime;
}
