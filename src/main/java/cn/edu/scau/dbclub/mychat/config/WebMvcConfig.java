//package cn.edu.scau.dbclub.mychat.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private MyInterceptor myInterceptor;
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry
//                .addInterceptor(myInterceptor)
//                .excludePathPatterns("/user/login")
//                .addPathPatterns("/**");
//    }
//
//}