package com.example.movieonlinedemo.service;

import com.example.movieonlinedemo.po.UserPo;
import com.teradata.ec.common.model.ActionResult;

public interface LoginService {
    ActionResult loginCheck(UserPo user);

    ActionResult register(UserPo user);


}
