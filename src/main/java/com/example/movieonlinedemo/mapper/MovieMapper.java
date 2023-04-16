package com.example.movieonlinedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MovieMapper {

    List<HashMap<Object,Object>> getTJMovie();
    List<HashMap<Object,Object>> getHisTJMovie(int userId);
    List<HashMap<Object,Object>> getMovie();
    List<HashMap<Object,Object>> getAllMovie(int offset,int limit);

    List<HashMap<Object,Object>> getMovieByType(String type);
    List<HashMap<Object,Object>> getMovieByName(String movieName);
    List<HashMap<Object,Object>> getMovieType();

    HashMap<String,Object> getMovieDetail(String movieId);

    List<HashMap<Object,Object>> getMovieUrl(String movieId);

    List<HashMap<Object,Object>> getMovieRankByCollect(String type);

    List<HashMap<Object,Object>> getMovieRankByWatch(String type);

    List<HashMap<Object,Object>> getMovieRankByScore(String type);

    void deleteMovie(String movieId);

    int getMovieNumber();

    HashMap<Object, Object> getMovieById(int movieId);
}
