package com.ibm.fsd.sba.user.repository;

import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorSkillRepository extends JpaRepository<MentorSkill, Integer> {
    List<MentorSkill> findBySid(Long sid);
}
