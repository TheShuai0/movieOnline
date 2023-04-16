package com.example.movieonlinedemo.serviceImpl;

import com.example.movieonlinedemo.mapper.MovieMapper;
import com.example.movieonlinedemo.service.MovieService;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
    public ActionResult getAllMovie(String page) {
        ActionResult ar = new ActionResult();
        ar.setData(movieMapper.getAllMovie(Integer.parseInt(page)*10,10));
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

    @Override
    public ActionResult getMovieRank(String type, String how) {
        ActionResult ar = new ActionResult();
        List list = new ArrayList<Object>();
        if(type == null || type.equals("全部"))
            type = "";
        list.add(movieMapper.getMovieRankByCollect(type));
        list.add(movieMapper.getMovieRankByWatch(type));
        list.add(movieMapper.getMovieRankByScore(type));
        ar.setData(list);
        return ar;
    }

    @Override
    public ActionResult deleteMovie(String movieId) {
        movieMapper.deleteMovie(movieId);
        return null;
    }

    @Override
    public ActionResult usePython() {

        int beforeNumber = movieMapper.getMovieNumber();
        ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\My PC\\Desktop\\movieOnline\\movie_select_py\\main\\download_pic.py");
        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int afterNumber = movieMapper.getMovieNumber();
        return null;
    }
}
