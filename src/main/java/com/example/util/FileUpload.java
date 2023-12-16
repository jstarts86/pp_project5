package com.example.util;

import com.example.board.dao.BoardDao;
import com.example.board.model.BoardVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

@Component
public class FileUpload {

    private final BoardDao boardDao;

    @Autowired
    public FileUpload(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardVo uploadPhoto(HttpServletRequest request){
        String filename="";
        int sizeLimit=15*1024*1024;

        String realPath = request.getServletContext().getRealPath("resources/image");
        System.out.println(realPath);
        File dir = new File(realPath);
        if(!dir.exists()) dir.mkdir();

        BoardVo one=null;
        MultipartRequest multipartRequest=null;
        try{
            multipartRequest= new MultipartRequest(request,realPath,sizeLimit,"utf-8",new DefaultFileRenamePolicy());
            filename=multipartRequest.getFilesystemName("photo");
            one=new BoardVo();
            String id=multipartRequest.getParameter("id");
            if(id!=null&&!id.equals("")) one.setId(Integer.parseInt(id));
            one.setName(multipartRequest.getParameter("name"));
            one.setType(multipartRequest.getParameter("type"));
            one.setMaterial(multipartRequest.getParameter("material"));
            one.setColor(multipartRequest.getParameter("color"));
            one.setDescription(multipartRequest.getParameter("description"));
            one.setPurchasedDate(Date.valueOf(multipartRequest.getParameter("purchasedDate")));
            one.setPrice(Integer.parseInt(multipartRequest.getParameter("price")));

            if(id!=null && !id.equals("")){
                BoardVo vo= boardDao.getBoard(Integer.parseInt(id));
                String oldfilename=vo.getImage();
                if(filename!=null && oldfilename!=null)
                    FileUpload.deleteFile(request,oldfilename);
                else if(filename==null&&oldfilename!=null)
                    filename=oldfilename;
            }
            one.setImage(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return one;
    }

    public static void deleteFile(HttpServletRequest request, String filename) {
        String realPath = request.getServletContext().getRealPath("resources/image");
        File file=new File(realPath+"/"+filename);
        if(file.exists()) file.delete();
    }
}
