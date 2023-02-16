package com.ssummit.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Builder
    public Item(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy,
                boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String title, String description,
                String brand, ItemType itemType, Date manufactureDate, Long inventoryNumber, boolean isVerifiable,
                Date verificationInterval, Date latestVerificationDate, Date expirationDate, String verificationCertificate,
                boolean available) {
        super(id, createdBy, createdDateTime, updatedDateTime, updatedBy, isDeleted, deletedDateTime, deletedBy);
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.itemType = itemType;
        this.manufactureDate = manufactureDate;
        this.inventoryNumber = inventoryNumber;
        this.isVerifiable = isVerifiable;
        this.verificationInterval = verificationInterval;
        this.latestVerificationDate = latestVerificationDate;
        this.expirationDate = expirationDate;
        this.verificationCertificate = verificationCertificate;
        this.available = available;
    }
}
