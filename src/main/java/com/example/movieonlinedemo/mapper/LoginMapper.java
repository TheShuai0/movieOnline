package com.example.movieonlinedemo.mapper;

import com.example.movieonlinedemo.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LoginMapper {
    void register(UserPo user);

    HashMap<String,Object> loginCheck(UserPo user);
}
