package com.ibm.fsd.sba.payment.schedule;

import com.ibm.fsd.sba.payment.entity.Payments;
import com.ibm.fsd.sba.payment.feign.TrainingFeign;
import com.ibm.fsd.sba.payment.feign.UserFeign;
import com.ibm.fsd.sba.payment.model.MentorSkillDto;
import com.ibm.fsd.sba.payment.model.ResponseDto;
import com.ibm.fsd.sba.payment.model.TrainingsDto;
import com.ibm.fsd.sba.payment.model.UserDto;
import com.ibm.fsd.sba.payment.repository.PaymentRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;


@Slf4j
@Component
public class PaymentTask {

    @Autowired
    TrainingFeign trainingFeign;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserFeign userFeign;

    @Scheduled(cron="* * 1 * * ?")
    private void process() {
        ResponseDto<List<TrainingsDto>> result = trainingFeign.findTrainings(new TrainingsDto());
        List<TrainingsDto> list = null;
        if ("00000".equals(result.getCode())) {
            list = result.getData();
        }
        if (CollectionUtils.isEmpty(list))  {
            return;
        }
        log.error("....................start process.......................");
        list.forEach(training -> {
            log.error("....................txnType:" + training);
            Integer progress = training.getProgress();
            String txnType = getTxnType(progress);
            if ("0".equals(txnType)) {
                return;
            }
            log.error("....................txnType:" + txnType);
            Payments payment = paymentRepository.findByTrainingId(training.getId());
            if (payment == null) {
                payment = new Payments();
                payment.setMentorId(training.getMentorId());
                payment.setTrainingId(training.getId());
                payment.setTxnType("0");
                payment.setAmount("0");
                payment.setDatetime(Calendar.getInstance().getTime());
                payment.setRemarks("Payment Task Generate");
            }
            if (payment.getTxnType().equals(txnType)) {
                return;
            }
            List<MentorSkillDto> mentorSkillDtoList = userFeign.findMentorSkillById(training.getMentorId()).getData();
            MentorSkillDto mentor = mentorSkillDtoList.get(0);
            Double total = Double.parseDouble(mentor.getSelfRating());
            Double amount = total * Double.parseDouble(txnType) / 4;
            payment.setTxnType(txnType);
            payment.setAmount(amount.toString());
            paymentRepository.save(payment);
        });
    }

    private String getTxnType(Integer progress) {
        if (progress < 25) {
            return "0";
        } else if (progress < 50) {
            return "1";
        } else if (progress < 75) {
            return "2";
        } else if (progress < 100) {
            return "3";
        } else {
            return "4";
        }
    }
}
