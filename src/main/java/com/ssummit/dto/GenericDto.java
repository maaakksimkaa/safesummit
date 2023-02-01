package com.ssummit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class GenericDto {

    private Long id;
    private String createdBy;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String updatedBy;
    private boolean isDeleted;
    private LocalDateTime deletedDateTime;
    private String deletedBy;
}
