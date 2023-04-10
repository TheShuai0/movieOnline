package com.example.movieonlinedemo.serviceImpl;

import com.example.movieonlinedemo.mapper.MovieMapper;
import com.example.movieonlinedemo.service.MovieService;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Override
    public ActionResult getMovie() {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getMovie());
        return ar;
    }

    @Override
    public ActionResult getMovieByType(String type) {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getMovieByType(type));
        return ar;
    }

    @Override
    public ActionResult getMovieByName(String type) {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getMovieByName(type));
        return ar;
    }

    @Override
    public ActionResult getMovieType() {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getMovieType());
        return ar;
    }

    @Override
    public ActionResult getMovieDetail(String movieId) {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getMovieDetail(movieId));
        return ar;
    }

    public ActionResult getMovieUrl(String movieId) {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getMovieUrl(movieId));
        return ar;
    }
}
