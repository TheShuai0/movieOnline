package com.example.movieonlinedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ForumMapper {
    List<HashMap<Object,Object>> getAllForum();

    List<HashMap<Object,Object>> getForumByTimeAsc();

    List<HashMap<Object,Object>> getForumByTimeDesc();
    List<HashMap<Object,Object>> getForumByReplyAsc();
    List<HashMap<Object,Object>> getForumByReplyDesc();
    List<HashMap<Object,Object>> getMyForum(Integer id);
    List<HashMap<Object,Object>> searchForum(String searchForum);

    List<HashMap<Object,Object>> getForumReply(String forumId);

    HashMap<Object,Object> getForumUser(String forumId);


    HashMap<Object,Object> getForumstatus(String forumId,int userId);

    void addReply(Integer userId, String forumId,String topicId,String content);

    void reduceGood(String forumId);

    void addGood(String forumId);

    void addGoodStatus(String forumId, Integer userId);

    void upGoodStatus(String forumId, Integer userId);

    void downGoodStatus(String forumId, Integer userId);

    void addForum(String userId, String name, String des, String content);

    void addForumReply(String forumId);

    List<HashMap<Object,Object>> searchForumReply(String searchForum, String forumId);

    List<HashMap<Object,Object>> getForumReplyByReplyDesc(String forumId);

    List<HashMap<Object,Object>> getForumReplyByReplyAsc(String forumId);

    List<HashMap<Object,Object>> getForumReplyByTimeDesc(String forumId);

    List<HashMap<Object,Object>> getForumReplyByTimeAsc(String forumId);
}
