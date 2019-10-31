package com.ibm.fsd.sba.training.feign;

import com.ibm.fsd.sba.training.model.ResponseDto;
import com.ibm.fsd.sba.training.model.SkillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("technology")
public interface TechnologyFeign {
    @PostMapping("/technology/api/technology/v1/findSkill")
    ResponseDto<SkillDto> findSkillById(@RequestParam Long id);
}
