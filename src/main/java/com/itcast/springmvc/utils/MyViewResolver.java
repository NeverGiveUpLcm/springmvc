package com.itcast.springmvc.utils;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @author lichunmiao
 * @date 2020/7/8
 * 自定义视图解析器
 * 实现了Ordered接口,就可以实现视图解析器的执行的先后顺序
 */
public class MyViewResolver implements ViewResolver, Ordered {
    private Integer order = 0;

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith("meinv:")) {
            return new MyView();
        } else {
            //如果不能处理返回null即可
            return null;

        }
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
