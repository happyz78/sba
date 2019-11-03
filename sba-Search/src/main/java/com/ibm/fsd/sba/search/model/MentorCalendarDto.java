package com.ibm.fsd.sba.search.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
public class MentorCalendarDto {
    private Long id;
    private Long mid;
    private String startDatetime;
    private String endDatetime;
}
