package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Route extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "category")
    private String category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "route_checkpoints",
            joinColumns = @JoinColumn(name = "route_id"),
            foreignKey =  @ForeignKey(name = "FK_ROUTES_CHECKPOINTS"),
            inverseJoinColumns = @JoinColumn(name = "checkpoint_id"),
            inverseForeignKey = @ForeignKey(name = "FK_CHECKPOINTS_ROUTES")
    )
    private Set<Checkpoint> checkpointList = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "required_item_types",
            joinColumns = @JoinColumn(name = "route_id"),
            foreignKey =  @ForeignKey(name = "FK_ROUTES_ITEMTYPES"),
            inverseJoinColumns = @JoinColumn(name = "itemtype_id"),
            inverseForeignKey = @ForeignKey(name = "FK_ITEMTYPES_ROUTES")
    )
    private Set<ItemType> requiredItemTypes = new HashSet<>();
}
