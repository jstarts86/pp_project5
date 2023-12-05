package com.example.service;

import com.example.dao.BoardDao;
import com.example.dto.BoardDto;
import com.example.util.FileUpload;
import com.example.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardDao boardDao;
    private final FileUpload fileUpload;

    @Autowired
    public BoardService(BoardDao boardDao, FileUpload fileUpload) {
        this.boardDao = boardDao;
        this.fileUpload=fileUpload;
    }
    public BoardDto infoSimpleList(){
        List<BoardVo> voList= boardDao.getMenuList();
        BoardDto dto=new BoardDto();

        List<BoardDto.SimpleInfo> list=new ArrayList<>();
        for (BoardVo vo:voList) {
            list.add(BoardDto.SimpleInfo.builder()
                    .id(vo.getId())
                    .name(vo.getName())
                    .type(vo.getType())
                    .material(vo.getMaterial())
                    .image(vo.getImage())
                    .build());
        }
        dto.setSimpleInfoList(list);
        return dto;
    }

    public int insertMenu(HttpServletRequest request){
        BoardVo vo= fileUpload.uploadPhoto(request);
        return boardDao.insertMenu(vo);
    }

    public BoardDto info(int id) {
        BoardVo vo= boardDao.getMenu(id);
        BoardDto dto=new BoardDto();
        dto.setInfo(BoardDto.Info.builder()
                .id(vo.getId())
                .name(vo.getName())
                .material(vo.getMaterial())
                .type(vo.getType())
                .color(vo.getColor())
                .description(vo.getDescription())
                .purchasedDate(vo.getPurchasedDate())
                .price(vo.getPrice())
                .image(vo.getImage())
                .build());
        return dto;
    }

    public Object getVo(int id) {
        return boardDao.getMenu(id);
    }

    public int editMenu(HttpServletRequest request) {
        BoardVo vo= fileUpload.uploadPhoto(request);
        return boardDao.updateMenu(vo);
    }

    public int deleteMenu(HttpServletRequest request,int id) {
        BoardVo vo= boardDao.getMenu(id);
        FileUpload.deleteFile(request,vo.getImage());
        return boardDao.deleteMenu(id);
    }
}
