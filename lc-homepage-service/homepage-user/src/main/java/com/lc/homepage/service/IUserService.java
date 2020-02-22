package com.lc.homepage.service;

import com.lc.homepage.UserInfo;
import com.lc.homepage.vo.CreateUserRequest;
import com.lc.homepage.vo.UserCourseInfo;

public interface IUserService {
    UserInfo createUser(CreateUserRequest request);

    UserInfo getUserInfo(Long id);

    UserCourseInfo getUserCourseInfo(Long id);
}
