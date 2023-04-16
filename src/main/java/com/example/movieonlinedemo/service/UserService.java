package com.example.movieonlinedemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teradata.ec.common.model.ActionResult;

public interface UserService {
    ActionResult getUserData(String token) ;
    ActionResult getUserDetail(String token) ;

    ActionResult getUserCollect(String token);

    ActionResult addCollect(String token, String movieId);
    ActionResult deleteCollect(String token, String movieId);

    ActionResult getCollectStatus(String token, String movieId);

    ActionResult updateDes(String token, String des);

    ActionResult addUserScore(String token, String movieId,String userScore);

    ActionResult addHistory(String token, String movieId);

    ActionResult getScore(String token, String movieId);

    ActionResult changeUserInfo(String userId, String age, String email, String phone);

    ActionResult getMovieHistory(String token);

    void updatePic(String fileName);

}
