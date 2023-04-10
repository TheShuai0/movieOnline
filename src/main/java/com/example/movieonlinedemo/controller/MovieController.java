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
    @RequestMapping(value = "/movie/getMovie", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovie(HttpServletRequest request){
        return movieService.getMovie();
    }

    @RequestMapping(value = "/movie/getMovieByType", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovieByType(HttpServletRequest request){
        String type = request.getParameter("type");
        return movieService.getMovieByType(type);
    }

    @RequestMapping(value = "/movie/getMovieByName", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovieByName(HttpServletRequest request){
        String movieName = request.getParameter("movieName");
        return movieService.getMovieByName(movieName);
    }
    @RequestMapping(value = "/movie/getMovieType", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovieType(HttpServletRequest request){
        return movieService.getMovieType();
    }

    @RequestMapping(value = "/movie/getMovieDetail", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovieDetail(HttpServletRequest request){
        String movieId = request.getParameter("movieId");
        return movieService.getMovieDetail(movieId);
    }

    @RequestMapping(value = "/movie/getMovieUrl", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMovieUrl(HttpServletRequest request){
        String movieId = request.getParameter("movieId");
        return movieService.getMovieUrl(movieId);
    }

}
