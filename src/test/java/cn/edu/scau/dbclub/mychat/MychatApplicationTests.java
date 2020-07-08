package cn.edu.scau.dbclub.mychat;

import cn.edu.scau.dbclub.mychat.dao.P2pMessageMapper;
import cn.edu.scau.dbclub.mychat.dao.UserMapper;
import cn.edu.scau.dbclub.mychat.util.FastDFSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MychatApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void testUser() {
        System.out.println(userMapper.getUser(2));
    }

}
