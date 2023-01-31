package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tour_equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourEquipment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "tour_equipment_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_EQUIPMENT_ITEMS")
    )
    private Set<Item> itemList;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;
}
