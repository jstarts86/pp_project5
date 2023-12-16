package com.example.service;

import com.example.dao.UserDao;
import com.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    UserDao userDao;
    public UserVo getUser(UserVo vo) {
        return userDao.getUser(vo);
    }
}
