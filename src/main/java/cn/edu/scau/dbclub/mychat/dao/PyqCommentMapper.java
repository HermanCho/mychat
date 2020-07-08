package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.PyqComment;
import java.util.List;

public interface PyqCommentMapper {
    int deletePyqComment(Integer id);

    int savePyqComment(PyqComment record);

    PyqComment getPyqComment(Integer id);

    List<PyqComment> listPyqComments();

    int updatePyqComment(PyqComment record);

    List<PyqComment> getPyqCommentsByPyqId(Integer pyqId);
}