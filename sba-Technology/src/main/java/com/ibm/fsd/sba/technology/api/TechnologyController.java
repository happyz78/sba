package com.ibm.fsd.sba.technology.api;

import com.ibm.fsd.sba.technology.entity.Skill;
import com.ibm.fsd.sba.technology.model.ResponseDto;
import com.ibm.fsd.sba.technology.repository.SkillRepository;
import com.ibm.fsd.sba.technology.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/technology/v1")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @PostMapping("/findAllSkills")
    public ResponseDto<List<Skill>> findAllSkills() {
        List<Skill> list = technologyService.findAllSkills();
        return ResponseDto.getSuccessResponseDto(list);
    }


    @PostMapping("/findSkill")
    public ResponseDto<Skill> findSkillById(@RequestParam Long id) {
        Skill skill = technologyService.findSkillById(id);
        return ResponseDto.getSuccessResponseDto(skill);
    }
}
