package com.ssummit.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message_types")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    public MessageType(MessageTypeDto messageTypeDto) {
//        this.id = messageTypeDto.getId();
//        this.title = messageTypeDto.getTitle();
//        this.description = messageTypeDto.getDescription();
//    }
}
