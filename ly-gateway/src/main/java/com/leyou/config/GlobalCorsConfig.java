package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 12:07 2018/11/3
 * @Modified:
 * @annotation: 跨域访问过滤器
 */

@Configuration
public class GlobalCorsConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1).允许的域 如果写*的话就不能使用cookie了
        corsConfiguration.addAllowedOrigin("http://manage.leyou.com");
        //2).是否发送cookie消息
        corsConfiguration.setAllowCredentials(true);
        //3).允许的请求方式
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        //4).允许的头信息
        corsConfiguration.addAllowedHeader("*");
        //5).有效时长
        corsConfiguration.setMaxAge(3600L);
        
        //2.添加映射路径  拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        
        //3.返回新的CorsFilter
        return new CorsFilter(configurationSource);
    }
}
