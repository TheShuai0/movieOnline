package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
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
    ActionResult updatePic(HttpServletRequest request,@RequestParam("file") MultipartFile uploadFile) throws ServletException, IOException {
            Part filePart = request.getPart("avatar"); // avatar是前端上传文件的字段名
            InputStream fileContent = filePart.getInputStream(); // 获取文件流
        System.out.println("dasdasdadasdsa");
            // 处理文件流，保存头像等操作
            return new ActionResult(true, "上传头像成功");
    }



    @RequestMapping(value = "/user/addUserScore", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addUserScore(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getParameter("token");
        String movieId = request.getParameter("movieId");
        String userScore = request.getParameter("userScore");

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

}
