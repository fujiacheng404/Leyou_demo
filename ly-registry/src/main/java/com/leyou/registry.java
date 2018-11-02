package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:08 2018/11/1
 * @Modified:
 * @annotation:
 */

@EnableEurekaServer
@SpringBootApplication
public class registry {
    public static void main(String[] args) {
        SpringApplication.run(registry.class, args);
    }
}
