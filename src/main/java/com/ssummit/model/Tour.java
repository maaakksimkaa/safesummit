package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tour extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "primary_guide_user_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_PRIMARYGUIDE")
    )
    private User primaryGuide;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "secondary_guide_user_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_SECONDARYGUIDE")
    )
    private User secondaryGuide;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tour_participants",
            joinColumns = @JoinColumn(name = "tour_id"),
            foreignKey =  @ForeignKey(name = "FK_TOURS_USERS"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            inverseForeignKey = @ForeignKey(name = "FK_USERS_TOURS")
    )
    private Set<User> participants = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "route_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_ROUTE")
    )
    private Route route;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "tour_application_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_TOURAPPLICATION")
    )
    private TourApplication tourApplication;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "tour_equipment_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_TOUR_EQUIPMENT")
    )
    private TourEquipment tourEquipment;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tour_checkpoint_marks",
            foreignKey = @ForeignKey(name = "FK_TOURS_CHECKPOINT_MARKS")
    )
    private Set<CheckpointMark> checkpointMarks = new HashSet<>();
}
