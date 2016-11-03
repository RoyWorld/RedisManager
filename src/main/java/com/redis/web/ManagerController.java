package com.redis.web;

import com.redis.util.repository.ConfigOperator;
import com.redis.util.repository.HashMapOperator;
import com.redis.util.repository.KeyOperator;
import com.redis.util.resolver.BizData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

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

    @Autowired
    HashMapOperator hashMapOperator;


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

    @ResponseBody
    @RequestMapping(value = "/keySelect")
    public Object keySelect(String key, String databaseName){
        configOperator.selectDatabase(Integer.valueOf(databaseName.substring(databaseName.length() - 1)));
        String type = keyOperator.type(key);
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("ttl", keyOperator.getExpire(key));
        switch (type){
            case "list":
            case "hash":
                map.put("type", type);
                Map entries = hashMapOperator.hGet(key);
                BizData<Map.Entry> bizData = new BizData<>();
                bizData.setRows(new ArrayList<>(entries.entrySet()));
                map.put("value", bizData);
                map.put("size", entries.size());
                return map;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/keyRename")
    public Object keyReme(String oldKey, String newKey){
        Map<String, String> map = new HashMap<>();
        if (!keyOperator.exists(newKey)){
            keyOperator.rename(oldKey, newKey);
            map.put("request", "success");
        } else{
            map.put("request", "error");
        }
        return map;
    }
}
