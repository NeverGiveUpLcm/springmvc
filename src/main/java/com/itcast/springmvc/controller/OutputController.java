package com.itcast.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author lichunmiao
 * @date 2020/7/4
 * springmvc除过在方法上传入原生的request和session外还能怎么样把数据带给页面？
 * 1)、可以在方法处传入Map、或者Model或者ModelMap。给这些参数里面保存的数据都会放在请求域中。可以在页面获取
 * 关系:
 *      Model、Map、ModelMap最终都是BindingAwareModelMap在工作
 *      相当于给BindingAwareModelMap中保存的东西都会被放在请求域中。
 * 2)、方法的返回值可以变为ModelAndView类型
 *      既包含视图信息(页面地址)也包含模型数据(给页面带的数据)
 *      而且数据是放在请求域当中的
 *
 * 3)、springmvc提供了一个可以临时给Session域中保存数据的方式
 * 使用一个注解 @SessionAttributes(只能标在类上)
 *  @SessionAttributes(value = "msg"):
 *      给BindingAwareModelMap中保存的数据,同时给session中放一份
 *      value指定保存数据时要给session中放的数据的key
 *      types = {String.class}:只要保存的是这种类型的数据,给session中也放一份
 *
 *  后来推荐@SessionAttributes就不要用了,可能会引发异常。
 *  给session中放数据还是使用原生的API比较好
 */
@Controller
@SessionAttributes(value = {"msg"},types = {String.class})
public class OutputController {

    @RequestMapping("/handle01")
    public String handle01(Map map) {
        map.put("msg", "成功");
        return "success";
    }

    @RequestMapping("/handle02")
    public String handle02(Model model) {
        model.addAttribute("msg","成功");
        return "success";
    }
    @RequestMapping("/handle03")
    public String handle03(ModelMap modelMap){
        modelMap.addAttribute("msg","成功");
        return "success";
    }

    @RequestMapping("/handle04")
    public ModelAndView handle04(){
        //之前的返回值我们就叫视图名,视图名是视图解析器帮我们最终拼串得到的页面的真实地址
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("msg","成功");
        return mv;
    }

}
