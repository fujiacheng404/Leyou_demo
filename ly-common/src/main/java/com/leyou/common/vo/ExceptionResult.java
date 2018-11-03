package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:54 2018/11/2
 * @Modified:
 * @annotation: 异常结果对象
 */

@Data
public class ExceptionResult {
    private int status;
    private String msg;
    private Long timestamp;
    
    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.msg = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
