package com.ssummit.dto;

import com.ssummit.model.Checkpoint;
import com.ssummit.model.ItemType;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto extends GenericDto {

    private String title;
    private String description;
    private Integer duration;
    private String category;
    private Set<Checkpoint> checkpoints;
    private Set<ItemType> requiredItemTypes;
}
