package com.redis.util.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Protocol;

/**
 * Created by Administrator on 2016/10/19.
 * 利用装饰器模式将RedisConnectionFactory重新包装成一个可动态配置database的redisfactory
 * 在原有的基础上增加动态切换database的接口
 */
public class DymanicRedisFactory implements RedisConnectionFactory {

    private JedisConnectionFactory jedisConnectionFactory;

    public DymanicRedisFactory(JedisConnectionFactory jedisConnectionFactory){
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Override
    public JedisConnection getConnection() {
        return jedisConnectionFactory.getConnection();
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        return jedisConnectionFactory.getConvertPipelineAndTxResults();
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return jedisConnectionFactory.getSentinelConnection();
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return jedisConnectionFactory.translateExceptionIfPossible(ex);
    }


    public void setDatabase(int index){
        jedisConnectionFactory.setDatabase(index);
    }
}
