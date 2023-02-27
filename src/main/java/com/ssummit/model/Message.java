package com.ssummit.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "message_seq", allocationSize = 1)
public class Message{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_timestamp")
    private LocalDateTime createdDateTime;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "message_type_id",
            foreignKey = @ForeignKey(name = "FK_MESSAGE_MESSAGETYPE")
    )
    private MessageType messageType;

    @Builder
    public Message(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy,
                   Boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String title, String description,
                   MessageType messageType) {
        this.title = title;
        this.description = description;
        this.messageType = messageType;
    }
}
