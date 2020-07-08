package cn.edu.scau.dbclub.mychat.controller;

import cn.edu.scau.dbclub.mychat.pojo.ao.PyqAO;
import cn.edu.scau.dbclub.mychat.pojo.do0.Pyq;
import cn.edu.scau.dbclub.mychat.result.ErrorCode;
import cn.edu.scau.dbclub.mychat.result.Result;
import cn.edu.scau.dbclub.mychat.service.PyqService;
import cn.edu.scau.dbclub.mychat.util.FastDFSClient;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 杜科
 * @description 就只是进行朋友圈的操作，而朋友圈的点赞和评论单独处理
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
@RestController
@RequestMapping("/pyq")
public class PyqController {

    @Autowired
    private PyqService pyqService;

    @Autowired
    Mapper dozerMapper;

    @Autowired
    FastDFSClient fastDFSClient;

    //可能要分两次存储
    @RequestMapping("/save")
    public Result save(MultipartFile file, @RequestBody PyqAO pyqAO) {
        System.out.println(pyqAO);
        String path = null;
        if (file != null)
            try {
                path = fastDFSClient.uploadFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
            }
        if(path==null) return Result.fail(ErrorCode.INTERNAL_ERROR,"上传失败");
        Pyq pyq = dozerMapper.map(pyqAO, Pyq.class);
        pyq.setPictureLocation(path);
        pyqService.save(pyq);
        return Result.success();
    }

    @RequestMapping("/uploadFile")
    public Result upload(MultipartFile file,Integer pyqId) {
        System.out.println(file.getName());
        String path = null;
        if (file != null)
            try {
                path = fastDFSClient.uploadFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                return Result.fail(ErrorCode.INTERNAL_ERROR, e.getMessage());
            }
        if(path==null) return Result.fail(ErrorCode.INTERNAL_ERROR,"上传失败");
        Pyq pyq = pyqService.getPyq(pyqId);
        pyq.setPictureLocation(path);
        pyqService.updatePyq(pyq);
        return Result.success();
    }

    /**
     * @description: 根据用户id返回其全部的朋友圈
     * todo 分页
     * @param userId
     * @return: cn.edu.scau.dbclub.mychat.result.Result<java.util.List<cn.edu.scau.dbclub.mychat.pojo.do0.Pyq>>
     * @author: 杜科
     * @date: 2020/4/30
     */
    @RequestMapping("/getUserPyqs")
    public Result<List<Pyq>> getPyqs(Integer userId) {
        System.out.println(userId);
        List<Pyq> pyqs = pyqService.getPyqsByUserId(userId);
        return Result.success(pyqs);
    }

    /**
     * @description: 根据用户id返回其全部可见的朋友圈
     * @param userId
     * @return: cn.edu.scau.dbclub.mychat.result.Result<java.util.List<cn.edu.scau.dbclub.mychat.pojo.do0.Pyq>>
     * @author: 杜科
     * @date: 2020/4/30
     */
    @RequestMapping("/getUserAndFriendPyqs")
    public Result<List<Pyq>> getUserAndFriendPyqs(Integer userId) {
        List<Pyq> pyqs = pyqService.getUserAndFriendPyqsByUserId(userId);
        return Result.success(pyqs);
    }

    //删除朋友圈和相关文件
    @RequestMapping("/delete")
    public Result delete(Integer pyqId) {
        pyqService.delete(pyqId);
        return Result.success();
    }

}
