package com.ibm.fsd.sba.training.repository;

import com.ibm.fsd.sba.training.entity.Trainings;
import com.ibm.fsd.sba.training.model.TrainingsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository  extends JpaRepository<Trainings, Integer> {
    List<Trainings> findByMentorId(Long id);
    List<Trainings> findByUserId(Long id);
    List<Trainings> findByStatus(Integer status);

    @Query(value = "SELECT t.`ID`," +
            "    t.`USER_ID`," +
            "    t.`MENTOR_ID`," +
            "    t.`SKILL_ID`," +
            "    t.`STATUS`," +
            "    t.`PROGRESS`," +
            "    t.`RATING`," +
            "    t.`START_DATE`," +
            "    t.`END_DATE`," +
            "    t.`START_TIME`," +
            "    t.`END_TIME`" +
            " FROM Trainings t " +
            " WHERE (t.skill_id = :skillId or :skillId is null)" +
            " and (t.start_time < :startTime or :startTime is null or :startTime = '')" +
            " and (t.end_time < :endTime or :endTime is null or :endTime = '')",
            nativeQuery = true)
    List<Trainings>  findTrainings(@Param("skillId") Long id,
                                   @Param("startTime") String startTime,
                                   @Param("endTime") String endTime);


    @Query(value = "SELECT t.`ID`," +
            "    t.`USER_ID`," +
            "    t.`MENTOR_ID`," +
            "    t.`SKILL_ID`," +
            "    t.`STATUS`," +
            "    t.`PROGRESS`," +
            "    t.`RATING`," +
            "    t.`START_DATE`," +
            "    t.`END_DATE`," +
            "    t.`START_TIME`," +
            "    t.`END_TIME`" +
            " FROM Trainings t " +
            " WHERE (t.MENTOR_ID = :userId or :userId is null)" +
            " and (t.STATUS = :status or :status is null)" ,
            nativeQuery = true)
    List<Trainings>  findConfirm(@Param("userId") Long userId,
                                   @Param("status") Integer status);

    @Query(value = "SELECT t.`ID`," +
            "    t.`USER_ID`," +
            "    t.`MENTOR_ID`," +
            "    t.`SKILL_ID`," +
            "    t.`STATUS`," +
            "    t.`PROGRESS`," +
            "    t.`RATING`," +
            "    t.`START_DATE`," +
            "    t.`END_DATE`," +
            "    t.`START_TIME`," +
            "    t.`END_TIME`" +
            " FROM Trainings t " +
            " WHERE (t.MENTOR_ID = :mentorId or :mentorId is null)" +
            " and (t.USER_ID = :userId or :userId is null)",
            nativeQuery = true)
    List<Trainings> findTrainingByUserId(@Param("userId") Long userId, @Param("mentorId") Long mentorId);
}
