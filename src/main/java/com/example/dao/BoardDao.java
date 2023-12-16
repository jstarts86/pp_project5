package com.example.dao;

import com.example.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    @Autowired
    SqlSession sqlSession;
    public int insertMenu(BoardVo vo) {
        int result = sqlSession.insert("Board.insertBoard", vo);
        return result;
    }

    public int updateMenu(BoardVo vo) {
        int result = sqlSession.update("Board.updateBoard", vo);
        return result;
    }

    public int deleteMenu(int vo) {
        int result = sqlSession.delete("Board.deleteBoard", vo);
        return result;
    }

    public BoardVo getMenu(int id){
        return sqlSession.selectOne("Board.getBoard", id);
    }

    public List<BoardVo> getMenuList(){
        return sqlSession.selectList("Board.getBoardList");
    }

    public String getImage(int id){
        return sqlSession.selectOne("Board.getImage", id);
    }
}
