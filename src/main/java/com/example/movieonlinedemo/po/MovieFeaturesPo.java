package com.example.movieonlinedemo.po;

import java.util.HashSet;
import java.util.Set;

public class MovieFeaturesPo {
    private int score;
    private int favorites;
    private int views;
    private Set<String> types;

    public MovieFeaturesPo(int score, int favorites, int views, String type) {
        this.score = score;
        this.favorites = favorites;
        this.views = views;
        this.types = new HashSet<>();
        this.types.add(type);
    }

    public int getScore() {
        return score;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getViews() {
        return views;
    }

    public Set<String> getTypes() {
        return types;
    }



}
