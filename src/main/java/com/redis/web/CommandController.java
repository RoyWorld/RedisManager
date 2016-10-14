package com.redis.web;

import com.redis.crawler.Command;
import com.redis.util.repository.HashMapOperator;
import com.redis.util.repository.KeyOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping(value = "/redis")
public class CommandController {

    @Autowired
    private HashMapOperator hashMapOperator;

    @Autowired
    private KeyOperator keyOperator;

    @RequestMapping(value = "/command")
    public String command_list(){
        return "command_list";
    }

    @ResponseBody
    @RequestMapping(value = "/command_list")
    public Object commandObject(){
        Set<String> keys = keyOperator.keys("command:*");
        List<Command> commandList = new ArrayList<>();
        for (String key : keys){
            Command command = (Command) hashMapOperator.hGet(key, Command.class);
            commandList.add(command);
        }
        return commandList;
    }
}
