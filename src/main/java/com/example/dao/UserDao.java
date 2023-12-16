package com.example.dao;

import com.example.vo.BoardVo;
import com.example.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class UserDao {
    @Autowired
    SqlSession sqlSession;

    public UserVo getUser(UserVo vo) {
        return sqlSession.selectOne("User.getUser", vo);  // if does not exist, returns null
    }

}