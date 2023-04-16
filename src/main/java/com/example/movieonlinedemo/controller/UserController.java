package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.service.UserService;

import com.example.movieonlinedemo.utils.CollaborativeFiltering;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
   @Autowired
    private CollaborativeFiltering collaborativeFiltering;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping(value = "/user/getUserData", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getUserData(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        return userService.getUserData(token);
    }

    @RequestMapping(value = "/user/getUserDetail", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getUserDetail(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        return userService.getUserDetail(token);
    }

    @RequestMapping(value = "/user/getUserCollect", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getUserCollect(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        return userService.getUserCollect(token);
    }

    @RequestMapping(value = "/user/addCollect", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addCollect(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");
        return userService.addCollect(token,movieId);
    }

    @RequestMapping(value = "/user/deleteCollect", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult deleteCollect(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");
        return userService.deleteCollect(token,movieId);
    }

    @RequestMapping(value = "/user/getCollectStatus", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getCollectStatus(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");
        return userService.getCollectStatus(token,movieId);
    }

    @RequestMapping(value = "/user/updateDes", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult updateDes(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String des = request.getParameter("des");
        return userService.updateDes(token,des);
    }

    @RequestMapping(value = "/user/updatePic", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult updatePic(@RequestParam("file") MultipartFile avatar) throws ServletException, IOException {
        String fileName = avatar.getOriginalFilename();
        // 设置文件存储的路径
        String filePath = "C:/Users/My PC/Desktop/movieOnline/movieOnline_vue/src/assets/user_img/" + fileName;
        userService.updatePic(fileName);
        System.out.println(fileName);
        // 将文件保存到本地文件系统中
        File dest = new File(filePath);
        avatar.transferTo(dest);
        return null;
    }



    @RequestMapping(value = "/user/addUserScore", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addUserScore(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");
        String userScore = request.getParameter("userScore");
        System.out.println("movie:"+userScore);
        return userService.addUserScore(token,movieId,userScore);
    }
    @RequestMapping(value = "/user/addHistory", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addHistory(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");

        return userService.addHistory(token,movieId);
    }


    @RequestMapping(value = "/user/getScore", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getScore(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");

        return userService.getScore(token,movieId);
    }

    @RequestMapping(value = "/user/changeUserInfo", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult changeUserInfo(HttpServletRequest request) throws JsonProcessingException {
        String userId = request.getParameter("userId");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        return userService.changeUserInfo(userId,age,email,phone);
    }



    @RequestMapping(value = "/user/getMovieHistory", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovieHistory(HttpServletRequest request) throws JsonProcessingException {

        String token = request.getParameter("token");
        return userService.getMovieHistory(token);
    }

    @RequestMapping(value = "/user/tuijian", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult tuijian(HttpServletRequest request) throws JsonProcessingException, SQLException {
        ActionResult ar = new ActionResult();
        String token = request.getParameter("token");
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        ar.setData(collaborativeFiltering.getRecommendations((Integer) user.get("ID")));

        return ar;
    }
}
