package com.redis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/12.
 */
@Controller
@RequestMapping(value = "/redis")
public class HomeController {

    @RequestMapping(value = "/home")
    public String index() {
        // 返回home主页
        return "home";
    }

    @RequestMapping(value = "/error")
    public String error() {
        // 返回home主页
        return "error";
    }
}
