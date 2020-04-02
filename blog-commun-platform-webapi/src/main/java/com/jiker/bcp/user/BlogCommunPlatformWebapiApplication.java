package com.jiker.bcp.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.jiker.bcp.user.dao")
public class BlogCommunPlatformWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCommunPlatformWebapiApplication.class, args);
    }

}
