package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.GroupMapper;
import cn.edu.scau.dbclub.mychat.dao.GroupMemMapper;
import cn.edu.scau.dbclub.mychat.dao.GroupMessageMapper;
import cn.edu.scau.dbclub.mychat.dao.P2pMessageMapper;
import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMessage;
import cn.edu.scau.dbclub.mychat.pojo.do0.P2pMessage;
import cn.edu.scau.dbclub.mychat.service.GroupMessageService;
import cn.edu.scau.dbclub.mychat.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/***
 * @Description: 保存群信息
 * @Author: hermanCho
 * @Date: 2020-05-02
 * @Param null:
 * @return: null
 **/

@Transactional
@Service
public class GroupMessageServiceImpl implements GroupMessageService {

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupMemMapper groupMemMapper;

    @Autowired
    GroupMessageMapper groupMessageMapper;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Override
    public void save(GroupMessage groupMessage) {
        //异步存到数据库
        executor.execute(() -> groupMessageMapper.insertGroupMessage(groupMessage));
    }

    @Override
    public List<GroupMessage> getUnReadGroupMessages(Integer userId) {
        List<GroupMessage> unReadGroupMessages = groupMessageMapper.selectUnReadGroupMessagesByUserId(userId);
        return unReadGroupMessages;
    }

    @Override
    public void deleteGroupMessage(GroupMessage groupMessage) {
        String location = groupMessage.getLocation();
        if (location != null) fastDFSClient.deleteFile(location); // 同，有文件则删除文件
        groupMessageMapper.deleteGroupMessage(groupMessage.getId());
    }

    @Override
    public void deleteListGroupMessage(List<GroupMessage> groupMessages) {
        for (GroupMessage groupMessage : groupMessages) {
            this.deleteGroupMessage(groupMessage);
        }
    }

    @Override
    public void deleteAllReadGroupMessage(Integer groupId) {
        int groupMesgId = groupMemMapper.selectOldestMessageId(groupId);
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setId(groupMesgId);
        groupMessage.setGroupId(groupId);

        List<GroupMessage> groupMessages = groupMessageMapper.selectOlderGroupMessage(groupMessage);
        this.deleteListGroupMessage(groupMessages);

    }


}
