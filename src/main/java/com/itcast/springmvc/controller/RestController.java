package com.itcast.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lichunmiao
 * @date 2020/7/3
 * 从页面发起PUT、DELETE形式的请求,spring提供了对Rest风格的支持
 * 1)、Springmvc中有一个Filter,它可以把普通的请求转化成为规定形式的请求;我们需要配置这个Filter
 *
 * <filter>
 *     <filter-name>HiddenHttpMethodFilter</filter-name>
 *     <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
 *   </filter>
 *   <filter-mapping>
 *     <filter-name>HiddenHttpMethodFilter</filter-name>
 *     <url-pattern>/*</url-pattern>
 *   </filter-mapping>
 *
 * 2)如何发其它形式的请求?
 *  按照以下要求:
 *      1、创建一个post类型的表单
 *      2、表单中携带一个_method的参数
 *      3、这个_method的值就是PUT、DELETE
 */
@Controller
@RequestMapping("/rest")
public class RestController {
    /**
     * 查询图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
    public String getBook(@PathVariable("id") String id){
        System.out.println("查询到了" + id + "号图书");
        return "success";
    }

    /**
     * 新增图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.POST)
    public String addBook(@PathVariable("id") String id){
        System.out.println("新增了" + id + "号图书");
        return "success";
    }

    /**
     * 更新图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.PUT)
    public String updateBook(@PathVariable("id") String id){
        System.out.println("更新了" + id + "号图书");
        return "success";
    }

    /**
     * 删除图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") String id){
        System.out.println("删除了" + id + "号图书");
        return "success";
    }

}
