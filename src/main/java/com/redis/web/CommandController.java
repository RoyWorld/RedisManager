package com.redis.web;

import com.redis.crawler.Command;
import com.redis.util.repository.HashMapOperator;
import com.redis.util.repository.KeyOperator;
import com.redis.util.repository.SetOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping(value = "/redis/command")
public class CommandController {

    @Autowired
    private HashMapOperator hashMapOperator;

    @Autowired
    private KeyOperator keyOperator;

    @Autowired
    private SetOperator setOperator;

    @RequestMapping(value = "")
    public String index(){
        return "command_list";
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public Object commandList(String group, String name){
        String keys = "command:";
        if (group != null && group != ""){
            keys = keys + group + ":";
        }else {
            keys = keys + "*:";
        }
        if (name != null && name != ""){
            keys = keys + name;
        }
        Set<String> commandKeys = keyOperator.keys(keys + "*");
        List<Command> commandList = new ArrayList<>();
        for (String key : commandKeys){
            Command command = (Command) hashMapOperator.hGet(key, Command.class);
            commandList.add(command);
        }
        return commandList;
    }

    @ResponseBody
    @RequestMapping(value = "/command_group")
    public Object commandOfGroup(){
        Map<String, String> group = hashMapOperator.hGet("groupOfCommands");
        List<Map<String, String>> groupList = new ArrayList<>();
        for(String groupname : group.keySet()){
            Map<String, String> map = new HashMap<>();
            map.put("name", groupname);
            map.put("value", group.get(groupname));
            groupList.add(map);
        }
        return groupList;
    }
}
