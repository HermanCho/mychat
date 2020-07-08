package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.pojo.ao.UserAO;
import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import cn.edu.scau.dbclub.mychat.pojo.vo.UserVO;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.UserService;
import cn.edu.scau.dbclub.mychat.util.RandomStr;
import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${redis.userChatNum.key}")
    String preUserChatNumKey;

    @Autowired
    Mapper dozerMapper;

    @RequestMapping("/login")
    public Result login(@RequestBody UserAO userAO) {
        try {
            String key=preUserChatNumKey+userAO.getChatNum();
            User user = userService.login(userAO.getChatNum(), userAO.getPassword(),key);
            if(user == null) return Result.fail(ErrorCode.INVALID_PARAMETER,"账号或密码错误");
            //登陆时，往redis存入用户所在的服务器，在聊天时可用（两人可能不在同一个服务器）
            String randStr=RandomStr.getRandomStr(10);
            String redisValue=  randStr+"本机地址"+":"+"本机端口"+"-"+user.getId();
            JedisUtil.setObject(key, redisValue,10);
            UserVO userVO = new UserVO();
            userVO.setUser(user);
            userVO.setRandStr(randStr);
            return Result.success(userVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ErrorCode.INTERNAL_ERROR, "登录错误");
        }
    }

    @RequestMapping("/save")
    public Result save(@RequestBody User user) {
        try {
            userService.save(user);
            return Result.success();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer userId){
        try {
            userService.delete(userId);
            return Result.success();
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile file, Integer userId) {
        try {
            User user = userService.upload(file, userId);
            if(user != null) return Result.success(user);
            return Result.fail(ErrorCode.INTERNAL_ERROR, "上传失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody UserAO userAO) {
        try {
            User user = dozerMapper.map(userAO, User.class);
            userService.update(user);
            return Result.success();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer userId) {
        try {
            User user = userService.getUserById(userId);
            if(user != null) return Result.success(user);
            return Result.fail(ErrorCode.INTERNAL_ERROR, "查找失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
        }
    }

}
