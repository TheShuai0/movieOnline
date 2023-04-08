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
}
