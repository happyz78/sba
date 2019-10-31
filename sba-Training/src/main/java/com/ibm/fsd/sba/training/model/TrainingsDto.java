package com.ibm.fsd.sba.training.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
