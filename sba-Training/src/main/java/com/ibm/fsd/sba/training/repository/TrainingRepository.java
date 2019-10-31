package com.ibm.fsd.sba.training.repository;

import com.ibm.fsd.sba.training.entity.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository  extends JpaRepository<Trainings, Integer> {
    List<Trainings> findByMentorId(Long id);
    List<Trainings> findByUserId(Long id);
}
