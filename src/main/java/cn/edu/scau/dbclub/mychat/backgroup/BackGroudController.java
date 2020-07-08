package cn.edu.scau.dbclub.mychat.backgroup;

import cn.edu.scau.dbclub.mychat.pojo.ao.UserAO;
import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import cn.edu.scau.dbclub.mychat.pojo.vo.UserVO;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.util.RandomStr;
import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/***
 * @Description: 后台功能，实现简单的人数统计功能。小时、日、周的用户量
 *
 * @Author: hermanCho
 * @Date: 2020-05-07
 * @Param null:
 * @return: null
 **/
@RestController
@RequestMapping("/backgroup")
public class BackGroudController {

    // 不清楚是否支持自动注入，必要时直接换为饿汉单例
//    public static UVCounterService uvCounterService = new UVCounterService();
    @Autowired
    UVCounterService uvCounterService;

    @RequestMapping("/test")
    public void test() {
        String key = "USER:LOGIN:06290957";
        JedisUtil.pfAdd(key, "3");
        System.out.println(JedisUtil.pfCount(key));
    }


    @RequestMapping("/adminlogin")
    public Result adminlogin(@RequestBody UserAO userAO) {
        if("admin".equals(userAO.getChatNum())){
            if("admin".equals(userAO.getPassword())){
                return Result.success();
            }
        }
        return Result.fail(ErrorCode.UNAUTHORIZED, "管理员信息错误");
    }


    @RequestMapping("/getWeeklyUV")
    public long getWeeklyUV() {
        return uvCounterService.getWeeklyUV();
    }

    @RequestMapping("/getDailyUV")
    public long getDailyUV() {
        return uvCounterService.getDailyUV();
    }

    @RequestMapping("/getHourlyUV")
    public long getHourlyUV() {
        return uvCounterService.getHourlyUV();
    }

}
