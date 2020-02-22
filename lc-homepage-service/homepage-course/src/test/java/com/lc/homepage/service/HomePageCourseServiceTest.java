package com.lc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.lc.homepage.Application;
import com.lc.homepage.CourseInfosRequest;
import com.lc.homepage.dao.HomepageCourseDao;
import com.lc.homepage.entity.HomepageCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HomePageCourseServiceTest {
    @Autowired
    private HomepageCourseDao courseDao;
    @Autowired
    private ICourseService courseService;

    @Test
    public void testCreateCourseInfo() {
        HomepageCourse course1 = new HomepageCourse(
                "JDK12解读",
                0,
                "http://www.imooc.com/1",
                "解读新版本特性"
        );

        HomepageCourse course2 = new HomepageCourse(
                "SpringCloud微服务",
                1,
                "http://www.imooc.com/2",
                "SpringCloud微服务"
        );
        System.out.println(courseDao.saveAll(Arrays.asList(course1,course2)).size());
    }

    @Test
    public void testGetCourseInfo(){
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(6L)
        ));

        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(8L)
        ));
    }

    @Test
    public void testGetCourseInfos(){
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfos(new CourseInfosRequest(Arrays.asList(9L,8L)))
        ));
    }
}
