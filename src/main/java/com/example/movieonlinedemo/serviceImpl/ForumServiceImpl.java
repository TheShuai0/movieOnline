package com.example.movieonlinedemo.serviceImpl;

import com.example.movieonlinedemo.mapper.ForumMapper;
import com.example.movieonlinedemo.service.ForumService;
import com.teradata.ec.common.model.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    ForumMapper forumMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ActionResult getAllForum() {
        ActionResult ar = new ActionResult();
        ar.setData(forumMapper.getForumByTimeDesc());
        return ar;
    }

    @Override
    public ActionResult getForumByTime(String ascOrDesc) {
        ActionResult ar = new ActionResult();
        if(ascOrDesc.equals("asc")){
            ar.setData(forumMapper.getForumByTimeAsc());
        }else {
            ar.setData(forumMapper.getForumByTimeDesc());
        }
        return ar;
    }

    @Override
    public ActionResult getForumByReply(String ascOrDesc) {
        ActionResult ar = new ActionResult();
        if(ascOrDesc.equals("asc")){
            ar.setData(forumMapper.getForumByReplyAsc());
        }else {
            ar.setData(forumMapper.getForumByReplyDesc());
        }
        return ar;
    }

    @Override
    public ActionResult getMyForum(String token) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        ar.setData(forumMapper.getMyForum((Integer)user.get("ID")));
        return ar;
    }

    @Override
    public ActionResult searchForum(String searchForum) {
        ActionResult ar = new ActionResult();
        ar.setData(forumMapper.searchForum(searchForum));
        return ar;
    }

    @Override
    public ActionResult getForumReply(String forumId) {
        ActionResult ar = new ActionResult();
        ar.setData(forumMapper.getForumReply(forumId));
        return ar;
    }

    @Override
    public ActionResult getForumUser(String forumId,String token) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        HashMap<Object,Object> map = forumMapper.getForumUser(forumId);
        HashMap<Object,Object> map2 = forumMapper.getForumstatus(forumId,(Integer)user.get("ID"));
        if(map2 == null){
            map.put("status",0);
        }else {
            map.put("status",map2.get("status"));
        }
        ar.setData(map);
        return ar;
    }

    @Override
    public ActionResult addReply(String token,String forumId,String topicId,String content) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        forumMapper.addReply((Integer)user.get("ID"),forumId,topicId,content);
        forumMapper.addForumReply(forumId);
        return ar;
    }

    @Override
    public ActionResult addOrReduce(String status, String forumId,String token) {
        ActionResult ar = new ActionResult();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String,Object> user = (HashMap) valueOperations.get(token);
        if(status.equals("0")){
            forumMapper.addGood(forumId);
            HashMap<Object,Object> map = forumMapper.getForumstatus(forumId,(Integer)user.get("ID"));
            if(map == null){
                forumMapper.addGoodStatus(forumId,(Integer)user.get("ID"));
            }else {
                forumMapper.upGoodStatus(forumId,(Integer)user.get("ID"));
            }
            ar.setData(1);
        }else {
            forumMapper.reduceGood(forumId);
            forumMapper.downGoodStatus(forumId,(Integer)user.get("ID"));
            ar.setData(0);
        }
        return ar;
    }

    @Override
    public ActionResult addForum(String userId, String name, String des, String content) {
        ActionResult ar = new ActionResult();
        forumMapper.addForum(userId,name,des,content);
        return null;
    }

    @Override
    public ActionResult searchForumReply(String searchForum, String forumId) {
        ActionResult ar = new ActionResult();
        ar.setData(forumMapper.searchForumReply(searchForum,forumId));
        return ar;
    }

    @Override
    public ActionResult getForumReplyByReply(String forumId, String judgeNumber) {
        ActionResult ar = new ActionResult();
        if(judgeNumber.equals("true")){
            ar.setData(forumMapper.getForumReplyByReplyDesc(forumId));
        }else {
            ar.setData(forumMapper.getForumReplyByReplyAsc(forumId));
        }
        return ar;
    }

    @Override
    public ActionResult getForumReplyByTime(String forumId, String judgeTime) {
        ActionResult ar = new ActionResult();
        if(judgeTime.equals("true")){
            ar.setData(forumMapper.getForumReplyByTimeDesc(forumId));
        }else {
            ar.setData(forumMapper.getForumReplyByTimeAsc(forumId));
        }

        return ar;
    }

    @Override
    public ActionResult getForumApply(String page) {
        ActionResult ar = new ActionResult();
        ar.setData(forumMapper.getForumApply(Integer.parseInt(page)*10,10));
        return ar;
    }

    @Override
    public ActionResult okForumApply(String forumId) {
        forumMapper.okApply(forumId);
        return null;
    }

    @Override
    public ActionResult deleteForumApply(String forumId) {
        forumMapper.deleteApply(forumId);
        return null;
    }
}
