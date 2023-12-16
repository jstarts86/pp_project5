package com.example.board.service;

import com.example.board.model.BoardVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface BoardService {
    public int insertBoard(BoardVo vo);
    public int deleteBoard(int seq);
    public int updateBoard(BoardVo vo);
    public BoardVo getBoard(int seq);
    public List<BoardVo> getBoardList();
}
