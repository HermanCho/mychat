package cn.edu.scau.dbclub.mychat.exception;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/29
 */
public class LoginErrorException extends RuntimeException {

    public LoginErrorException(){}

    public LoginErrorException(String s){super(s);}
}
