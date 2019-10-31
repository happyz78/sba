package com.ibm.fsd.sba.training.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
public class Trainings {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="USER_ID")
    private Long userId;
    @Column(name="MENTOR_ID")
    private Long mentorId;
    @Column(name="SKILL_ID")
    private Long skillId;
    @Column(name="STATUS")
    //0.NOT START\n1.PROCESSING\n2.FINISH
    private int status;
    @Column(name="PROGRESS")
    private int progress;
    @Column(name="RATING")
    private int rating;
    @Column(name="START_DATE")
    private Date startDate;
    @Column(name="END_DATE")
    private Date endDate;
    @Column(name="START_TIME")
    private String startTime;
    @Column(name="END_TIME")
    private String endTime;
}
