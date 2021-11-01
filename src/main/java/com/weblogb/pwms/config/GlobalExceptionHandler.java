package com.weblogb.pwms.config;

import com.weblogb.pwms.model.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author: Jiajiajia
* @Date: 2021/10/27
* @Description: 全局异常处理
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 只要出现异常就退出登录，跳转登录页面
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result resolveConstraintViolationException(Exception ex) {
        ex.printStackTrace();
        return Result.ofError("请重新登录后重试！");
    }
}