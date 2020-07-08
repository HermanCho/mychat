package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: sql返回值默认就是受影响的行
 * 所以用int接受，用来判断是否成功。
 * 返回int特殊时，会备注
 * @Author: hermanCho
 * @Date: 2020-05-02
 * @Param null:
 * @return: null
 **/

public interface GroupMapper {


    /***
     * @Description: 新建群
     * @Author: hermanCho
     * @Date: 2020-05-02
     * @Param name: 群名
     * @return: int  新建群的id
     **/

    // 注意这里返回的是groupId，不是受影响的行
    int insertGroup(Group group);

    int deleteGroup(Integer id);

    Group selectGroup(Integer id);

    /**
     * @Description: 更新群组，主要是群通告、群人数、在线人数
     * 后两者的消耗比较昂贵
     * @Author: hermanCho
     * @Date: 2020-05-02
     * @Param group:
     * @return: int
     **/
    int updateGroup(Group group);


}