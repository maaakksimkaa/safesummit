package com.ssummit.dto;

import com.ssummit.model.Checkpoint;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckpointMarkDto extends GenericDto{

    private Long id;
    private Checkpoint checkpoint;
    private LocalDateTime scheduledMarkedTime;
    private LocalDateTime actualMarkedTime;
}
