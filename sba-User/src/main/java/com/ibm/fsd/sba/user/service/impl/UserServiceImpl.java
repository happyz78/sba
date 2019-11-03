package com.ibm.fsd.sba.user.service.impl;

import com.ibm.fsd.sba.user.entity.MentorSkill;
import com.ibm.fsd.sba.user.entity.User;
import com.ibm.fsd.sba.user.repository.MentorSkillRepository;
import com.ibm.fsd.sba.user.repository.UserRepository;
import com.ibm.fsd.sba.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MentorSkillRepository mentorSkillRepository;

    @Override
    public User addUser(User user) {
        user.setId(null);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    @Override
    public Boolean checkUserCriteria(User user) {
        return null;
    }

    @Override
    public User findUser(User user) {
        return null;
    }

    @Override
    public User findUserId(Long id) {
        User user = userRepository.findById(id);
        return user;
    }

    @Override
    public User query(User user) {
        return userRepository.findByUserName(user.getUserName());
    }

    @Override
    public List<MentorSkill> findMentors(MentorSkill mentorSkill) {
        return mentorSkillRepository.findBySid(mentorSkill.getSid());
    }
}
