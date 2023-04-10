package com.example.movieonlinedemo.serviceImpl;

import com.example.movieonlinedemo.po.UserPo;
import com.example.movieonlinedemo.mapper.LoginMapper;
import com.example.movieonlinedemo.service.LoginService;


import com.teradata.ec.common.model.ActionResult;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ActionResult register(UserPo user) {
        ActionResult ar = new ActionResult();
        try {
            loginMapper.register(user);
            ar.setSuccess(true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @Override
    public ActionResult loginCheck(UserPo user) {
        ActionResult ar = new ActionResult();
        try {
            HashMap<String, Object> getUser = loginMapper.loginCheck(user);
            if (getUser != null) {
                String token = UUID.randomUUID().toString().replaceAll("-", "") + "";
                System.out.println("设置token:"+token);
                ValueOperations valueOperations = redisTemplate.opsForValue();
                valueOperations.set("token", token, 30, TimeUnit.MINUTES);
                valueOperations.set(token, getUser, 30, TimeUnit.MINUTES);
                ar.setSuccess(true);
                ar.setData(token);
            } else {
                ar.setSuccess(false);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }
}
