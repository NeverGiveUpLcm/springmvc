package com.itcast.springmvc.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

/**
 * @author lichunmiao
 * @date 2020/9/6
 */
public class User {
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @NumberFormat(pattern = "#,###.##")
    private Double salery;
}
