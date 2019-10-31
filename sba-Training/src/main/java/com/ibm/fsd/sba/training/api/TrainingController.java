package com.ibm.fsd.sba.training.api;

import com.ibm.fsd.sba.training.entity.Trainings;
import com.ibm.fsd.sba.training.feign.UserFeign;
import com.ibm.fsd.sba.training.model.ResponseDto;
import com.ibm.fsd.sba.training.model.TrainingsDto;
import com.ibm.fsd.sba.training.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/training/v1")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @PostMapping("/findTrainingsByMentorId")
    public ResponseDto<List<TrainingsDto>> findTrainingByMentorId(@RequestParam Long id) {
        List<TrainingsDto> list = trainingService.findTrainingByMentorId(id);
        return ResponseDto.getSuccessResponseDto(list);
    }

    @PostMapping("/findTrainingsByUserId")
    public ResponseDto<List<TrainingsDto>> findTrainingsByUserId(@RequestParam Long id) {
        List<TrainingsDto> list = trainingService.findTrainingByUserId(id);
        return ResponseDto.getSuccessResponseDto(list);
    }
}
