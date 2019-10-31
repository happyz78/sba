package com.ibm.fsd.sba.jwt.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private Date regDatetime;
    private String regCode;
    private String forceResetPassword;
    private String active;
    private String userType;
}
