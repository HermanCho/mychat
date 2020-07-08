package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.PyqCommentMapper;
import cn.edu.scau.dbclub.mychat.pojo.do0.PyqComment;
import cn.edu.scau.dbclub.mychat.service.PyqCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/5/8
 */
@Service
@Transactional
public class PyqCommentServiceImpl implements PyqCommentService {

    @Autowired
    PyqCommentMapper pyqCommentMapper;

    @Override
    public List<PyqComment> getCommentsByPyqId(Integer pyqId) {
        return pyqCommentMapper.getPyqCommentsByPyqId(pyqId);
    }

    @Override
    public void delete(Integer commentId) {
        pyqCommentMapper.deletePyqComment(commentId);
    }

    @Override
    public void save(PyqComment comment) {
        pyqCommentMapper.savePyqComment(comment);
    }
}
