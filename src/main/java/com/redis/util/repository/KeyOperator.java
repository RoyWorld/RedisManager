package com.redis.util.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/10/13.
 */
@Component
public class KeyOperator<K, V> {

//    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
//
//    private static final RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
//
//    private volatile static KeyOperator keyOperator;
//
//    public static KeyOperator getKeyOperator(){
//        if (keyOperator == null){
//            synchronized (HashMapOperator.class){
//                if (keyOperator == null){
//                    keyOperator = new KeyOperator();
//                }
//            }
//        }
//        return keyOperator;
//    }

    @Autowired
    private RedisTemplate redisTemplate;

    private  BoundValueOperations<K, V> getBoundValueOps(final K key){
        return redisTemplate.boundValueOps(key);
    }

    // Key

    public void del(final K key) {
        redisTemplate.delete(key);
    }

    public void del(final Collection<K> keys) {
        redisTemplate.delete(keys);
    }

    public Boolean exists(final K key) {
        return redisTemplate.hasKey(key);
    }

    public Boolean expire(final K key,final long timeout,final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public void expireAt(final K key,Date date) {
        redisTemplate.expireAt(key, date);
    }

    public Set<K> keys(final K pattern) {
        return redisTemplate.keys(pattern);
    }

    public String type(final K key) {
        return redisTemplate.type(key).code();
    }

    public V get(final K key) {
        BoundValueOperations<K,V> ops  = this.getBoundValueOps(key);
        return ops.get();
    }

    public V getSet(final K key,final V value) {
        BoundValueOperations<K,V> ops  = this.getBoundValueOps(key);
        return ops.getAndSet(value);
    }

    public Long incr(final K key,final long delta) {
        BoundValueOperations<K,V> ops  = this.getBoundValueOps(key);
        return ops.increment(delta);
    }

    public void set(final K key,final V value) {
        BoundValueOperations<K,V> ops  = this.getBoundValueOps(key);
        ops.set(value);
    }

    public void set(final K key,final V value,final long timeout,final TimeUnit unit) {
        BoundValueOperations<K,V> ops  = this.getBoundValueOps(key);
        ops.set(value, timeout, unit);
    }

    public void rename(final K oldkey, final K newKey){
        redisTemplate.rename(oldkey, newKey);
    }

    public Long getExpire(final K key){
        return redisTemplate.getExpire(key);
    }
}
