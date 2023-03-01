package com.ssummit.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTourApplicationDto {

    String title;
    String description;
    String outcomingPostNumber;
    LocalDate applicationDate;
    String incomingPostNumber;
    LocalDate applicationRegistrationDate;
}
