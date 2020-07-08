package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.Pyq;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
public interface PyqService {
    void save(Pyq pyq);

    /**
     * @description: 根据用户id，分页返回该用户所有的朋友圈
     * @param userId 用户id
     * @return: java.util.List<cn.edu.scau.dbclub.mychat.pojo.do0.Pyq>
     * @author: 杜科
     * @date: 2020/4/25
     */
    List<Pyq> getPyqsByUserId(Integer userId);

    void delete(Integer pyqId);

    /**
     * @description: 根据用户id，分页返回该用户所有可见的朋友圈（包含朋友的）
     * @param userId
     * @return: java.util.List<cn.edu.scau.dbclub.mychat.pojo.do0.Pyq>
     * @author: 杜科
     * @date: 2020/4/30
     */
    List<Pyq> getUserAndFriendPyqsByUserId(Integer userId);

    Pyq getPyq(Integer pyqId);

    void updatePyq(Pyq pyq);
}
