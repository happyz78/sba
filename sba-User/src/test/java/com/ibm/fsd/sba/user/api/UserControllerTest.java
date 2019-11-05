package com.ibm.fsd.sba.user.api;

import com.ibm.fsd.sba.user.entity.Mentor;
import com.ibm.fsd.sba.user.entity.MentorCalendar;
import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;
import com.ibm.fsd.sba.user.model.ResponseDto;
import com.ibm.fsd.sba.user.model.UserDto;
import com.ibm.fsd.sba.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void query() {
        User user = new User();
        User result = new User();
        result.setUserName("test");
        when(userService.query(any(User.class))).thenReturn(result);
        ResponseDto<User> obj = userController.query(user);
        assertEquals(result.getUserName(), obj.getData().getUserName());
    }

    @Test
    public void signup() {
        UserDto param =  new UserDto();
        param.setUserType("1");
        User result = new User();
        result.setUserName("test");
        result.setUserType("1");
        when(userService.addUser(any(User.class))).thenReturn(result);
        Mentor mentor = new Mentor();
        when(userService.saveMentor(any(Mentor.class))).thenReturn(mentor);
        MentorSkill mentorSkill = new MentorSkill();
        when(userService.saveMentorSkill(any(MentorSkill.class))).thenReturn(mentorSkill);
        ResponseDto<User> obj = userController.signup(param);
        assertEquals(result.getUserName(), obj.getData().getUserName());
    }

    @Test
    public void addUser() {
        User user = new User();
        User result = new User();
        result.setUserName("test");
        when(userService.addUser(any(User.class))).thenReturn(result);
        ResponseDto<User> obj = userController.addUser(user);
        assertEquals(result.getUserName(), obj.getData().getUserName());
    }

    @Test
    public void findByUserId() {
        User result = new User();
        result.setUserName("test");
        when(userService.findUserId(anyLong())).thenReturn(result);
        ResponseDto<User> obj = userController.findByUserId(1L);
        assertEquals(result.getUserName(), obj.getData().getUserName());
    }

    @Test
    public void findMentorSkillById() {
        List<MentorSkill> result = new ArrayList<>();
        MentorSkill skill = new MentorSkill();
        skill.setSelfRating("50");
        result.add(skill);
        when(userService.findByUserId(anyLong())).thenReturn(result);
        ResponseDto<List<MentorSkill>> obj = userController.findMentorSkillById(1L);
        assertEquals(skill.getSelfRating(), obj.getData().get(0).getSelfRating());
    }

    @Test
    public void findUser() {
        User user = new User();
        User result = new User();
        result.setUserName("test");
        when(userService.findUser(any(User.class))).thenReturn(result);
        ResponseDto<User> obj = userController.findUser(user);
        assertEquals(result.getUserName(), obj.getData().getUserName());
    }

    @Test
    public void findMentors() {
        MentorSkill mentorSkill = new MentorSkill();
        List<MentorSkill> result = new ArrayList<>();
        MentorSkill skill = new MentorSkill();
        skill.setSelfRating("50");
        result.add(skill);
        when(userService.findMentors(any(MentorSkill.class))).thenReturn(result);
        ResponseDto<List<MentorSkill>> obj = userController.findMentors(mentorSkill);
        assertEquals(skill.getSelfRating(), obj.getData().get(0).getSelfRating());
    }

    @Test
    public void findMentorCalendar() {
        MentorCalendar mentorCalendar = new MentorCalendar();
        List<MentorCalendar> result = new ArrayList<>();
        MentorCalendar calendar = new MentorCalendar();
        calendar.setEndTime("55");
        result.add(calendar);
        when(userService.findMentorCalendar(any(MentorCalendar.class))).thenReturn(result);
        ResponseDto<List<MentorCalendar>> obj = userController.findMentorCalendar(mentorCalendar);
        assertEquals(calendar.getEndTime(), obj.getData().get(0).getEndTime());
    }

    @Test
    public void saveMentorCalendar() {
        MentorCalendar mentorCalendar = new MentorCalendar();
        MentorCalendar result = new MentorCalendar();
        result.setEndTime("55");
        when(userService.saveMentorCalendar(any(MentorCalendar.class))).thenReturn(result);
        ResponseDto<MentorCalendar> obj = userController.saveMentorCalendar(mentorCalendar);
        assertEquals(result.getEndTime(), obj.getData().getEndTime());
    }
}