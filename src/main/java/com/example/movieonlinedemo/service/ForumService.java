package com.example.movieonlinedemo.service;

import com.teradata.ec.common.model.ActionResult;
import org.springframework.stereotype.Service;


public interface ForumService {

    ActionResult getAllForum();

    ActionResult getForumByTime(String ascOrDesc);

    ActionResult getForumByReply(String ascOrDesc);


    ActionResult getMyForum(String token);

    ActionResult searchForum(String searchForum);

    ActionResult getForumReply(String forumId);

    ActionResult getForumUser(String forumId,String token);

    ActionResult addReply(String token,String forumId,String topicId,String content);

    ActionResult addOrReduce(String status,String addOrReduce, String forumId);

    ActionResult addForum(String userId, String name, String des, String content);

    ActionResult searchForumReply(String searchForum, String forumId);

    ActionResult getForumReplyByReply(String forumId, String judgeNumber);

    ActionResult getForumReplyByTime(String forumId, String judgeTime);
}
