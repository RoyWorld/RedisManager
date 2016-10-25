package com.redis.util.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
@Component
public class ConfigOperator {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    DymanicRedisFactory dymanicRedisFactory;

    public List getDatabaseNum(){
        List list = (List) redisTemplate.execute((RedisCallback) (RedisConnection connection) -> connection.getConfig("databases"));
        return list;
    }

    public boolean selectDatabase(int index){
        dymanicRedisFactory.setDatabase(index);
        return true;
    }
}
