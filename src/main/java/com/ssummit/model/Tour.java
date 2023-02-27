package com.ssummit.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "tours_seq", allocationSize = 1)
public class Tour extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "primary_guide_user_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_PRIMARYGUIDE")
    )
    private User primaryGuide;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Builder
    public Tour(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy,
                Boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String title, String description,
                User primaryGuide, User secondaryGuide, Set<User> participants, Route route, Date startDate, Date endDate,
                TourApplication tourApplication, TourEquipment tourEquipment, Set<CheckpointMark> checkpointMarks) {
        super(id, createdBy, createdDateTime, updatedDateTime, updatedBy, isDeleted, deletedDateTime, deletedBy);
        this.title = title;
        this.description = description;
        this.primaryGuide = primaryGuide;
        this.secondaryGuide = secondaryGuide;
        this.participants = participants;
        this.route = route;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourApplication = tourApplication;
        this.tourEquipment = tourEquipment;
        this.checkpointMarks = checkpointMarks;
    }
}
