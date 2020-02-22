package com.lc.homepage.service;

import com.lc.homepage.CourseInfo;
import com.lc.homepage.CourseInfosRequest;

import java.util.List;

public interface ICourseService {
    CourseInfo getCourseInfo(Long id);

    List<CourseInfo> getCourseInfos(CourseInfosRequest request);
}
