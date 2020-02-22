package com.lc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.lc.homepage.UserInfo;
import com.lc.homepage.service.IUserService;
import com.lc.homepage.vo.CreateUserRequest;
import com.lc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomepageUserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/create/user")
    public UserInfo createUser(@RequestBody CreateUserRequest request) {
        log.info("<homepage-user>: create user -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }

    @GetMapping("/get/user")
    public UserInfo getUserInfo(Long id) {
        log.info("<homepage-usre>: get usre -> {}", id);
        return userService.getUserInfo(id);
    }

    @GetMapping("/get/user/course")
    public UserCourseInfo getUserCourseInfo(Long id) {
        log.info("<homepage-user>: get user course info ->{}", id);
        return userService.getUserCourseInfo(id);
    }
}
