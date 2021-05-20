package com.itcast.springmvc.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author lichunmiao
 * @date 2020/9/8
 */
public class MyLocalResolver implements LocaleResolver {
    /**
     * 获取区域信息
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale;
        String local = request.getParameter("local");
        if (!StringUtils.isEmpty(local)){
            locale = new Locale("zh","CN");
        }else{
            locale = request.getLocale();
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
