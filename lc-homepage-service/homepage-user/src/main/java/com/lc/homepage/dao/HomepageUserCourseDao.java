package com.lc.homepage.dao;

import com.lc.homepage.entity.HomepageUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomepageUserCourseDao extends JpaRepository<HomepageUserCourse, Long> {
    List<HomepageUserCourse> findAllByUserId(Long userId);
}
