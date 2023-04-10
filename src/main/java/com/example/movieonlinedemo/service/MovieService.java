package com.example.movieonlinedemo.service;

import com.teradata.ec.common.model.ActionResult;

public interface  MovieService {
    ActionResult getMovie();

    ActionResult getMovieByType(String type);

    ActionResult getMovieByName(String type);


    ActionResult getMovieType();

    ActionResult getMovieDetail(String movieId);
    ActionResult getMovieUrl(String movieId);
}
