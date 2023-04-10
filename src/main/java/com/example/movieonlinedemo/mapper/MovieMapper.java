package com.example.movieonlinedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MovieMapper {
    List<HashMap<Object,Object>> getMovie();

    List<HashMap<Object,Object>> getMovieByType(String type);
    List<HashMap<Object,Object>> getMovieByName(String movieName);
    List<HashMap<Object,Object>> getMovieType();

    HashMap<String,Object> getMovieDetail(String movieId);

    List<HashMap<Object,Object>> getMovieUrl(String movieId);
}
