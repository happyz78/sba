package com.ibm.fsd.sba.user.service.impl;

import com.ibm.fsd.sba.user.entity.User;
import com.ibm.fsd.sba.user.repository.UserRepository;
import com.ibm.fsd.sba.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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
        return userRepository.findByUserName(user.getUserName());
    }

    @Override
    public User findUserId(Long id) {
        User user = userRepository.findById(id);
        return user;
    }
}
