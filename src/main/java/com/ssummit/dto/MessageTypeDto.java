package com.ssummit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTypeDto {

    private Long id;
    private String title;
    private String description;
}
