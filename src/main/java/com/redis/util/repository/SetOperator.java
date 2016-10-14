package com.redis.util.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Administrator on 2016/10/14.
 */
@Component
public class SetOperator<K, V> {
//    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
//
//    private static final RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
//
//    private volatile static SetOperator setOperator;
//
//    public static SetOperator getSetOperator(){
//        if (setOperator == null){
//            synchronized (SetOperator.class){
//                if (setOperator == null){
//                    setOperator = new SetOperator();
//                }
//            }
//        }
//        return setOperator;
//    }

    @Autowired
    private RedisTemplate redisTemplate;

    private  BoundSetOperations<K, V> getBoundSetOps(final K key){
        return redisTemplate.boundSetOps(key);
    }

    public Long sAdd(final K key,final V value) {
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.add(value);
    }

    public Set<V> sDiff(final K key) {
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.diff(key);
    }

    public Set<V> sMembers(final K key) {
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.members();
    }

    public Boolean sIsMember(final K key,final V value ){
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.isMember(value);
    }

    public V sPop(final K key) {
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.pop();
    }

    public Long sRem(final K key, final V value ) {
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.remove(value);
    }

    public Long sCard(K key) {
        BoundSetOperations<K,V> ops  = this.getBoundSetOps(key);
        return ops.size();
    }
}
