package com.lc.homepage.service.impl;

import com.lc.homepage.CourseInfo;
import com.lc.homepage.CourseInfosRequest;
import com.lc.homepage.dao.HomepageCourseDao;
import com.lc.homepage.entity.HomepageCourse;
import com.lc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private HomepageCourseDao homepageCourseDao;

    @Override
    public CourseInfo getCourseInfo(Long id) {
        Optional<HomepageCourse> course = homepageCourseDao.findById(id);
        /**
         * course如果为空就返回空对象
         */
        return buildCourseInfo(course.orElse(HomepageCourse.invalid()));
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        if (CollectionUtils.isEmpty(request.getIds())) {
            return Collections.emptyList();
        }

        List<HomepageCourse> courses = homepageCourseDao.findAllById(request.getIds());

        return courses.stream().map(x -> buildCourseInfo(x)).collect(Collectors.toList());
    }


    /**
     * 根据数据记录构造对象信息
     *
     * @param course
     * @return
     */
    private CourseInfo buildCourseInfo(HomepageCourse course) {
        return CourseInfo.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType() == 0 ? "免费课程" : "实战课程")
                .courseIcon(course.getCourseIcon())
                .courseIntro(course.getCourseIntro())
                .build();
    }

}
