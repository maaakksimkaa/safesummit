package com.ssummit.dto;

import com.ssummit.model.ItemType;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends GenericDto{

    private String title;
    private String description;
    private String brand;
    private ItemType itemType;
    private LocalDate manufactureDate;
    private Long inventoryNumber;
    private boolean isVerifiable;
    private Integer verificationInterval;
    private LocalDate latestVerificationDate;
    private LocalDate expirationDate;
    private String verificationCertificate;
    private boolean available;
}
