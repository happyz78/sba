package com.ibm.fsd.sba.user.service;

import com.ibm.fsd.sba.user.entity.User;

public interface UserService {
    User addUser(User user);
    Boolean checkUserCriteria(User user);
    User findUser(User user);
    User findUserId(Long id);
    User query(User user);
}
