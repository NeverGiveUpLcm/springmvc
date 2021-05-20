package com.itcast.springmvc.controller;

import com.itcast.springmvc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

/**
 * @author lichunmiao
 * @date 2020/7/2
 * helloworld细节:
 * 1、运行流程
 *      1)、客户端点击链接会发送hello请求
 *      2)、来到tomcat服务器
 *      3)、springmvc的前端控制器收到所有请求
 *      4)、来看请求地址和@RequestMapping标注的哪个匹配,来找到到底使用哪个类的哪个方法
 *      5)、前端控制器找到了目标处理器类和目标方法,直接利用反射执行目标方法
 *      6)、方法执行完成之后会有一个返回值,springmvc认为这个返回值就是要去的页面地址
 *      7)拿到方法返回值以后,用视图解析器进行拼串得到完整的页面地址
 *      8)、拿到页面地址,前端控制器帮我们转发页面
 *
 * 2、@RequestMapping:
 *      就是告诉springmvc这个方法用来处理什么请求
 *      这个/是可以省略,即使省略了,也是默认从当前项目下开始
 *
 * 3、如果不指定配置文件的位置:
 *      <init-param>
 *       <param-name>contextConfigLocation</param-name>
 *       <param-value>classpath:springmvc.xml</param-value>
 *     </init-param>
 *     就回去默认找一个配置文件:
 *     <servlet-name>dispatcherServlet</servlet-name>
 *     /WEB-INF/dispatcherServlet-servlet.xml
 *     需要我们在web应用的/WEB-INF下创建一个名叫 前端控制器-servlet.xml的配置文件
 *
 */
@Controller
//为当前类的所有方法的请求地址指定一个基准路径
@RequestMapping("/haha")
public class HelloController {
    /**
     * Ant 风格资源地址支持 3 种匹配符：
     * ?:匹配文件名中的一个字符,0个或者多个都不行
     * *:匹配文件名中的任意字符或者一层路径
     * **:** 匹配多层路径
     * 精确匹配和模糊匹配的情况下,精确匹配优先。
     */
    @RequestMapping(value = "/hello0?")
    public String  hello01(){
        //视图解析器会帮助我们拼接成WEB-INF/pages/success.jsp
        return "success";
    }
    @RequestMapping(value = "/hello0*")
    public String  hello02(){
        //视图解析器会帮助我们拼接成WEB-INF/pages/success.jsp
        return "success";
    }
    @RequestMapping(value = "/*/hello0*")
    public String  hello03(){
        //视图解析器会帮助我们拼接成WEB-INF/pages/success.jsp
        return "success";
    }



    @RequestMapping("/user/{id}")
    public String  pathVariableTest(@PathVariable("id") String id){
        System.out.println(id);
        return id;
    }

    /**
     * Springmvc如何获取请求带来的各种信息
     * 默认方式获取请求参数:
     *      直接给方法入参上写一个和请求参数名相同的变量。这个变量就是用来接收请求参数的值。
     * @RequestParam:获取请求参数的;参数默认是必须带的
     * @RequestParam("user") String username =
     * username = request.getParam("user")
     * 三个属性:
     * value: 指定要获取的参数的key
     * required: 这个参数是否是必须的
     * defaultValue: 默认值
     *
     * @RequestHeader: 获取请求头中某个参数的值
     * @RequestHeader("User-Agent") String userAgent =
     * userAgent = request.getHeader("User-Agent")
     * 如果请求头中没有这个值就会报错
     *
     * @CookieValue: 获取某个cookie的值
     */
    @RequestMapping(value = "/handle01")
    public String  hello04(@RequestParam("user") String username,
                           @RequestHeader("User-Agent") String userAgent,
                           @CookieValue("JSESSIONID") String cookie){
        return "success";
    }

    /**
     * @ModelAttribute 标注的方法会提前运行并把方法的运行结果放在隐含模型当中
     *  放的时候会使用一个key:
     *      如果@ModelAttrbute("book)指定了,就用指定的book
     *      如果没指定就用返回值类型的首字母小写
     *      或者是map或者model中的key
     *
     */

    @RequestMapping(value = "/updateBook")
    public String  updateBook(@ModelAttribute("book") Book book){
        return "success";
    }

    @ModelAttribute
    public void beforeUpdateBook(Map<String,Object> map){
        map.put("book",new Book());
        System.out.println("目标方法之前先运行");
    }

    /**
     * springmvc可以直接在参数上写原生API
     * HttpServletRequest :
     * HttpSession:
     * HttpServletResponse:
     * java.security.Principal: https协议相关
     * Locale : 国际化有关的区域信息对象
     * InputStream: 输入流
     *      ServletInputStream inputStream = httpServletRequest.getInputStream();
     * OutputStream: 输出流
     *      ServletOutputStream outputStream = httpServletResponse.getOutputStream();
     * Reader: 字符输入流
     *      BufferedReader reader = httpServletRequest.getReader();
     *
     * Writer: 字符输出流
     *      PrintWriter writer = httpServletResponse.getWriter();
     *
     *
     *
     */
    @RequestMapping("/handle03")
    public String handle03(HttpServletRequest httpServletRequest,
                           HttpSession httpSession,
                            HttpServletResponse httpServletResponse){
        try {
            ServletInputStream inputStream = httpServletRequest.getInputStream();
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            BufferedReader reader = httpServletRequest.getReader();
            PrintWriter writer = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }






    @RequestMapping("/hello")
    public String hello(){
        //这样写,经过视图解析器的解析,会跳转到/WEB-INF/pages/hello.jsp
//        return "hello";
        //我们想跳转的是/WEB-INF/hello.jsp,可以使用相对路径
        return "../../hello";
    }

    /**
     * forward: 转发到一个页面
     * /hello.jsp: 转发到当前项目下的hello
     * forward:/hello.jsp: 这样的写法,不会经过视图解析器的解析进行拼串
     * forward:/handle01:还可以进行访问路径的转发
     */
    @RequestMapping("/handle01")
    public String handle01(){
       return "forward:/hello.jsp";
    }
    @RequestMapping("/handle02")
    public String handle02(){
        return "forward:/handle01";
    }

    /**
     * 重定向: redirect:重定向的路径
     * /hello.jsp: 代表就是从当前项目下开始,springmvc会为路径自动的拼接上项目名
     *
     * 原生的Servlet重定向/路径需要加上项目名才能成功
     * response.sendRedirect("/hello.jsp")
     * redirect:/handle03: redirect还可以重定向请求路径
     * 有前缀的转发和重定向操作,配置的视图解析器就不会进行拼串
     */
    @RequestMapping("/handle03")
    public String handle03(){
        return "redirect:/hello.jsp";
    }

    @RequestMapping("/handle04")
    public String handle04(){
        return "redirect:/handle03";
    }


    /**
     * org.springframework.beans.factory.xml包下的BeanDefinitionParser接口,包含了很多的parser,
     * 它主要是用来解析springmvc与spring的配置文件中的各种标签的
     * 我们可以通过 AnnotationDrivenBeanDefinitionParser类,
     * 观察springmvc解析 mvc:annotation-driven标签到底做了哪些事情？
     * 1、添加了好多的组件
     *
     */

    /**
     * 为什么说 <mvc:default-servlet-handler/> 和 <mvc:annotation-driven/>为mvc配置的标配？
     * 现象:
     * 1)如果两个标签都没有配置,动态资源(@RequestMapping映射的资源能访问,静态资源(.html,.js,.img)等不能访问)
     * 2)加上<mvc:default-servlet-handler/> 静态资源可以正常访问,动态资源访问不了
     * 3)加上<mvc:annotation-driven/> 静态资源和动态资源才能同时访问
     */
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/login")
    public String login(String local,HttpSession httpSession,HttpServletRequest request){
        /**
         * 配置了SessionLocaleResolver之后,我们可以通过在session加入local属性
         * SessionLocaleResolver会从session中获取locale对象封装进SessionLocaleResolver中。
         */
        Locale locale;
        if (!StringUtils.isEmpty(local)){
            locale = new Locale("zh","CN");
        }else{
            locale = request.getLocale();
        }
        httpSession.setAttribute(SessionLocaleResolver.class.getName() + ".LOCALE",locale);
        return "login";
    }
}

