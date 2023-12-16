package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    UserDao userDao;
    public UserVo getUser(UserVo vo){
        return userDao.getUser(vo);
    }
}
