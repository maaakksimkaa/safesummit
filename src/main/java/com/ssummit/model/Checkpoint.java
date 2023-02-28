package com.ssummit.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "checkpoints")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "checkpoints_seq", allocationSize = 1)
public class Checkpoint extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "route_checkpoints",
            joinColumns = @JoinColumn(name = "checkpoint_id"),
            foreignKey = @ForeignKey(name = "FK_CHECKPOINTS_ROUTES"),
            inverseJoinColumns = @JoinColumn(name = "route_id"),
            inverseForeignKey = @ForeignKey(name = "FK_ROUTES_CHECKPOINTS")
    )
    private Set<Route> routes = new HashSet<>();

    @Builder
    public Checkpoint(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime,
                      String updatedBy, Boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String title,
                      String description, Double latitude, Double longitude) {
        super(id, createdBy, createdDateTime, updatedDateTime, updatedBy, isDeleted, deletedDateTime, deletedBy);
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
