package com.xuan.spring.A20;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controller1 {

    public static final Logger log = LoggerFactory.getLogger(Controller1.class);

    @GetMapping("/test1")
    public ModelAndView test1() {
        log.debug("test1()");
        return null;
    }

    @PostMapping("/test2")
    public ModelAndView test2(@RequestParam("name") String name) {
        log.debug("test2({})",name);
        return null;
    }

    @PutMapping("/test3")
    public ModelAndView test3(@Token String token) {
        log.debug("test3({})",token);
        return null;
    }

    @RequestMapping("/test4")
    @ResponseBody // 将响应体解析到返回实例里 一般是json格式
    public User test4(@Token String token) {
        log.debug("test3({})",token);
        return new User("name",18);
    }




}
