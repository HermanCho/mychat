package cn.edu.scau.dbclub.mychat.config;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 杜科
 * @description 一些单例
 * @contact AllenDuke@163.com
 * @date 2020/4/24
 */
@Configuration
public class SingletonBeansConfig {

    @Bean
    public ThreadPoolExecutor executor(){
        return new ThreadPoolExecutor(2,4,
                30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }
}
