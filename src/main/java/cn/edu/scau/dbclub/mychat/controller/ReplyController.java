package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.pojo.ao.PyqCommentReplyAO;
import cn.edu.scau.dbclub.mychat.pojo.do0.PyqCommentReply;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.PyqCommentReplyService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/5/8
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    PyqCommentReplyService replyService;

    @Autowired
    Mapper dozerMapper;

    @RequestMapping("/getRepliesByPyqId")
    public Result<List<PyqCommentReply>> getRepliesByPyqId(Integer pyqId){
        List<PyqCommentReply> replies=null;
        try{
            replies = replyService.getRepliesByPyqId(pyqId);
        }catch (Exception e){
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
        return Result.success(replies);
    }

    @RequestMapping("/save")
    public Result save(@RequestBody PyqCommentReplyAO replyAO){
        try {
            PyqCommentReply reply=dozerMapper.map(replyAO,PyqCommentReply.class);
            replyService.save(reply);
        }catch (Exception e){
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
        return Result.success();
    }

    @RequestMapping("/delete")
    public Result delete(Integer replyId){
        try {
            replyService.delete(replyId);
        }catch (Exception e){
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
        return Result.success();
    }
}
