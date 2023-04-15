package com.example.movieonlinedemo.controller;

import com.example.movieonlinedemo.service.ForumService;
import com.example.movieonlinedemo.service.MovieService;
import com.teradata.ec.common.model.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ForumController {
    @Autowired
    private ForumService forumService;
    @RequestMapping(value = "/forum/getAllForum", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getAllForum(HttpServletRequest request){
        return forumService.getAllForum();
    }

    @RequestMapping(value = "/forum/getForumByTime", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getForumByTime(HttpServletRequest request){
        String ascOrDesc =  request.getParameter("ascOrDesc");
        return forumService.getForumByTime(ascOrDesc);
    }

    @RequestMapping(value = "/forum/getForumByReply", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getForumByReply(HttpServletRequest request){
        String ascOrDesc =  request.getParameter("ascOrDesc");
        return forumService.getForumByReply(ascOrDesc);
    }

    @RequestMapping(value = "/forum/getMyForum", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getMyForum(HttpServletRequest request){
        String token = request.getParameter("token");
        return forumService.getMyForum(token);
    }

    @RequestMapping(value = "/forum/searchForum", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult searchForum(HttpServletRequest request){
        String searchForum = request.getParameter("searchForum");
        return forumService.searchForum(searchForum);
    }

    @RequestMapping(value = "/forum/getForumReply", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getForumReply(HttpServletRequest request){
        String forumId = request.getParameter("forumId");
        return forumService.getForumReply(forumId);
    }

    @RequestMapping(value = "/forum/getForumUser", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getForumUser(HttpServletRequest request){
        String forumId = request.getParameter("forumId");
        String token = request.getParameter("token");
        return forumService.getForumUser(forumId,token);
    }

    @RequestMapping(value = "/forum/addReply", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addReply(HttpServletRequest request){
        String forumId = request.getParameter("forumId");
        String topicId = request.getParameter("topicId");
        String content = request.getParameter("content");
        String token = request.getParameter("token");
        return forumService.addReply(token,forumId,topicId,content);
    }
    @RequestMapping(value = "/forum/addForum", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addForum(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String des = request.getParameter("des");
        String content = request.getParameter("content");
        return forumService.addForum(userId,name,des,content);
    }


    @RequestMapping(value = "/forum/addOrReduce", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult addOrReduce(HttpServletRequest request){
        String forumId = request.getParameter("forumId");
        String token = request.getParameter("token");
        String status = request.getParameter("status");
        return forumService.addOrReduce(status,forumId,token);
    }

    @RequestMapping(value = "/forum/searchForumReply", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult searchForumReply(HttpServletRequest request){
        String searchForum = request.getParameter("searchForum");
        String forumId = request.getParameter("forumId");
        return forumService.searchForumReply(searchForum,forumId);
    }

    @RequestMapping(value = "/forum/getForumReplyByReply", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getForumReplyByReply(HttpServletRequest request){
        String forumId = request.getParameter("forumId");
        String judgeNumber = request.getParameter("judgeNumber");
        return forumService.getForumReplyByReply(forumId,judgeNumber);
    }

    @RequestMapping(value = "/forum/getForumReplyByTime", method = RequestMethod.POST)
    public @ResponseBody
    ActionResult getForumReplyByTime(HttpServletRequest request){
        String forumId = request.getParameter("forumId");
        String judgeTime = request.getParameter("judgeTime");
        return forumService.getForumReplyByTime(forumId,judgeTime);
    }

}
