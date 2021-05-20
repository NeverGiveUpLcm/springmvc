package com.itcast.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lichunmiao
 * @date 2020/7/8
 */
@Controller
public class MyViewResolveController {

    @RequestMapping("/handleplus")
    public String handleplus() {
        return "meinv:gaoqing";
    }
}
