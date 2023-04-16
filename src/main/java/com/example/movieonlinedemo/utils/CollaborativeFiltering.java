package com.example.movieonlinedemo.utils;

import com.example.movieonlinedemo.mapper.MovieMapper;
import com.example.movieonlinedemo.po.MovieFeaturesPo;
import com.example.movieonlinedemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class CollaborativeFiltering {
    @Autowired
    MovieMapper movieMapper;
    private Connection conn;

    public CollaborativeFiltering() throws SQLException {
        // Connect to MySQL database
        String url = "jdbc:mysql://localhost:3306/movieonline?serverTimezone=PRC&characterEncoding=utf-8&useSSL=false";
        String user = "root";
        String password = "123456";
        conn = DriverManager.getConnection(url, user, password);
    }

    public List<HashMap<Object,Object>> getRecommendations(int userId) throws SQLException {
        List<HashMap<Object,Object>> recommendations = new ArrayList<>();

        // 获取指定用户给过的电影评分，并返回一个包含电影 ID 和评分的映射。
        Map<Integer, Integer> userRatings = getUserRatings(userId);

        // 获取所有电影的特征（得分、收藏数、观看数和类型），并返回一个包含电影 ID 和其特征的映射。
        Map<Integer, MovieFeaturesPo> movieFeatures = getMovieFeatures();

        // 计算每个电影与用户历史记录的相似度，并返回一个包含电影 ID 和相似度的映射。
        Map<Integer, Double> movieSimilarities = computeMovieSimilarities(userRatings, movieFeatures);

        // 将电影按照与用户历史记录的相似度从高到低排序，并返回一个包含电影 ID 和相似度的映射的列表。
        List<Map.Entry<Integer, Double>> sortedMovies = sortMoviesBySimilarity(movieSimilarities);

        // 从排序后的电影列表中获取前10个电影
        for (int i = 0; i < 10 && i < sortedMovies.size(); i++) {
            int movieId = sortedMovies.get(i).getKey();
            recommendations.add(getMovieTitle(movieId));
        }
        System.out.println(recommendations);
        return recommendations;
    }

//获取指定用户给过的电影评分，并返回一个包含电影 ID 和评分的映射。

    private Map<Integer, Integer> getUserRatings(int userId) throws SQLException {
        Map<Integer, Integer> ratings = new HashMap<>();

        String sql = "SELECT MOVIE_ID, USER_SCORE FROM movie_history WHERE USER_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        /*List<HashMap<Object,Object>> movieList = movieMapper.getHisTJMovie(userId);
        for (HashMap<Object,Object> map : movieList){
            int movieId = (int) map.get("MOVIE_ID");
            System.out.println(map.get("USER_SCORE"));
            int rating =  (int)map.get("USER_SCORE");
            ratings.put(movieId, rating);
        }*/

        while (rs.next()) {
            int movieId = rs.getInt("MOVIE_ID");
            int rating = rs.getInt("USER_SCORE");
            ratings.put(movieId, rating);
        }

        return ratings;
    }

//获取所有电影的特征（得分、收藏数、观看数和类型），并返回一个包含电影 ID 和其特征的映射。

    private Map<Integer, MovieFeaturesPo> getMovieFeatures() throws SQLException {
        Map<Integer, MovieFeaturesPo> features = new HashMap<>();

        String sql = "SELECT id, SCORE, COLLECT, WATCH, TYPES FROM movie where  DES != '无'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        /*List<HashMap<Object,Object>> movieList = movieMapper.getTJMovie();
        for (HashMap<Object,Object> map : movieList){
            int id = (int) map.get("id");
            int SCORE = (int) map.get("SCORE");
            int COLLECT = (int) map.get("COLLECT");
            int WATCH = (int) map.get("WATCH");
            String TYPES = (String) map.get("TYPES");
            features.put(id, new MovieFeaturesPo(SCORE, COLLECT, WATCH, TYPES));
        }*/

        while (rs.next()) {
            int id = rs.getInt("id");
            int SCORE = rs.getInt("SCORE");
            int COLLECT = rs.getInt("COLLECT");
            int WATCH = rs.getInt("WATCH");
            String TYPES = rs.getString("TYPES");
            features.put(id, new MovieFeaturesPo(SCORE, COLLECT, WATCH, TYPES));
        }

        return features;
    }

    //计算每个电影与用户历史记录的相似度，并返回一个包含电影 ID 和相似度的映射。
    private Map<Integer, Double> computeMovieSimilarities(Map<Integer, Integer> userRatings,
                                                          Map<Integer, MovieFeaturesPo> movieFeatures) {
        Map<Integer, Double> similarities = new HashMap<>();

        for (int movieId : movieFeatures.keySet()) {
            //计算相似度
            double similarity = computeSimilarity(userRatings, movieFeatures, movieId);
            similarities.put(movieId, similarity);
        }
        return similarities;
    }

    //给定电影与用户观看历史的相似度。
    private double computeSimilarity(Map<Integer, Integer> userRatings,
                                     Map<Integer, MovieFeaturesPo> movieFeatures,
                                     int movieId) {
        double numerator = 0.0;
        double denominator = 0.0;

    //遍历用户观看过的所有电影，并计算给定电影与这些电影的余弦相似度
        for (int ratedMovieId : userRatings.keySet()) {
            if (ratedMovieId == movieId) {
                continue;
            }
            double similarity = computeCosineSimilarity(movieFeatures.get(movieId),
                    movieFeatures.get(ratedMovieId));
    //将相似度乘以用户对该电影的评分，并将结果累加到 numerator 变量中。
            numerator += similarity * userRatings.get(ratedMovieId);
            denominator += similarity;
        }
        return denominator == 0 ? 0 : numerator / denominator;
    }

    private double computeCosineSimilarity(MovieFeaturesPo movie1, MovieFeaturesPo movie2) {
        double score1 = movie1.getScore();
        double score2 = movie2.getScore();
        double favorites1 = movie1.getFavorites();
        double favorites2 = movie2.getFavorites();
        double views1 = movie1.getViews();
        double views2 = movie2.getViews();
        Set<String> types1 = movie1.getTypes();
        Set<String> types2 = movie2.getTypes();

        double dotProduct = score1 * score2 + favorites1 * favorites2 + views1 * views2;
        double magnitude1 = Math.sqrt(score1 * score1 + favorites1 * favorites1 + views1 * views1 + types1.size());
        double magnitude2 = Math.sqrt(score2 * score2 + favorites2 * favorites2 + views2 * views2 + types2.size());

        return dotProduct / (magnitude1 * magnitude2);
    }

    //根据相似度从高到低排序。
    private List<Map.Entry<Integer, Double>> sortMoviesBySimilarity(Map<Integer, Double> movieSimilarities) {
        List<Map.Entry<Integer, Double>> sortedMovies = new ArrayList<>(movieSimilarities.entrySet());
        Collections.sort(sortedMovies, (m1, m2) -> Double.compare(m2.getValue(), m1.getValue()));
        return sortedMovies;
    }

    private HashMap<Object,Object> getMovieTitle(int movieId) throws SQLException {
        String sql = "SELECT NAME FROM movie WHERE id = ? and  DES != '无'";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, movieId);
        ResultSet rs = stmt.executeQuery();

        HashMap<Object,Object> movie = movieMapper.getMovieById(movieId);

        if (rs.next()) {
            return movie;
        } else {
            return null;
        }
    }
}


