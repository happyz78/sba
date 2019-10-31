package com.ibm.fsd.sba.training.service.impl;

import com.ibm.fsd.sba.training.entity.Trainings;
import com.ibm.fsd.sba.training.feign.TechnologyFeign;
import com.ibm.fsd.sba.training.feign.UserFeign;
import com.ibm.fsd.sba.training.model.ResponseDto;
import com.ibm.fsd.sba.training.model.SkillDto;
import com.ibm.fsd.sba.training.model.TrainingsDto;
import com.ibm.fsd.sba.training.model.UserDto;
import com.ibm.fsd.sba.training.repository.TrainingRepository;
import com.ibm.fsd.sba.training.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    UserFeign userFeign;
    @Autowired
    TechnologyFeign technologyFeign;

    @Override
    public List<TrainingsDto> findTrainingByMentorId(Long id) {
        List<Trainings> list = trainingRepository.findByMentorId(id);
        List<TrainingsDto> result = modifyList(list);
        return result;
    }

    @Override
    public List<TrainingsDto> findTrainingByUserId(Long id) {
        List<Trainings> list = trainingRepository.findByUserId(id);
        List<TrainingsDto> result = modifyList(list);
        return result;
    }

    @Override
    public List<TrainingsDto> findTrainings(TrainingsDto trainingsDto) {
        log.error(trainingsDto.toString());
        List<Trainings> list = trainingRepository.findTrainings(trainingsDto.getSkillId(),
                trainingsDto.getStartTime(), trainingsDto.getEndTime());
        List<TrainingsDto> result = modifyList(list);
        return result;
    }

    private List<TrainingsDto> modifyList(List<Trainings> list) {
        if (list == null) {
            return null;
        }
        List<TrainingsDto> result = list.stream().map(item -> {
            String userName = findUserName(item.getUserId());
            String mentorName = findUserName(item.getMentorId());
            TrainingsDto trainingsDto = new TrainingsDto();
            BeanUtils.copyProperties(item, trainingsDto);
            trainingsDto.setUserName(userName);
            trainingsDto.setMentorName(mentorName);

            String skillName = findSkillName(item.getSkillId());
            trainingsDto.setSkillName(skillName);
            return trainingsDto;
        }).collect(Collectors.toList());
        return result;
    }

    private String findSkillName(Long id) {
        ResponseDto<SkillDto> responseDto = technologyFeign.findSkillById(id);
        if ("SUCCESS".equals(responseDto.getMessage())) {
            SkillDto skill = responseDto.getData();
            return skill.getName();
        }
        log.error(responseDto.getMessage());
        throw new RuntimeException(responseDto.getMessage());
    }

    private String findUserName(Long id) {
        ResponseDto<UserDto> responseDto = userFeign.findByUserId(id);
        if ("SUCCESS".equals(responseDto.getMessage())) {
            UserDto user = responseDto.getData();
            return user.getUserName();
        }
        log.error(responseDto.getMessage());
        throw new RuntimeException(responseDto.getMessage());
    }
}
