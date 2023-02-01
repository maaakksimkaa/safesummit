package com.ssummit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generatior", sequenceName = "routes_seq", allocationSize = 1)
public class Route extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "category")
    private String category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "route_checkpoints",
            foreignKey = @ForeignKey(name = "FK_CHECKPOINTS_ROUTE")
    )
    private Set<Checkpoint> checkpoints = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "required_item_types",
            joinColumns = @JoinColumn(name = "route_id"),
            foreignKey = @ForeignKey(name = "FK_ROUTES_ITEMTYPES"),
            inverseJoinColumns = @JoinColumn(name = "itemtype_id"),
            inverseForeignKey = @ForeignKey(name = "FK_ITEMTYPES_ROUTES")
    )
    private Set<ItemType> requiredItemTypes = new HashSet<>();

    @Builder
    public Route(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy,
                 boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String title, String description,
                 Integer duration, String category, Set<Checkpoint> checkpoints, Set<ItemType> requiredItemTypes) {
        super(id, createdBy, createdDateTime, updatedDateTime, updatedBy, isDeleted, deletedDateTime, deletedBy);
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.checkpoints = checkpoints;
        this.requiredItemTypes = requiredItemTypes;
    }
}
