package com.ssummit.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends GenericDto {

    @NotBlank(message = "Поле не должно быть пустым")
    private String firstName;

    @NotBlank(message = "Поле не должно быть пустым")
    private String middleName;

    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;

    @NotBlank(message = "Поле не должно быть пустым")
    private LocalDate birthDate;

    @NotBlank(message = "Поле не должно быть пустым")
    private String phone;

    @NotBlank(message = "Поле не должно быть пустым")
    private String email;

    @NotBlank(message = "Поле не должно быть пустым")
    private String address;

    @NotBlank(message = "Поле не должно быть пустым")
    private String passportNo;

    @NotBlank(message = "Поле не должно быть пустым")
    private String login;

    @NotBlank(message = "Поле не должно быть пустым")
    private String password;

    @NotBlank(message = "Поле не должно быть пустым")
    private RoleDto role;
    private Set<Long> assignedToursIds;
}
