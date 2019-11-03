package com.ibm.fsd.sba.search.feign;

import com.ibm.fsd.sba.search.model.MentorSkillDto;
import com.ibm.fsd.sba.search.model.ResponseDto;
import com.ibm.fsd.sba.search.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("user")
public interface UserFeign {

    @PostMapping("/user/api/user/v1/query")
    ResponseDto<UserDto> queryUser(UserDto user);
    @PostMapping("/user/api/user/v1/findMentors")
    ResponseDto<List<MentorSkillDto>> findMentors(MentorSkillDto mentorSkillDto);
}
