package com.redis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/17.
 */
@Controller
@RequestMapping(value = "/redis")
public class ManagerController {
    @RequestMapping(value = "/manager")
    public String index(){
        return "manager";
    }
}
