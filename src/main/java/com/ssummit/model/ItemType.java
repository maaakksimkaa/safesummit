package com.ssummit.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_types")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemType extends GenericModel{

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "requiredItemTypes", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Route> routes = new HashSet<>();

//    public ItemType(ItemTypeDto itemTypeDto) {
//        this.id = itemTypeDto.getId();
//        this.title = itemTypeDto.getTitle();
//        this.description = itemTypeDto.getDescription();
//    }
}
