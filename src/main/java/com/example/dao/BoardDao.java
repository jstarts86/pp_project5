package com.example.dao;

import com.example.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDao {
    private final String BOARD_INSERT="insert into MENU (name,type,material,color,description,purchasedDate,price,image) values (?,?,?,?,?,?,?,?)";
    private final String BOARD_UPDATE = "update MENU set name=?, type=?, material=?, color=?, description=?, purchasedDate=?, price=?, image=?, modDate=current_timestamp where id=?";
    private final String BOARD_DELETE="delete from MENU where id=?";

    private final String BOARD_GET = "select * from MENU where id=?";

    private final String BOARD_LIST="select * from MENU order by id desc";

    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int insertMenu(BoardVo vo){
        return template.update(BOARD_INSERT,new Object[]{vo.getName(),vo.getType(),vo.getMaterial(),vo.getColor(),vo.getDescription(),vo.getPurchasedDate(),vo.getPrice(),vo.getImage()});
    }

    public int deleteMenu(int id){
        return template.update(BOARD_DELETE,
                new Object[]{id});
    }

    public int updateMenu(BoardVo vo){
        return template.update(BOARD_UPDATE,
                new Object[]{vo.getName(),vo.getType(),vo.getMaterial(),vo.getColor(),vo.getDescription(),vo.getPurchasedDate(),vo.getPrice(),vo.getImage()});
    }

    public BoardVo getMenu(int id){
        return template.queryForObject(BOARD_GET,
                new Object[]{id},
                new BeanPropertyRowMapper<BoardVo>(BoardVo.class));
    }

    public List<BoardVo> getMenuList(){
        return template.query(BOARD_LIST, new RowMapper<BoardVo>() {
            @Override
            public BoardVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardVo data=new BoardVo();
                data.setId(rs.getInt("id"));
                data.setName(rs.getString("name"));
                data.setType(rs.getString("type"));
                data.setMaterial(rs.getString("material"));
                data.setColor(rs.getString("color"));
                data.setDescription(rs.getString("description"));
                data.setPurchasedDate(rs.getDate("purchasedDate"));
                data.setPrice(rs.getInt("price"));
                data.setImage(rs.getString("image"));
                return data;
            }
        });
    }
}
