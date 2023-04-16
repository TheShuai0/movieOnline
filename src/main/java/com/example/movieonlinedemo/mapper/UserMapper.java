package com.example.movieonlinedemo.mapper;

import com.example.movieonlinedemo.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {

    HashMap<String,Object> getUserData(String userName);
    HashMap<String,Object> getUserDetail(String userName);
    List<HashMap<Object,Object>> getUserCollect(Integer id);

    void addCollect(Integer id, String movieId);

    void deleteCollect(Integer id, String movieId);

    HashMap<Object, Object> getCollectStatus(Integer id, String movieId);

    void updateDes(Integer id, String des);

    void addUserScore(Integer userId, String movieId,Float userScore);

    void addHistory(Integer id, String movieId);

    HashMap<Object, Object> getHistory(Integer id, String movieId);

    void updateHistory(Integer id, String movieId);

    Float getScore(Integer id, String movieId);

    void changeUserInfo(String userId, String age, String email, String phone);

    List<HashMap<Object,Object>> getMovieHistory(Integer id);

    void updatePic(String fileName, int id);
}
