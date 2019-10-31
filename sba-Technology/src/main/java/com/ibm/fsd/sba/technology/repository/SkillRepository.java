package com.ibm.fsd.sba.technology.repository;

import com.ibm.fsd.sba.technology.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findAll();
    Skill findById(Long id);
}
