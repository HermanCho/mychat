package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/21
 */
public interface UserService {

    List<User> listUsers();

    User login(String chatNum, String password,String key);

    void save(User user);

    User upload(MultipartFile file, Integer userId);

    User getUserById(Integer userId);

    User update(User user);

    void delete(Integer userId);
}
