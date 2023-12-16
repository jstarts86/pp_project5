package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BoardDto {

    private List<SimpleInfo> simpleInfoList;
    private Info info;
    @Getter
    @Builder
    public static class Info{

        private int id;
        private String name;
        private String type;
        private String material;
        private String color;
        private String description;
        private Date purchasedDate;
        private int price;
        private String image;
    }
    @Builder
    @Getter
    public static class SimpleInfo{
        private int id;
        private String name;
        private String type;
        private String material;
        private String color;
        private String image;
    }
}
