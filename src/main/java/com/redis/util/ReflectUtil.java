package com.redis.util;

import com.redis.crawler.Book;
import sun.security.jca.GetInstance;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/10.
 */
public class ReflectUtil<V> {

    public Map<String, V> object2Map(V object){
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, V> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                String fieldName = field.getName();
                Method method = clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
                V invokeObject = (V) method.invoke(object, null);
                map.put(fieldName, invokeObject);
            }catch (NoSuchMethodException e){
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public V map2Object(Map<String, V> map, Class clazz){
        try {
            Constructor constructor = clazz.getConstructor();
            V instance = (V) constructor.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                Method method = clazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), field.getType());
                method.invoke(instance, map.get(fieldName));
            }
            return instance;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        Book book = new Book();
        ReflectUtil<Book> reflectUtil = new ReflectUtil();
        Map map = reflectUtil.object2Map(book);
        Book book2 = (Book) reflectUtil.map2Object(map, Book.class);
        System.out.println(book2.toString());
        for (Object key : map.keySet()){
            System.out.println(String.format("Map key: %s, value: %s", key.toString(), map.get(key).toString()));
        }
    }
}
