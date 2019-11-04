package com.ibm.fsd.sba.user.repository;

import com.ibm.fsd.sba.user.entity.MentorCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorCalendarRespository extends JpaRepository<MentorCalendar, Integer> {
    List<MentorCalendar> findByMid(Long mid);
}
