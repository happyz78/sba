package com.ibm.fsd.sba.search.api;

import com.ibm.fsd.sba.search.feign.SkillFeign;
import com.ibm.fsd.sba.search.feign.TrainingFeign;
import com.ibm.fsd.sba.search.feign.UserFeign;
import com.ibm.fsd.sba.search.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/search/v1")
public class SearchController {
    @Autowired
    UserFeign userFeign;
    @Autowired
    SkillFeign skillFeign;

    @PostMapping("/findMentors")
    public ResponseDto<List<MentorSkillDto>> findTrainingByMentorId(@RequestBody MentorSkillDto mentorSkillDto) {
        return userFeign.findMentors(mentorSkillDto);
    }

    @PostMapping("/findAllSkills")
    public ResponseDto<List<SkillDto>> findAllSkills() {
        return skillFeign.findAllSkills();
    }

    @PostMapping("/signup")
    public ResponseDto<UserDto> signup(@RequestBody UserDto userDto) {
        return userFeign.signup(userDto);
    }
}
