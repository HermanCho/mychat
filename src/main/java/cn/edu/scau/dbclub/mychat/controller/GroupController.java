package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.exception.GroupNotFoundException;
import cn.edu.scau.dbclub.mychat.exception.UserNotFoundException;
import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.GroupMemService;
import cn.edu.scau.dbclub.mychat.service.GroupMessageService;
import cn.edu.scau.dbclub.mychat.service.GroupService;
import cn.edu.scau.dbclub.mychat.service.UserService;
import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @Description: 群组 ,暂时没用dozer ，功能还没写完 。
 *               可以考虑把验证提出来，单独一个Util类
 * @Author: hermanCho
 * @Date: 2020-05-02
 * @Param null:
 * @return: null
 **/

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @Autowired
    GroupMemService groupMemService;

    @Autowired
    GroupMessageService groupMessageService;

    // 引入该service，是为了验证userId是否存在
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public void test() {
        System.out.println("触发");
        Group group = groupService.getGroup(1);
        System.out.println(group);
        System.out.println("测试成功");
    }

    //    @RequestMapping("/test")
//    public void test() {
//        JedisUtil.setObject("gOnlineNum:45", "50", 3);
//    }
//
//    @RequestMapping("/test2")
//    public Integer test2() {
//        return JedisUtil.getId();
//    }
//
    @RequestMapping("/redistest")
    public void redistest() {
        System.out.println(JedisUtil.getObject("aaa", String.class));
    }


    //    @RequestMapping("/createGroup")
//    public Result createGroup(Integer userId, @RequestBody Group group) {
//        groupService.addGroup(userId, group);
//        return Result.success();
//    }

    @RequestMapping("/createGroup/{userId}")
    public Result createGroup(@PathVariable("userId") Integer userId, @RequestBody Group group) {
        groupService.addGroup(userId, group);
        return Result.success();
    }

    /***
     * @Description: 对应 : 修改群名，发布群公告，其他字段都无法主动修改
     * @Author: hermanCho
     * @Date: 2020-05-04
     * @Param group:
     * @return: cn.edu.scau.dbclub.mychat.result.Result
     **/

    @RequestMapping("/modifyGroup")
    public Result modifyGroup(@RequestBody Group group) {
        groupService.modifyGroup(group);
        return Result.success();
    }

    @RequestMapping("/getGroup")
    public Result getGroup(Integer groupId) {
        Group group = groupService.getGroup(groupId);
        if (group == null) return Result.fail(ErrorCode.INTERNAL_ERROR, "查找失败");
        return Result.success(group);
    }


    /***
     * @Description: 只在这里做验证，别的验证都没写，后续有必要再补吧
     * @Author: hermanCho
     * @Date: 2020-05-04
     * @Param userId:
     * @Param group:
     * @return: cn.edu.scau.dbclub.mychat.result.Result
     **/
    @RequestMapping("/createGroupMem/{userId}")
    public Result createGroupMem(@PathVariable("userId") Integer userId, @RequestBody Group group) {
//        try {
//            userService.getUserById(userId);
//            groupService.getGroup(group.getId());
//        } catch (UserNotFoundException userNotFoundException) {
//            String message = userNotFoundException.getMessage();
//            return Result.invalidParameter(message);
//        } catch (GroupNotFoundException groupNotFoundException) {
//            String message = groupNotFoundException.getMessage();
//            return Result.invalidParameter(message);
//        }

        groupMemService.addGroupMem(userId, group);
        return Result.success();
    }


    @RequestMapping("/removeGroupMem/{userId}")
    public Result removeGroupMem(@PathVariable("userId") Integer userId, @RequestBody Group group) {
        groupMemService.removeGroupMem(userId, group);
        return Result.success();
    }


    /***
     * @Description: 根据群组id，返回群组成员id
     * @Author: hermanCho
     * @Date: 2020-05-10
     * @Param groupId:
     * @return: cn.edu.scau.dbclub.mychat.result.Result
     **/
    @RequestMapping("/getGroupMems")
    public Result getGroupMems(Integer groupId) {
        List<Integer> memIdsByGroupId = groupMemService.getMemIdsByGroupId(groupId);
        return Result.success(memIdsByGroupId);
    }


    /***
     * @Description: 更新相应的群信息，即GroupMem的groupMsgId字段。因为标准不应该是登录、离线，而是是否查看未读的群消息
     *                 同时删除群组中所有人的已读消息
     * @Author: hermanCho
     * @Date: 2020-05-05
     * @Param userId:
     * @Param group:
     * @return:
     **/
    @RequestMapping("/modifyGroupMem/{userId}")
    public Result modifyGroupMem(@PathVariable("userId") Integer userId, @RequestBody Group group) {
        groupMemService.updateGroupMem(userId, group);
        groupMessageService.deleteAllReadGroupMessage(group.getId());
        return Result.success();
    }


    /**
     * @Description: 根据用户id，返回其加入的所有组
     * @Author: hermanCho
     * @Date: 2020-06-27
     * @Param userId:
     * @return: cn.edu.scau.dbclub.mychat.result.Result
     **/

    @RequestMapping("/getGroups")
    public Result getGroups(Integer userId) {
        List<Group> groups = groupMemService.selectGroupsByUserId(userId);
        return Result.success(groups);
    }

}
