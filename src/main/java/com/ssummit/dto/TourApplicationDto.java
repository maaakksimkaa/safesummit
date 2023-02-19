package com.ssummit.dto;

import lombok.*;

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
    private Date applicationDate;
    private String incomingPostNumber;
    private Date applicationRegistrationDate;
}
