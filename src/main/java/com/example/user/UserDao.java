package com.example.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    SqlSessionTemplate sqlSession;
    public UserVo getUser(UserVo vo){
        return sqlSession.selectOne("User.getUser", vo);
    }
}