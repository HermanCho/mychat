package cn.edu.scau.dbclub.mychat.util.redisUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/***
 * @Description:redis配置类
 * @Author: hermanCho
 * @Date: 2020-05-09
 * @Param null:
 * @return: null
 **/

@Configuration
public class RedisConfiguration {

    @Autowired
    public RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }


}
