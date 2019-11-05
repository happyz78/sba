package com.ibm.fsd.sba.user.repository;

import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorSkillRepository extends JpaRepository<MentorSkill, Integer> {
    List<MentorSkill> findBySid(Long sid);

    @Query(value = "SELECT * FROM mentor_skill u WHERE u.mid = :mid",
        nativeQuery = true)
    List<MentorSkill> findByMid(@Param("mid")  Long mid);
}
