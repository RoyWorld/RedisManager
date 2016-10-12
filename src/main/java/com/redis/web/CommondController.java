package com.redis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping(value = "/command")
public class CommondController {

    @RequestMapping()
    public String index() {
        // 返回知识点管理列表页面question_knowledge_list
        System.out.println("11111");
        return "command_list";
    }

    @RequestMapping(value = "/list")
    public void list(){
        System.out.println("hello world");
    }
}
