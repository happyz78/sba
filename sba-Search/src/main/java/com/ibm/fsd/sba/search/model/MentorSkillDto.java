package com.ibm.fsd.sba.search.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
public class MentorSkillDto {
    private Long id;
    private Long sid;
    private String selfRating;
    private String yearsOfExperience;
    private String trainingsDelivered;
    private String facitiliesOffered;

    private MentorDto mentor;
}
