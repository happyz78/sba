package com.ibm.fsd.sba.user.service;

import com.ibm.fsd.sba.user.entity.Mentor;
import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    Boolean checkUserCriteria(User user);
    User findUser(User user);
    User findUserId(Long id);
    User query(User user);
    Mentor saveMentor(Mentor mentor);
    List<MentorSkill> findMentors(MentorSkill mentorSkill);
    MentorSkill saveMentorSkill(MentorSkill mentorSkill);
}
