package com.example.board.dao;

import com.example.board.model.BoardVo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class BoardDao {
    @Autowired
    SqlSession sqlSession;

    public int insertBoard(BoardVo vo) {
        int result = sqlSession.insert("Board.insertBoard", vo);
        return result;
    }

    public int deleteBoard(int id) {
        return sqlSession.delete("Board.deleteBoard", id);
    }

    public int updateBoard(BoardVo vo) {
        return sqlSession.update("Board.updateBoard", vo);
    }

    public BoardVo getBoard(int id) {
        BoardVo one = sqlSession.selectOne("Board.˚getBoard", id);
        return one;
    }
    public List<BoardVo> getBoardList() {
        List<BoardVo> list = sqlSession.selectList("Board.getBoardList");
        return list;
    }


}
