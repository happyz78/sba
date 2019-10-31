package com.ibm.fsd.sba.jwt.feign;

import com.ibm.fsd.sba.jwt.model.ResponseDto;
import com.ibm.fsd.sba.jwt.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("user")
public interface UserFeign {

    @PostMapping("/user/api/user/v1/query")
    ResponseDto<UserDto> queryUser(UserDto user);
}
