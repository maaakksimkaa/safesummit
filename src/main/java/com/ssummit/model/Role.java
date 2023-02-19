package com.ssummit.model;

import com.ssummit.dto.RoleDto;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Role(RoleDto roleDto) {
        this.id = roleDto.getId();
        this.title = roleDto.getTitle();
        this.description = roleDto.getDescription();
    }
}
