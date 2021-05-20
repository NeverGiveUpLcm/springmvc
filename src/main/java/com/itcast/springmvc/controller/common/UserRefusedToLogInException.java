package com.itcast.springmvc.controller.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ResponseStatus一般是配合自定义异常来做的,当我们抛出我们自定义的异常的时候,可以返回页面特定的错误码和错误信息提示
 */
@ResponseStatus(reason = "用户拒绝登录",value = HttpStatus.BAD_GATEWAY)
public class UserRefusedToLogInException extends RuntimeException{

    public UserRefusedToLogInException(String message) {
        super(message);
    }
}
