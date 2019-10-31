package com.ibm.fsd.sba.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="CONTACT_NUMBER")
    private String contactNumber;
    @Column(name="REG_DATETIME")
    private Date regDatetime;
    @Column(name="REG_CODE")
    private String regCode;
    @Column(name="FORCE_REST_PASSWORD")
    private String forceResetPassword;
    @Column(name="ACTIVE")
    private String active;
    @Column(name="USER_TYPE")
    private String userType;
}
