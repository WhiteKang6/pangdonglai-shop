package com.database.homework.pangdonglaishop.handler;


import com.database.homework.pangdonglaishop.VO.Result;
import com.database.homework.pangdonglaishop.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j  //日志注解
@RestControllerAdvice  //注册全局异常处理类
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException e) {
        log.error("业务异常信息：{}",e.getMessage());
        return Result.error(e.getMessage());
    }


    /**
     * 数据库异常处理
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException e) {
        log.error("数据库异常信息：{}",e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 参数校验异常处理 (@RequestBody + @Valid)
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("参数校验异常：{}", msg);
        return Result.error(msg);
    }

    /**
     * 参数校验异常处理 (GET请求/@ModelAttribute + @Valid)
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BindException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("参数校验异常：{}", msg);
        return Result.error(msg);
    }

}
