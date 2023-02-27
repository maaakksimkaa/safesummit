package com.ssummit.dto;

import com.ssummit.model.Item;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourEquipmentDto extends GenericDto{

    private Set<Item> itemList;
    private LocalDateTime dateOfIssue;
}
