package com.lc.homepage.service.impl;

import com.lc.homepage.CourseInfo;
import com.lc.homepage.CourseInfosRequest;
import com.lc.homepage.UserInfo;
import com.lc.homepage.client.CourseClient;
import com.lc.homepage.dao.HomepageUserCourseDao;
import com.lc.homepage.dao.HomepageUserDao;
import com.lc.homepage.entity.HomepageUser;
import com.lc.homepage.entity.HomepageUserCourse;
import com.lc.homepage.service.IUserService;
import com.lc.homepage.vo.CreateUserRequest;
import com.lc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private HomepageUserDao homepageUserDao;
    @Autowired
    private HomepageUserCourseDao homepageUserCourseDao;
    @Autowired
    private CourseClient courseClient;

    @Override
    public UserInfo createUser(CreateUserRequest request) {
        if (!request.validate()) {
            return UserInfo.invalid();
        }

        HomepageUser oldUser = homepageUserDao.findByUsername(request.getUsername());
        if (oldUser != null) {
            return UserInfo.invalid();
        }
        HomepageUser newUser = homepageUserDao.save(new HomepageUser(request.getUsername(), request.getEmail()));
        return new UserInfo(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if (!user.isPresent()) {
            return UserInfo.invalid();
        }
        HomepageUser curUser = user.get();
        return new UserInfo(curUser.getId(), curUser.getUsername(), curUser.getEmail());
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {
        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if (!user.isPresent()) {
            return UserCourseInfo.invalid();
        }
        HomepageUser homepageUser = user.get();
        UserInfo userInfo = new UserInfo(homepageUser.getId(), homepageUser.getUsername(), homepageUser.getEmail());
        List<HomepageUserCourse> userCourses = homepageUserCourseDao.findAllByUserId(id);
        if (CollectionUtils.isEmpty(userCourses)) {
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }
        CourseInfo a = courseClient.getCourseInfo(8L);
        List<CourseInfo> courseInfos = courseClient.getCourseInfos(new CourseInfosRequest(
                userCourses.stream().map(x -> x.getCourseId()).collect(Collectors.toList())
        ));
        return new UserCourseInfo(userInfo, courseInfos);
    }
}
