package com.ssummit.dto;
import lombok.Data;

import java.util.Date;

@Data
public class SignUpDto {
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
    private Long roleId;
}
