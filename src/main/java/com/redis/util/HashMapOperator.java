package com.redis.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/9.
 */
public class HashMapOperator<K, V>{

    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

    private static final RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

    private final ReflectUtil<V> reflectUtil = new ReflectUtil<>();

    private volatile static HashMapOperator hashMapOperator;

    public static HashMapOperator getHashMapOperator(){
        if (hashMapOperator == null){
            synchronized (HashMapOperator.class){
                if (hashMapOperator == null){
                    hashMapOperator = new HashMapOperator();
                }
            }
        }
        return hashMapOperator;
    }

    private <HK, HV> BoundHashOperations<K, HK, HV> getBoundHashOps(K key) {
		return redisTemplate.boundHashOps(key);
	}

    public void hSet(final K key,final K hk, final V hv) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        ops.put(hk, hv);
    }

    public void hSet(final K key,final Map<K,V> map) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        ops.putAll(map);
    }

    public void hSet(final K key, final V object){
        Map map = reflectUtil.object2Map(object);
        this.hSet(key, map);
    }

    public Map<K,V> hGet(final K key) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        return ops.entries();
    }

    public V hGet(final K key, final Class clazz) {
        Map map = this.hGet(key);
        return (V)reflectUtil.map2Object(map, clazz);
    }

    public V hGet(final K key,final K hKey) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        return ops.get(hKey);
    }

    public void hDel(final K key,final Object... hKeys) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        ops.delete(hKeys);
    }

    public Boolean hExists(final K key,final K hKeys) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        return ops.hasKey(hKeys);
    }

    public Long hLen(final K key) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        return ops.size();
    }

    public List<V> hVals(final K key) {
        BoundHashOperations<K, K, V>  ops  = this.getBoundHashOps(key);
        return ops.values();
    }
}
