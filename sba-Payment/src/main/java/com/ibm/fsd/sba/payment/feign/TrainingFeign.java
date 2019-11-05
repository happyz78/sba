package com.ibm.fsd.sba.payment.feign;

import com.ibm.fsd.sba.payment.model.ResponseDto;
import com.ibm.fsd.sba.payment.model.TrainingsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("training")
public interface TrainingFeign {
    @PostMapping("/training/api/training/v1/findTrainings")
    ResponseDto<List<TrainingsDto>> findTrainings(TrainingsDto trainingsDto);
}
