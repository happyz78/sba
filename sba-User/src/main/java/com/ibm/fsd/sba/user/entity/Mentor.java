package com.ibm.fsd.sba.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Entity
public class Mentor implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="user_Id")
    private Long userId;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="LINKEDDIN_URL")
    private String linkeddinUrl;
    @Column(name="REG_DATETIME")
    private String regDatatime;
    @Column(name="REG_CODE")
    private String regCode;
    @Column(name="YEARS_OF_EXPERIENCE")
    private String yearsOfExperience;
    @Column(name="ACTIVE")
    private String active;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="mid", referencedColumnName="user_Id")
    private List<MentorCalendar> mentorCalendar;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="id", referencedColumnName="user_Id")
    private User user;
}
