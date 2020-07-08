package cn.edu.scau.dbclub.mychat.exception;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/29
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){}

    public UserNotFoundException(String s){super(s);}
}
