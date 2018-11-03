package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:55 2018/11/1
 * @Modified:
 * @annotation:
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper")
public class LyItenApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyItenApplication.class, args);
    }
}
