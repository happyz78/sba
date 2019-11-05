package com.ibm.fsd.sba.payment.feign;

import com.ibm.fsd.sba.payment.model.MentorSkillDto;
import com.ibm.fsd.sba.payment.model.ResponseDto;
import com.ibm.fsd.sba.payment.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("user")
public interface UserFeign {

    @PostMapping("/user/api/user/v1/findByUserId")
    ResponseDto<UserDto> findByUserId(@RequestParam Long id);


    @PostMapping("/user/api/user/v1/findMentorSkillById")
    ResponseDto<List<MentorSkillDto>> findMentorSkillById(@RequestParam Long id);
}
