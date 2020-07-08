package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.UserMapper;
import cn.edu.scau.dbclub.mychat.exception.LoginErrorException;
import cn.edu.scau.dbclub.mychat.exception.UserNotFoundException;
import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import cn.edu.scau.dbclub.mychat.service.UserService;
import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import cn.edu.scau.dbclub.mychat.util.RandomStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Value("${redis.userChatNum.key}")
    String preUserChatNumKey;

    @Override
    public List<User> listUsers() {
        return null;
    }

    /**
     * @description: 判断是否已经登陆过（在redis中存在），如果已经登陆过，抛出异常，否则查出该用户，并添加到缓存，
     * 如果查不到，抛出异常
     * @param chatNum
     * @param password
     * @return: cn.edu.scau.dbclub.mychat.pojo.do0.User
     * @author: 杜科
     * @date: 2020/4/30
     */
    @Override
    public User login(String chatNum, String password,String key) {
        if(JedisUtil.isExist(key))
            throw new LoginErrorException("chatNum为：" + chatNum + " 的用户，已经登陆过了");
        User user = userMapper.getUserByChatNum(chatNum);
        if (user == null) throw new UserNotFoundException("找不到chatNum为：" + chatNum + "的用户！");
        if (!user.getPassword().equals(password))
            throw new LoginErrorException("chatNum为：" + chatNum + " 的用户，输入的密码错误，错误密码："
                    +password+" 正确密码："+user.getPassword());
        return user;
    }

    @Override
    public void save(User user) {
        userMapper.saveUser(user);
        System.out.println("新增用户" + user);
    }

    @Override
    public void delete(Integer userId) {
        userMapper.deleteUser(userId);
        System.out.println("删除用户 " + userId);
    }

    @Override
    public User upload(MultipartFile file, Integer userId) {
        return null;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = userMapper.getUser(userId);
        if (user == null) throw new UserNotFoundException("找不到id为：" + userId + "的用户！");
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.updateUser(user);
        return user;
    }
}
