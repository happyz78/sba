package com.ibm.fsd.sba.payment.model;

import lombok.Data;
import lombok.ToString;

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
