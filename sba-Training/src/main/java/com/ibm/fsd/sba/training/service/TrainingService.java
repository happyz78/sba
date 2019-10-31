package com.ibm.fsd.sba.training.service;

import com.ibm.fsd.sba.training.entity.Trainings;
import com.ibm.fsd.sba.training.model.TrainingsDto;

import java.util.List;

public interface TrainingService {
    List<TrainingsDto> findTrainingByMentorId(Long id);
    List<TrainingsDto> findTrainingByUserId(Long id);
}
