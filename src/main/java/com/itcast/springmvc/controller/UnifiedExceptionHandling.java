package com.itcast.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * UnifiedExceptionHandling这里类用来集中处理所有发生的异常
 * 1、集中处理所有异常的类要加入到ioc容器当中
 * 2、@ControllerAdvice 用来声明这个类是专门用来处理异常的
 * 3、全局的异常处理和本类的异常处理同时存在,本类优先。
 */
@ControllerAdvice
public class UnifiedExceptionHandling {
    /**
     * 告诉springmvc这个方法专门处理这个类发生的异常
     * 1、方法参数上写一个Exception,用来接收发生的异常
     * 2、要携带异常信息,参数位置只能接收Exception这一个参数,不能写Model参数
     * 3、我们可以选择返回ModelAndView对象就可以了。
     * 4、如果有多个@ExceptionHandler都能处理这个异常,精确优先。
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView handleException1(Exception exception) {
        //视图解析器同样会进行解析
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("ex", exception);
        return modelAndView;
    }

    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public ModelAndView handleException2(Exception exception) {
        //视图解析器同样会进行解析
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("ex", exception);
        return modelAndView;
    }
}
