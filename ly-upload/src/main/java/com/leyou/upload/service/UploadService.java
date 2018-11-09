package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.configura.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 11:49 2018/11/5
 * @Modified:
 * @annotation:  使用属性注入的方式定义Service  看起来很专业，很装逼
 */

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
    
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    
    @Autowired
    private UploadProperties uploadProperties;
    
    //private static final List<String> ALLOW_TYPE = Arrays.asList("image/jpeg", "image/png", "image/bmp");
    
    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType(); //返回文件的内容类型
            if (!uploadProperties.getAllowTypes().contains(contentType)) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE); //抛出一个无效的文件类型异常
            }
            
            BufferedImage read = ImageIO.read(file.getInputStream());
            //校验文件内容
            if (read == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            
            //上传到 Fastdfs
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), StringUtils.substringAfterLast(file.getOriginalFilename(), "."), null);
            
           /* //目标路径
            File dest = new File("G:\\BaiduYunDown\\乐优商城\\upload", file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);*/
            
            //返回路径
            return uploadProperties.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("[文件上传] 上传文件失败", e);
            throw new LyException(ExceptionEnum.UPLOAD_ERROR);
        }
    }
}
