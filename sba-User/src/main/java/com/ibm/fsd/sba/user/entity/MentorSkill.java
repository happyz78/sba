package com.ibm.fsd.sba.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class MentorSkill {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="SID")
    private Long sid;
    @Column(name="SELF_RATING")
    private String selfRating;
    @Column(name="YEARS_OF_EXPERIENCE")
    private String yearsOfExperience;
    @Column(name="TRAININGS_DELIVERED")
    private String trainingsDelivered;
    @Column(name="FACITILIES_OFFERED")
    private String facitiliesOffered;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="mid", referencedColumnName="user_Id")
    private Mentor mentor;
}
