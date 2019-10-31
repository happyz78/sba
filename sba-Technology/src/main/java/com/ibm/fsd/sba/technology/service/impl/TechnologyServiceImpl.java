package com.ibm.fsd.sba.technology.service.impl;

import com.ibm.fsd.sba.technology.entity.Skill;
import com.ibm.fsd.sba.technology.repository.SkillRepository;
import com.ibm.fsd.sba.technology.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findSkillById(Long id) {
        return skillRepository.findById(id);
    }
}
