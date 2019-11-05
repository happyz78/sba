package com.ibm.fsd.sba.payment.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MentorCalendarDto {
    private Long id;
    private Long mid;
    private String startDatetime;
    private String endDatetime;
    private String startTime;
    private String endTime;
}
