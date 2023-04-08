package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.service.MovieService;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @RequestMapping(value = "/local/getMovie", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovie(HttpServletRequest request){
        System.out.println("123456"+movieService.getMovie());
        return movieService.getMovie();
    }
}
