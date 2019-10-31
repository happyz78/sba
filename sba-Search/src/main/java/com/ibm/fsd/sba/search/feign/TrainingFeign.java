package com.ibm.fsd.sba.search.feign;

import com.ibm.fsd.sba.search.model.ResponseDto;
import com.ibm.fsd.sba.search.model.TrainingsDto;
import com.ibm.fsd.sba.search.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("training")
public interface TrainingFeign {
    @PostMapping("/training/api/training/v1/findTrainings")
    ResponseDto<List<TrainingsDto>> findTrainings(TrainingsDto trainingsDto);
}
