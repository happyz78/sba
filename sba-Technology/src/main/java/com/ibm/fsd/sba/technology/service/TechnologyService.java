package com.ibm.fsd.sba.technology.service;

import com.ibm.fsd.sba.technology.entity.Skill;

import java.util.List;

public interface TechnologyService {
    List<Skill> findAllSkills();
    Skill findSkillById(Long id);
}
