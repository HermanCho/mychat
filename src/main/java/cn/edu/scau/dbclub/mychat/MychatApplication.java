package cn.edu.scau.dbclub.mychat;


import cn.edu.scau.dbclub.mychat.dao.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.edu.scau.dbclub.mychat.dao")
public class MychatApplication {

    public static void main(String[] args) {

        SpringApplication.run(MychatApplication.class, args);

    }

}
