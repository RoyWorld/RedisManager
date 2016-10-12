package com.redis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping(value = "/redis")
public class CommandController {

    @RequestMapping(value = "/command")
    public String command_list(){
        return "command_list";
    }

}
