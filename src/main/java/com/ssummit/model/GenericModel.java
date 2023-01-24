package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GenericModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_genrator")
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_timestamp")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_timestamp")
    private LocalDateTime updatedDateTime;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_timestamp")
    private LocalDateTime deletedDateTime;

    @Column(name = "deleted_by")
    private String deletedBy;
}
