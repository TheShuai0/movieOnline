package com.example.movieonlinedemo.po;

import lombok.Data;

@Data
public class UserPo {
    //用户名
    String userName;
    //登录密码
    String password;
    //用户昵称
    String Name;
    //用户年龄
    String age;
    //用户电话
    String phoneNumber;
    //用户邮箱
    String email;
    //用户权限
    String userRole;
}
