package com.ssummit.dto;

import com.ssummit.model.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String title;
    private String description;

    public RoleDto (Role role) {
        this.id = role.getId();
        this.title = role.getTitle();
        this.description = role.getDescription();
    }
}
