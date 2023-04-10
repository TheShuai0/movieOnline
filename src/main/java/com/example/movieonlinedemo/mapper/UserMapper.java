package com.example.movieonlinedemo.mapper;

import com.example.movieonlinedemo.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {

    HashMap<String,Object> getUserData(String userName);
    HashMap<String,Object> getUserDetail(String userName);
    List<HashMap<String,Object>> getUserCollect(Integer id);
}
