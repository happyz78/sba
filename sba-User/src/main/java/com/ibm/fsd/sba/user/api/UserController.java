package com.ibm.fsd.sba.user.api;

import com.ibm.fsd.sba.user.entity.Mentor;
import com.ibm.fsd.sba.user.entity.MentorCalendar;
import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;
import com.ibm.fsd.sba.user.model.ResponseDto;
import com.ibm.fsd.sba.user.model.UserDto;
import com.ibm.fsd.sba.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/query")
    public ResponseDto<User> query(@RequestBody User user) {
        user = userService.query(user);
        return ResponseDto.getSuccessResponseDto(user);
    }

    @PostMapping("/signup")
    public ResponseDto<User> signup(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserType(userDto.getUserType());
        try {
            user = userService.addUser(user);
        } catch (Exception e) {
            if (e.getMessage().startsWith("Duplicate")) {
                return new ResponseDto<>("500", "User Name is duplicate!", null);
            } else {
                throw e;
            }
        }
        if ("1".equals(userDto.getUserType())) {
            Mentor mentor = new Mentor();
            mentor.setUserName(user.getUserName());
            mentor.setYearsOfExperience(userDto.getYearsOfExperience());
            mentor.setUser(user);
            mentor = userService.saveMentor(mentor);

            MentorSkill mentorSkill = new MentorSkill();
            mentorSkill.setMentor(mentor);
            mentorSkill.setSelfRating(userDto.getSelfRating());
            mentorSkill.setSid(userDto.getSid());
            mentorSkill.setYearsOfExperience(userDto.getYearsOfExperience());
            mentorSkill = userService.saveMentorSkill(mentorSkill);
        }
        return ResponseDto.getSuccessResponseDto(user);
    }

    @PostMapping("/save")
    public ResponseDto<User> addUser(@RequestBody User user) {
        user = userService.addUser(user);
        return ResponseDto.getSuccessResponseDto(user);
    }

    @PostMapping("/findByUserId")
    public ResponseDto<User> findByUserId(@RequestParam Long id) {
        User user = userService.findUserId(id);
        return ResponseDto.getSuccessResponseDto(user);
    }

    @PostMapping("/findUser")
    public ResponseDto<User> findUser(@RequestBody User user) {
        user = userService.findUser(user);
        return ResponseDto.getSuccessResponseDto(user);
    }

    @PostMapping("/findMentors")
    public ResponseDto<List<MentorSkill>> findMentors(@RequestBody MentorSkill mentorSkill) {
        List<MentorSkill> list = userService.findMentors(mentorSkill);
        return ResponseDto.getSuccessResponseDto(list);
    }

    @PostMapping("/findMentorCalendar")
    public ResponseDto<List<MentorCalendar>> findMentorCalendar(@RequestBody MentorCalendar mentorCalendar) {
        List<MentorCalendar> list = userService.findMentorCalendar(mentorCalendar);
        return ResponseDto.getSuccessResponseDto(list);
    }


    @PostMapping("/saveMentorCalendar")
    public ResponseDto<MentorCalendar> saveMentorCalendar(@RequestBody MentorCalendar mentorCalendar) {
        mentorCalendar = userService.saveMentorCalendar(mentorCalendar);
        return ResponseDto.getSuccessResponseDto(mentorCalendar);
    }
}
