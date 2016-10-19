package com.redis.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ConnectionResolver {
    public List<Map> read(){
        try {
            File redis_connection_xml = new File(ClassLoader.getSystemResource("redis_connection.xml").toURI());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(redis_connection_xml);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("connection");

            List connectionList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Map<String, String> map = new HashMap<>();
                Element node = (Element) nodeList.item(i);
                map.put("name", node.getElementsByTagName("name").item(0).getTextContent());
                map.put("username", node.getElementsByTagName("username").item(0).getTextContent());
                map.put("password", node.getElementsByTagName("password").item(0).getTextContent());
                connectionList.add(map);
            }
            return connectionList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    public static void main(String[] args) {
        ConnectionResolver connectionResolver = new ConnectionResolver();
        List<Map> list = connectionResolver.read();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            System.out.println(String.format("name: %s, username: %s, password: %s", map.get("name"), map.get("username"), map.get("password")));
        }
    }
}
