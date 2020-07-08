package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.PyqCommentReply;
import java.util.List;

public interface PyqCommentReplyMapper {
    int deletePyqCommentReply(Integer id);

    int savePyqCommentReply(PyqCommentReply record);

    PyqCommentReply getPyqCommentReply(Integer id);

    List<PyqCommentReply> listPyqCommentReplys();

    int updatePyqCommentReply(PyqCommentReply record);

    List<PyqCommentReply> getPyqCommentRepliesByPyqId(Integer pyqId);
}