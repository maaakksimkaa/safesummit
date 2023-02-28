package com.ssummit.dto;

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
    private Set<Long> checkpointsIds;
    private Set<Long> requiredItemTypesIds;
}
