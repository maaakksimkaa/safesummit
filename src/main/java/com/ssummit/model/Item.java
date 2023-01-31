package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "item_type_id",
            foreignKey = @ForeignKey(name = "FK_ITEM_ITEMTYPE")
    )
    private ItemType itemType;

    @Column(name = "manufacture_date")
    private Date manufactureDate;

    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @Column(name = "is_verifiable")
    private boolean isVerifiable;

    @Column(name = "verification_interval")
    private Date verificationInterval;

    @Column(name = "latest_verification_date")
    private Date latestVerificationDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "verification_certificate")
    private String verificationCertificate;

    @Column(name = "available")
    private boolean available;
}
