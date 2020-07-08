package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike;
import java.util.List;

/***
  * @Description: 点赞mapper
  * @Author: hermanCho
  * @Date: 2020-05-09
  * @Param null:
  * @return: null
  **/

public interface PyqLikeMapper {
    int deletePyqLike(Integer id);


    // 注意，本类不采用DB的自增主键策略，类中已自带了一个自增id
    int insertPyqLike(PyqLike record);

    /***
     * @Description: 根据pyqId返回该pyq的全部点赞
     * @Author: hermanCho
     * @Date: 2020-05-09
     * @Param pyqId:
     * @return: java.util.List<cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike>
     **/

    List<PyqLike> selectListPyqLikes(Integer pyqId);


    PyqLike selectPyqLike(Integer id);

    //update应该没用，最多就是取消再赞，没有更新这一需求，但还是保留
    int updatePyqLike(PyqLike record);
    
    List<PyqLike> listPyqLikes();
    
    
    
}