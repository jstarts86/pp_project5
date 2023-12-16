package com.example.board.service;

import com.example.board.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.board.dao.BoardDao;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardDao boardDao;

    public int insertBoard(BoardVo vo) {
        return boardDao.insertBoard(vo);
    }


    public int deleteBoard(int seq) {
        return boardDao.deleteBoard(seq);
    }

    public int updateBoard(BoardVo vo) {
        return boardDao.updateBoard(vo);
    }

    public BoardVo getBoard(int seq) {
        return boardDao.getBoard(seq);
    }

    public List<BoardVo> getBoardList() {
        return boardDao.getBoardList();
    }

}
