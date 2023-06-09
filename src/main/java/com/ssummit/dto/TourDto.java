package com.ssummit.dto;

import com.ssummit.model.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDto extends GenericDto {

    private String title;
    private String description;
    private User primaryGuide;
    private User secondaryGuide;
    private Set<Long> participantsIds;
    private Route route;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private TourApplication tourApplication;
    private TourEquipment tourEquipment;
    private Set<Long> checkpointMarksIds;
}
