package com.itcast.springmvc.utils;

import com.itcast.springmvc.entity.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 自定义类型转换器
 *
 * @author lichunmiao
 * @date 2020/7/9
 */
public class MyConverter implements Converter<String,Book> {
    /**
     * 自定义的转换规则
     */
    @Override
    public Book convert(String source) {
        Book book = new Book();
        if (!StringUtils.isEmpty(source)){
            String[] split = source.split("-");
            //todo 为book属性赋值
        }
        return null;
    }
}
