package com.redis.util;

import com.redis.crawler.Command;
import com.redis.util.repository.HashMapOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Example {

    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
    }

    public static void main(String[] args) throws MalformedURLException {
        HashMapOperator<String, Command> hashMapOperator = new HashMapOperator<>();
        Command command = hashMapOperator.hGet("string:APPEND", Command.class);
        System.out.println(command.toString());
    }
}
