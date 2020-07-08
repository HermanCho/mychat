package cn.edu.scau.dbclub.mychat.pojo.vo;

import cn.edu.scau.dbclub.mychat.pojo.do0.User;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/6/8
 */
public class UserVO {

    private User user;

    private String randStr;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRandStr() {
        return randStr;
    }

    public void setRandStr(String randStr) {
        this.randStr = randStr;
    }
}
