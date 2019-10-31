package com.ibm.fsd.sba.user.api;

import com.ibm.fsd.sba.user.entity.User;
import com.ibm.fsd.sba.user.model.ResponseDto;
import com.ibm.fsd.sba.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/query")
    public ResponseDto<User> query(@RequestBody User user) {
        log.error(user.toString());
        user = userService.findUser(user);
        return ResponseDto.getSuccessResponseDto(user);
    }

    @PostMapping("/save")
    public ResponseDto<User> addUser(@RequestBody User user) {
        user = userService.addUser(user);
        return ResponseDto.getSuccessResponseDto(user);
    }
}
