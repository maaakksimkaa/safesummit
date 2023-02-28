package com.ssummit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.type.Type;
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

    @Column(name = "description", columnDefinition="TEXT")
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
    private LocalDate manufactureDate;

    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @Column(name = "is_verifiable")
    private boolean isVerifiable;


    @Column(name = "verification_interval")
    private int verificationInterval;

    @Column(name = "latest_verification_date")
    private LocalDate latestVerificationDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "verification_certificate")
    private String verificationCertificate;

    @Column(name = "available")
    private boolean available;

    @Builder
    public Item(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy,
                Boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String title, String description,
                String brand, ItemType itemType, LocalDate manufactureDate, Long inventoryNumber, boolean isVerifiable,
                int verificationInterval, LocalDate latestVerificationDate, LocalDate expirationDate, String verificationCertificate,
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
