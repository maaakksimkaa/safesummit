package com.ssummit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tour_applications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourApplication extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desctiption;

    @Column(name = "outcoming_post_number")
    private String outcomingPostNumber;

    @Column(name = "application_date")
    private Date applicationDate;

    @Column(name = "incoming_post_number")
    private String incomingPostNumber;

    @Column(name = "application_registration_date")
    private Date applicationRegistrationDate;
}
