package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.po.UserPo;
import com.example.movieonlinedemo.service.LoginService;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/local/login", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult login(HttpServletRequest request){
        ActionResult ar = new ActionResult();
        try {
            UserPo user = new UserPo();
            user.setUserName(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setUserRole(request.getParameter("userRole"));
            System.out.println("user"+user);
            //查询用户是否存在
            return loginService.loginCheck(user);
        }catch (Exception e){
            System.out.println("登录错误");
            return ar;
        }
    }

    @RequestMapping(value = "/local/register", method = {RequestMethod.POST})
    public @ResponseBody
    ActionResult register(HttpServletRequest request){
        UserPo user = new UserPo();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setUserRole("用户");
        System.out.println(this.getClass()+"user:"+user);
        return loginService.register(user);
    }

    @RequestMapping(value = "/local/trytry", method = {RequestMethod.POST})
    public @ResponseBody
    String trytry(HttpServletRequest request){

        return "你好呀";
    }



}
