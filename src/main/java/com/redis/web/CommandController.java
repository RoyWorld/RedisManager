package com.redis.web;

import com.redis.crawler.Command;
import com.redis.util.HashMapOperator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/command_list")
    public Object commandObject(){
        HashMapOperator<String, Command> hashMapOperator = HashMapOperator.getHashMapOperator();
        Command command = hashMapOperator.hGet("hash:HSET", Command.class);
        return command;
    }
}
