package com.example.movieonlinedemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teradata.ec.common.model.ActionResult;

public interface UserService {
    ActionResult getUserData(String token) ;
    ActionResult getUserDetail(String token) ;

    ActionResult getUserCollect(String token);
}
