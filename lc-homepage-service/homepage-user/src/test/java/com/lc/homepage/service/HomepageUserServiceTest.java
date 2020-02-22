package com.lc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.lc.homepage.Application;
import com.lc.homepage.dao.HomepageUserCourseDao;
import com.lc.homepage.entity.HomepageUserCourse;
import com.lc.homepage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class HomepageUserServiceTest {
    @Autowired
    private IUserService userService;

    @Autowired
    private HomepageUserCourseDao userCourseDao;

    @Test
    //@Transactional
    public void testCreateUser() {
        //{"email":"qinyi@imooc.com","id":6,"username":"qinyi"}
        System.out.println(JSON.toJSONString(userService.createUser(new CreateUserRequest(
                "qinyi", "qinyi@imooc.com"
        ))));
    }

    @Test
    public void testGetUserInfo() {
        //{"email":"qinyi@imooc.com","id":7,"username":"qinyi"}
        System.out.println(JSON.toJSONString(userService.getUserInfo(7L)));
    }

    @Test
    public void testCreateHomepageUserCourse() {
        HomepageUserCourse course1 = new HomepageUserCourse();
        course1.setUserId(7L);
        course1.setCourseId(8L);

        HomepageUserCourse course2 = new HomepageUserCourse();
        course2.setUserId(7L);
        course2.setCourseId(9L);

        System.out.println(userCourseDao.saveAll(Arrays.asList(course1,course2)));
    }

    @Test
    public void testGetUserCourseInfo(){
        System.out.println(JSON.toJSONString(userService.getUserCourseInfo(7L)));
    }
}
