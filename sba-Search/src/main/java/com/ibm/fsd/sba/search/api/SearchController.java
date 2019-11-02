package com.ibm.fsd.sba.search.api;

import com.ibm.fsd.sba.search.feign.TrainingFeign;
import com.ibm.fsd.sba.search.model.ResponseDto;
import com.ibm.fsd.sba.search.model.TrainingsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/search/v1")
public class SearchController {
    @Autowired
    TrainingFeign trainingFeign;

    @PostMapping("/findMentor")
    public ResponseDto<List<TrainingsDto>> findTrainingByMentorId(@RequestBody TrainingsDto trainingsDto) {
        return trainingFeign.findTrainings(trainingsDto);
    }
}
