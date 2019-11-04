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
@RestController
@RequestMapping("/api/training/v1")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @PostMapping("/findTrainingsByMentorId")
    public ResponseDto<List<TrainingsDto>> findTrainingByMentorId(@RequestBody TrainingsDto trainingsDto) {
        List<TrainingsDto> list = trainingService.findTrainingByMentorId(trainingsDto.getMentorId());
        return ResponseDto.getSuccessResponseDto(list);
    }

    @PostMapping("/findTrainingsByUserId")
    public ResponseDto<List<TrainingsDto>> findTrainingsByUserId(@RequestBody TrainingsDto trainingsDto) {
        List<TrainingsDto> list = trainingService.findTrainingByUserId(trainingsDto);
        return ResponseDto.getSuccessResponseDto(list);
    }

    @PostMapping("/findTrainings")
    public ResponseDto<List<TrainingsDto>> findTrainings(@RequestBody TrainingsDto trainingsDto) {
        List<TrainingsDto> list = trainingService.findTrainings(trainingsDto);
        return ResponseDto.getSuccessResponseDto(list);
    }
    @PostMapping("/save")
    public ResponseDto<Trainings> save(@RequestBody Trainings trainingsDto) {
        Trainings dto = trainingService.save(trainingsDto);
        return ResponseDto.getSuccessResponseDto(dto);
    }

    @PostMapping("/findConfirm")
    public ResponseDto<List<TrainingsDto>> findConfirm(@RequestBody TrainingsDto trainingsDto) {
        List<TrainingsDto> list = trainingService.findConfirm(trainingsDto);
        return ResponseDto.getSuccessResponseDto(list);
    }
}
