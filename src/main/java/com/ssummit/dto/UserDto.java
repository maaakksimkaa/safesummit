package com.ssummit.dto;

import com.ssummit.model.Role;
import com.ssummit.model.Tour;
import lombok.*;

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
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
    private String passportNo;
    private String login;
    private String password;
    private Role role;
    private Set<Tour> assignedTours;
}
