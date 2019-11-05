package com.ibm.fsd.sba.payment.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TrainingsDto {
    private Long id;
    private Long userId;
    private Long mentorId;
    private Long skillId;
    private int status;
    private int progress;
    private int rating;
    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;
    private String userName;
    private String mentorName;
    private String skillName;
}
