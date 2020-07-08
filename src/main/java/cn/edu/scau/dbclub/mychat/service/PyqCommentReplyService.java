package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.PyqCommentReply;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/5/8
 */
public interface PyqCommentReplyService {
    List<PyqCommentReply> getRepliesByPyqId(Integer pyqId);

    void save(PyqCommentReply reply);

    void delete(Integer replyId);
}
