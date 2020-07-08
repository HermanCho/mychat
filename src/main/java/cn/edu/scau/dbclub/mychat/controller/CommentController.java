package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.pojo.ao.PyqCommentAO;
import cn.edu.scau.dbclub.mychat.pojo.do0.PyqComment;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.PyqCommentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杜科
 * @description 就只是进行评论的操作，评论的回复单独处理
 * @contact AllenDuke@163.com
 * @date 2020/5/8
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    PyqCommentService pyqCommentService;

    @Autowired
    Mapper dozerMapper;

    @RequestMapping("/getCommentsByPyqId")
    public Result<List<PyqComment>> getCommentsByPyqId(Integer pyqId){
        List<PyqComment> comments=null;
        try {
             comments= pyqCommentService.getCommentsByPyqId(pyqId);
        }catch (Exception e){
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
        return Result.success(comments);
    }

    @RequestMapping("/deleteComment")
    public Result delete(Integer commentId){
        try {
            pyqCommentService.delete(commentId);
        }catch (Exception e){
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
        return Result.success();
    }

    @RequestMapping("/save")
    public Result save(@RequestBody PyqCommentAO pyqCommentAO){
        try {
            PyqComment comment=dozerMapper.map(pyqCommentAO,PyqComment.class);
            pyqCommentService.save(comment);
        }catch (Exception e){
            return Result.fail(ErrorCode.INTERNAL_ERROR,e.getMessage());
        }
        return Result.success();
    }
}
