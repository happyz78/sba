package com.ibm.fsd.sba.payment.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="MENTOR_ID")
    private Long mentorId;
    @Column(name="TRAINING_ID")
    private Long trainingId;
    @Column(name="TXN_TYPE")
    private String txnType;
    @Column(name="AMOUNT")
    private String amount;
    @Column(name="DATETIME")
    private Date datetime;
    @Column(name="REMARKS")
    private String remarks;
}
