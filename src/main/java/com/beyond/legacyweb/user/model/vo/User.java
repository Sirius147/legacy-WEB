package com.beyond.legacyweb.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int no;
    private String id;
    private String password;
    private String role;
    private String name;

    private String phone;

    private String email;

    private String address;

    private String hobby;

    private String status;

    private Date enrollDate;

    private Date modifyDate;

}
