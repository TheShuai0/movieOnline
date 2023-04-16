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

    @Override
    public ActionResult addCollect(String token, String movieId) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        userMapper.addCollect((Integer)user.get("ID"),movieId);
        return null;
    }

    @Override
    public ActionResult deleteCollect(String token, String movieId) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        userMapper.deleteCollect((Integer)user.get("ID"),movieId);
        return null;
    }

    @Override
    public ActionResult getCollectStatus(String token, String movieId) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        HashMap<Object,Object> collect = userMapper.getCollectStatus((Integer)user.get("ID"),movieId);
        if(collect == null || collect.equals("")){
            ar.setData("false");
        }else {
            ar.setData("true");
        }
        return ar;
    }

    @Override
    public ActionResult updateDes(String token, String des) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        userMapper.updateDes((Integer)user.get("ID"),des);
        return null;
    }

    @Override
    public ActionResult addUserScore(String token, String movieId,String userScore) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        userMapper.addUserScore((Integer)user.get("ID"),movieId,Float.parseFloat(userScore) );
        return null;
    }

    @Override
    public ActionResult addHistory(String token, String movieId) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        if(userMapper.getHistory((Integer)user.get("ID"),movieId)!=null){
            userMapper.updateHistory((Integer)user.get("ID"),movieId);
        }else {
            userMapper.addHistory((Integer) user.get("ID"), movieId);
        }
        return null;
    }

    @Override
    public ActionResult getScore(String token, String movieId) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        ar.setData(userMapper.getScore((Integer) user.get("ID"), movieId));
        return ar;
    }

    @Override
    public ActionResult changeUserInfo(String userId, String age, String email, String phone) {
        userMapper.changeUserInfo(userId,age,email,phone);
        return null;
    }

    @Override
    public ActionResult getMovieHistory(String token) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        ar.setData(userMapper.getMovieHistory((Integer) user.get("ID")));
        return ar;
    }

    @Override
    public void updatePic(String fileName) {
        userMapper.updatePic(fileName,1);
    }



}
