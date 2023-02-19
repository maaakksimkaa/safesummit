package com.ssummit.dto;

import com.ssummit.model.MessageType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto extends GenericDto {

    private String title;
    private String description;
    private MessageType messageType;
}
