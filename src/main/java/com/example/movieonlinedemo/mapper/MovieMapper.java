package com.example.movieonlinedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MovieMapper {
    List<HashMap<Object,Object>> getMovie();
}
