package com.ssummit.dto;

import com.ssummit.model.Route;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemTypeDto extends GenericDto {

    private Long id;
    private String title;
    private String description;
    private Set<Route> routes;
}
