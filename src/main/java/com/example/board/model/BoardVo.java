package com.example.board.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardVo {

    private int id;
    private String name;
    private String type;
    private String material;
    private String color;
    private String description;
    private Date purchasedDate;
    private int price;
    //private int last_worn;
    private String image;
}
