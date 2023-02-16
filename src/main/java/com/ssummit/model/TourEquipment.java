package com.ssummit.model;

import javax.persistence.*;
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
@SequenceGenerator(name = "default_generator", sequenceName = "tourequipments_seq", allocationSize = 1)
public class TourEquipment extends GenericModel{

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "tour_equipment_id",
            foreignKey = @ForeignKey(name = "FK_TOUR_EQUIPMENT_ITEMS")
    )
    private Set<Item> itemList;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

//    @Builder
//    public TourEquipment(Long id, Set<Item> itemList, Date dateOfIssue) {
//        this.id = id;
//        this.itemList = itemList;
//        this.dateOfIssue = dateOfIssue;
//    }
}
