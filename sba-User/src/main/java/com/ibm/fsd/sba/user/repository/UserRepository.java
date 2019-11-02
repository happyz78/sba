package com.ibm.fsd.sba.user.repository;

import com.ibm.fsd.sba.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findById(Long id);

//    @Query(value = "SELECT * FROM User u WHERE u.status = :status and u.name = :name",
//            nativeQuery = true)
//    User findUser(@Param("user") User user);
}
