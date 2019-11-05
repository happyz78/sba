package com.ibm.fsd.sba.user.service.impl;

import com.ibm.fsd.sba.user.api.UserController;
import com.ibm.fsd.sba.user.entity.Mentor;
import com.ibm.fsd.sba.user.entity.MentorCalendar;
import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;
import com.ibm.fsd.sba.user.model.ResponseDto;
import com.ibm.fsd.sba.user.repository.MentorCalendarRespository;
import com.ibm.fsd.sba.user.repository.MentorRespository;
import com.ibm.fsd.sba.user.repository.MentorSkillRepository;
import com.ibm.fsd.sba.user.repository.UserRepository;
import com.ibm.fsd.sba.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();;

    @Mock
    private UserRepository userRepository;
    @Spy
    private MentorSkillRepository mentorSkillRepository;
    @Mock
    private MentorRespository mentorRespository;
    @Mock
    private MentorCalendarRespository mentorCalendarRespository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setPassword("123");
        User result = new User();
        result.setUserName("test");
        when(userRepository.save(any(User.class))).thenReturn(result);
        User obj = userService.addUser(user);
        assertEquals(result.getUserName(), obj.getUserName());
    }

    @Test
    public void checkUserCriteria() {
        User user = new User();
        assertNull(userService.checkUserCriteria(user));
    }

    @Test
    public void findUser() {
        User user = new User();
        assertNull(userService.findUser(user));
    }

    @Test
    public void findUserId() {
        User result = new User();
        result.setUserName("test");
        when(userRepository.findById(anyLong())).thenReturn(result);
        User obj = userService.findUserId(1L);
        assertEquals(result.getUserName(), obj.getUserName());
    }

    @Test
    public void query() {
        User result = new User();
        result.setUserName("test");
        when(userRepository.findByUserName(anyString())).thenReturn(result);
        User obj = userService.query(new User());
        assertNull(obj);
    }

    @Test
    public void findMentors() {
        List<MentorSkill> result = new ArrayList<>();
        when(mentorSkillRepository.findBySid(anyLong())).thenReturn(result);
        List<MentorSkill> obj = userService.findMentors(new MentorSkill());
        assertEquals(result, obj);
    }

    @Test
    public void saveMentorSkill() {
        MentorSkill skill = new MentorSkill();
        when(mentorSkillRepository.save(any(MentorSkill.class))).thenReturn(skill);
        MentorSkill obj = userService.saveMentorSkill(skill);
        assertEquals(skill, obj);
    }

    @Test
    public void findMentorCalendar() {
        MentorCalendar mentorCalendar = new MentorCalendar();
        List<MentorCalendar> result = new ArrayList<>();
        when(mentorCalendarRespository.findByMid(anyLong())).thenReturn(result);
        List<MentorCalendar> obj = userService.findMentorCalendar(mentorCalendar);
        assertEquals(result, obj);
    }

    @Test
    public void saveMentorCalendar() {
        MentorCalendar result = new MentorCalendar();
        when(mentorCalendarRespository.save(any())).thenReturn(result);
        MentorCalendar obj = userService.saveMentorCalendar(result);
        assertEquals(result, obj);
    }

    @Test
    public void findByUserId() {
        List<MentorSkill> result = new ArrayList<>();
        when(mentorSkillRepository.findByMid(any())).thenReturn(result);
        List<MentorSkill> obj = userService.findByUserId(1L);
        assertEquals(result, obj);
    }

    @Test
    public void saveMentor() {
        Mentor result = new Mentor();
        when(mentorRespository.save(any())).thenReturn(result);
        Mentor obj = userService.saveMentor(result);
        assertEquals(result, obj);
    }
}