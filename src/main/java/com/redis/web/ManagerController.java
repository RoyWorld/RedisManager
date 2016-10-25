package com.redis.web;

import com.redis.util.repository.ConfigOperator;
import com.redis.util.repository.KeyOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Administrator on 2016/10/17.
 */
@Controller
@RequestMapping(value = "/redis/manager")
public class ManagerController {

    @Autowired
    ConfigOperator configOperator;

    @Autowired
    KeyOperator keyOperator;


    @RequestMapping(value = "")
    public String index(){
        return "manager";
    }

    @ResponseBody
    @RequestMapping(value = "/database")
    public List database(){
        List l = configOperator.getDatabaseNum();
        List<Map<String, Object>> databaseList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt((String) l.get(1)); i++) {
            boolean result = configOperator.selectDatabase(i);
            Set<String> keySet = keyOperator.keys("*");
            Map<String, Object> map = new HashMap<>();
            map.put("name", "db" + i);
            map.put("keys", keySet);
            map.put("display", false);
            databaseList.add(map);
        }
        return databaseList;
    }
}
