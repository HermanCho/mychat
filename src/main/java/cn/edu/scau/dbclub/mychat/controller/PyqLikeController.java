package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.PyqLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pyqLike")
public class PyqLikeController {

    @Autowired
    PyqLikeService pyqLikeService;



    @RequestMapping("/createPyqLike")
    public Result createPyqLike(@RequestBody PyqLike pyqLike){
        pyqLikeService.addPyqLike(pyqLike);
        return Result.success();
    }

    @RequestMapping("/removePyqLike")
    public Result removePyqLike(Integer pyqLikeId){
        pyqLikeService.deletePyqLike(pyqLikeId);
        return Result.success();
    }


    /***
      * @Description: 根据pyqId, 查询全部赞
      * @Author: hermanCho
      * @Date: 2020-05-10
      * @Param pyqId:
      * @return: cn.edu.scau.dbclub.mychat.result.Result
      **/

    @RequestMapping("/getPyqLikes")
    public Result getPyqLikes(Integer pyqId){
        List<PyqLike> pyqLikes = pyqLikeService.getPyqLikes(pyqId);
        return Result.success(pyqLikes);
    }

    /***
      * @Description:  删除pyq时，根据pyqId，删除全部点赞
      * @Author: hermanCho
      * @Date: 2020-05-09
      * @Param pyqId:
      * @return: cn.edu.scau.dbclub.mychat.result.Result
      **/

    @RequestMapping("/removePyqLikes")
    public Result removePyqLikes(Integer pyqId){
        List<PyqLike> pyqLikes = pyqLikeService.getPyqLikes(pyqId);
        for(PyqLike pyqLike:pyqLikes){
            pyqLikeService.deletePyqLike(pyqLike.getId());
        }
        return Result.success();
    }

}
