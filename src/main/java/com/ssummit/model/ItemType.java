package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_types")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "requiredItemTypes", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Route> routes = new HashSet<>();
}
