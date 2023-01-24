package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "checkpoints")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Checkpoint extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToMany(mappedBy = "checkpointList", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Route> routes = new HashSet<>();
}
