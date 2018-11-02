package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:55 2018/11/1
 * @Modified:
 * @annotation:
 */

@SpringBootApplication
@EnableDiscoveryClient
public class LyItenApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyItenApplication.class, args);
    }
}
