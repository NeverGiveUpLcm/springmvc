package com.itcast.springmvc.utils;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义视图
 *
 * @author lichunmiao
 * @date 2020/7/8
 */
public class MyView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(model);
        response.setContentType("text/html");
        response.getWriter().write("即将展现精彩内容");
    }
}
