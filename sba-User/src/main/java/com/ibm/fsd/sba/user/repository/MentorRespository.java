package com.ibm.fsd.sba.user.repository;

import com.ibm.fsd.sba.user.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRespository extends JpaRepository<Mentor, Integer> {
}
