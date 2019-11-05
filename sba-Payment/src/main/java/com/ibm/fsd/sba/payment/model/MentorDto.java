package com.ibm.fsd.sba.payment.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class MentorDto implements Serializable {
    private Long id;
    private Long userId;
    private String userName;
    private String linkeddinUrl;
    private String regDatatime;
    private String regCode;
    private String yearsOfExperience;
    private String active;

    private List<MentorCalendarDto> mentorCalendar;

    private UserDto user;
}
