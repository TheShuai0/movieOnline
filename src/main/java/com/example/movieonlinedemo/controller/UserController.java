package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

}
