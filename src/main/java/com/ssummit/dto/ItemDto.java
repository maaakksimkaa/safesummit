package com.ssummit.dto;

import com.ssummit.model.ItemType;
import lombok.*;

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
    private Date manufactureDate;
    private Long inventoryNumber;
    private boolean isVerifiable;
    private Date verificationInterval;
    private Date latestVerificationDate;
    private Date expirationDate;
    private String verificationCertificate;
    private boolean available;
}
