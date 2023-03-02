package com.ssummit.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckpointMarkDto extends GenericDto{

    private Long id;
    private Long checkpointId;
    private LocalDateTime scheduledMarkedTime;
    private LocalDateTime actualMarkedTime;
    private CheckpointDto checkpoint;
    private boolean messageSend;
}

//TODO удалить класс?