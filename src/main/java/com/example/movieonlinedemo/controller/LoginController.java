package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.po.UserPo;
import com.example.movieonlinedemo.service.LoginService;
import com.teradata.ec.common.model.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping(value = "/local/login", method = {RequestMethod.POST})
    public @ResponseBody
    ActionResult login(HttpServletRequest request){
        ActionResult ar = new ActionResult();
        String loginName = request.getParameter("userName");
        String password = request.getParameter("password");
        String userrole = request.getParameter("userrole");
        System.out.println("loginName"+loginName+"password"+password+"userrole:"+userrole);
        if (loginName.equals("admin")&&password.equals("123456")){
            ar.setSuccess(true);
        }else {
            ar.setSuccess(false);
        }
        return ar;
    }

    @RequestMapping(value = "/local/register", method = {RequestMethod.POST})
    public @ResponseBody
    ActionResult register(HttpServletRequest request){
        System.out.println("dsadsa");
        ActionResult ar = new ActionResult();
        UserPo user = new UserPo();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        System.out.println("user:"+user);
        return loginService.register(user);
    }

}
