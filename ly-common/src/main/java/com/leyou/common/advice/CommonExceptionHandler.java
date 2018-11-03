package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 20:23 2018/11/2
 * @Modified:
 * @annotation: 通用异常处理类 适用于捕获异常
 */

@ControllerAdvice
public class CommonExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e) {
//        ExceptionEnum exceptionEnums = e.getExceptionEnums();
        return ResponseEntity.status(e.getExceptionEnums().getCode()).body(new ExceptionResult(e.getExceptionEnums()));
    }
}
