package com.ssummit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckpointDto extends GenericDto{

    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
}
