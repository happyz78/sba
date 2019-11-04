package com.ibm.fsd.sba.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class MentorCalendar {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="MID")
    private Long mid;
    @Column(name="START_DATETIME")
    private String startDatetime;
    @Column(name="END_DATETIME")
    private String endDatetime;
    @Column(name="START_TIME")
    private String startTime;
    @Column(name="END_TIME")
    private String endTime;
}
