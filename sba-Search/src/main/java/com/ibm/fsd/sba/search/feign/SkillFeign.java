package com.ibm.fsd.sba.search.feign;

import com.ibm.fsd.sba.search.model.ResponseDto;
import com.ibm.fsd.sba.search.model.SkillDto;
import com.ibm.fsd.sba.search.model.TrainingsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("technology")
public interface SkillFeign {
    @PostMapping("/technology/api/technology/v1/findAllSkills")
    ResponseDto<List<SkillDto>> findAllSkills();
}
