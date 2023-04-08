package com.example.movieonlinedemo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")//注意： *是来自于所有的域名请求
@RequestMapping("/api/customer")
public class MsgController {
/*    @Autowired(required = false)
    private CustomerService customerService;*/

/*    @Autowired
    private JedisPool jedisPool;*/

/*    //发送验证码
    @RequestMapping("/sengCodeNum")
    public Map sendCodeNum(String phoneNum) {
        Map map = new HashMap();
        //1.在发送验证码之前，随机撞见一个6位数的验证码
        int randomNum = new Random().nextInt(999999);//0-999999
        if (randomNum < 100000) {
            randomNum = randomNum + 100000;
        }
        //1.1在发送验证码之前 需要向redis中查询该手机 有没有验证码存在，如果存在，就直接从redis中读取，而不从阿里云发送，可以节省成本
        //查询pcode这个key在不在
        Boolean b = jedisPool.getResource().exists("phoneNum");
        if (b) {
            map.put("code", 0);
            map.put("msg", "验证啊已存在，情趣短信中查询");
            return map;
        } else {
            //2.接受到前端传过来的手机号：phoneNum  对其调用 阿里云的发送短信的工具类，去发送验证码
            ALSMSUtil.sendMsg(phoneNum, randomNum);
            //3.再送之后，将手机号当做redis key，验证码当做redis value 存入到redis数据库中
            String setex = jedisPool.getResource().setex(phoneNum, 120, String.valueOf(randomNum));
            System.out.println("setex = " + setex);
            jedisPool.getResource().persist(phoneNum);//注意：这里设置成-1 在线上环境测试要删除
            //4.将验证码发送给前端
            if ("OK".equals(setex)) {
                map.put("code", 0);
                map.put("msg", "发送成功");
                //map.put("data",randomNum);//线上不能把验证码发送前端，容易被人利用
                return map;
            } else {
                map.put("code", 500);
                map.put("msg", "发送失败");
                return map;
            }
        }
    }

    //校验手机号和验证码
    @RequestMapping("/customerLoginAxios") //  /api/customer/customerLoginAxios
    public Map customerLoginAxios(@RequestBody Map map) {
        Map codemap = new HashMap();
        //1.根据前端传来的手机号和验证码来和redis中的数据做对比
        String redisCodeNum = jedisPool.getResource().get((String) map.get("phoneNum"));//redis中的验证码
        if (map.get("codeNum").equals(redisCodeNum)) {
            codemap.put("code", 0);
            codemap.put("msg", "发送成功");
            return codemap;
        } else {
            codemap.put("code", 500);
            codemap.put("msg", "发送失败");
            return codemap;
        }
    }*/
}

