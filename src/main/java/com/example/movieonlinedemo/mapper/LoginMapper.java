package com.example.movieonlinedemo.mapper;

import com.example.movieonlinedemo.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    void register(UserPo user);
}
