package com.ibm.fsd.sba.training.api;

import com.ibm.fsd.sba.training.feign.UserFeign;
import com.ibm.fsd.sba.training.model.ResponseDto;
import com.ibm.fsd.sba.training.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/training/v1")
public class TrainingController {
    @Autowired
    UserFeign userFeign;

    @PostMapping("/user/query")
    public ResponseDto<UserDto> hello(@RequestBody UserDto user) {
        ResponseDto<UserDto> result = userFeign.queryUser(user);
        return  result;
    }
}
