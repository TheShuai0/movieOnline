package com.example.movieonlinedemo.serviceImpl;

import com.example.movieonlinedemo.mapper.UserMapper;
import com.example.movieonlinedemo.po.UserPo;
import com.example.movieonlinedemo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ActionResult getUserData(String token)  {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,String> user = (HashMap) valueOperations.get(token);
        ar.setData(userMapper.getUserData(user.get("USER_NAME")));

        return ar;
    }

    @Override
    public ActionResult getUserDetail(String token)  {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,String> user = (HashMap) valueOperations.get(token);
        ar.setData(userMapper.getUserDetail(user.get("USER_NAME")));

        return ar;
    }


    public ActionResult getUserCollect(String token)  {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        ar.setData(userMapper.getUserCollect((Integer)user.get("ID")));

        return ar;
    }
}
