package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.pojo.do0.*;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Message;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.FriendRequestMessageService;
import cn.edu.scau.dbclub.mychat.service.GroupMessageService;
import cn.edu.scau.dbclub.mychat.service.P2pMessageService;
import cn.edu.scau.dbclub.mychat.service.UserService;
import cn.edu.scau.dbclub.mychat.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private P2pMessageService p2pMessageService;

    @Autowired
    private GroupMessageService groupMessageService;

    @Autowired
    private FriendRequestMessageService friendRequestMessageService;

    @Autowired
    private UserService userService;

    @Autowired
    FastDFSClient fastDFSClient;


    @RequestMapping("/getUnReadMessages")
    public Result<List<Message>> getUnReadMessage(Integer userId) {
//        User user = userService.getUserById(userId);
        List<P2pMessage> unReadP2pMessages = p2pMessageService.getUnReadP2pMessagesByUserId(userId);
        List<GroupMessage> unReadGroupMessages = groupMessageService.getUnReadGroupMessages(userId);
        List<FriendRequestMessage> friendRequestMessages = friendRequestMessageService.getFriendRequestMessages(userId);
        List<Message> messages = new ArrayList<>(unReadP2pMessages.size() + unReadGroupMessages.size()
                + friendRequestMessages.size());
        for (int i = 0; i < unReadP2pMessages.size(); i++) {
            messages.add(new Message(0, unReadP2pMessages.get(i), null));
        }
        for (int i = 0; i < unReadGroupMessages.size(); i++) {
            messages.add(new Message(1, unReadGroupMessages.get(i), null));
        }
        for (int i = 0; i < friendRequestMessages.size(); i++) {
            messages.add(new Message(3, friendRequestMessages.get(i), null));
        }
        return Result.success(messages);
    }

    /**
     * @param p2pMessageIds
     * @description: 当认为消息送达时，就删除消息，若存在location，一道删除。
     * @return: cn.edu.scau.dbclub.mychat.result.Result
     * @author: 杜科
     * @date: 2020/5/5
     */
    @RequestMapping("/deleteP2pMessages")
    public Result deleteP2pMessages(@RequestBody List<Integer> p2pMessageIds) {
        p2pMessageService.deleteMessages(p2pMessageIds);
        return Result.success();
    }

    @RequestMapping("/deleteP2pMessage")
    public Result deleteP2pMessage(Integer p2pMessageId) {
        p2pMessageService.deleteMessage(p2pMessageId);
        return Result.success();
    }



    /**
     * @param file
     * @description: 当聊天要发送图片时，进行http通信上传图片，返回地址，拿到地址，进行websocket通信，当接收方接收到信息，
     * 检测到有location，那么在进行http通信，下载图片。
     * @return: cn.edu.scau.dbclub.mychat.result.Result<java.lang.String>
     * @author: 杜科
     * @date: 2020/5/5
     */
    @RequestMapping("/uploadFile")
    public Result<String> upload(MultipartFile file) {
        System.out.println(file.getName());
        String path = null;
        if (file != null)
            try {
                path = fastDFSClient.uploadFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
            }
        if (path == null) return Result.fail(ErrorCode.INTERNAL_ERROR, "上传失败");
        return Result.success(path);
    }

}
