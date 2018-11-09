package com.leyou.upload.configura;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:40 2018/11/7
 * @Modified:
 * @annotation: 上传的一些属性
 */

@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
