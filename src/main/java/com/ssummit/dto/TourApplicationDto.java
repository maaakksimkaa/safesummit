package com.ssummit.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourApplicationDto extends GenericDto {

    private String title;
    private String desctiption;
    private String outcomingPostNumber;
    private LocalDate applicationDate;
    private String incomingPostNumber;
    private LocalDate applicationRegistrationDate;
}
