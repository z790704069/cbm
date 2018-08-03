package com.kooola.cloudbookmark.common.exception;

import com.kooola.cloudbookmark.common.RestResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by march on 2018/7/31.
 * 异常处理类
 */
@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MyException.class)
    public RestResponseModel MyExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response){
        MyException exception = (MyException)e;
        return new RestResponseModel(exception.getMessage());
    }
}
