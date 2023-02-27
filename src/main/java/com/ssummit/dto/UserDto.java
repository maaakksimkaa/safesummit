package com.ssummit.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends GenericDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String address;
    private String passportNo;
    private String login;
    private String password;
    private RoleDto role;
    private Set<Long> toursIds;
}
