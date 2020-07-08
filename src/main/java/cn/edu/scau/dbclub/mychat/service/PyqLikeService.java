package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.Pyq;
import cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PyqLikeService {

    List<PyqLike> getPyqLikes(Integer pyqId);

    PyqLike getPyqLike(Integer id);

    void deletePyqLike(Integer id);


    /***
      * @Description: 主要是要userId和PyqId两个字段
      * @Author: hermanCho
      * @Date: 2020-05-09
      * @Param pyqLike:
      * @return: void
      **/
    void addPyqLike(PyqLike pyqLike);


}
