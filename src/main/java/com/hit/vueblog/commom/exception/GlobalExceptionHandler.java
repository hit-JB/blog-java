package com.hit.vueblog.commom.exception;

import com.hit.vueblog.commom.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常");
        return Result.fail(e.getMessage());
    }
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handler(ShiroException e){
        log.error("运行时异常");
        return Result.fail(401,"认证失败",null);
    }

    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("---实体校验异常--");
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(error.getDefaultMessage());
    }
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("---Assert异常--");
        log.error(e.getMessage());
        return Result.fail(e.getMessage());
    }
}
