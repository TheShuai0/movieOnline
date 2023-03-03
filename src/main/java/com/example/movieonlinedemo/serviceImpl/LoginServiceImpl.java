package com.example.movieonlinedemo.serviceImpl;

import com.example.movieonlinedemo.po.UserPo;
import com.example.movieonlinedemo.mapper.LoginMapper;
import com.example.movieonlinedemo.service.LoginService;


import com.teradata.ec.common.model.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    public ActionResult register(UserPo user){
        ActionResult ar = new ActionResult();
            loginMapper.register(user);
            ar.setSuccess(true);
        return ar;
    }
}
