package com.redis.crawler;

import com.redis.util.repository.HashMapOperator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class CommandCrawler {

    public static List<Command> crawlerCommandInfo(){
        try{
            Document doc = Jsoup.connect("http://redis.io/commands").get();
            Elements container = doc.getElementsByClass("container");
            Elements commandElementList = container.get(2).getElementsByTag("li");
            List commandList = new ArrayList<>();
            for (Element commandElement : commandElementList){
                Command command = new Command();
                command.setGroup(commandElement.attr("data-group"));
                command.setName(commandElement.attr("data-name").toUpperCase());
                command.setArgs(commandElement.select("span.args").text());
                command.setSummary(commandElement.select("span.summary").text());
                command.setHref(commandElement.getElementsByTag("a").attr("href"));
                commandList.add(command);
            }
            return commandList;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void setCommand2Redis(){
        List<Command> commandList = CommandCrawler.crawlerCommandInfo();
        HashMapOperator<String, Command> hashMapOperator = new HashMapOperator<>();
        for (Command command: commandList){
            System.out.println(command.toString());
            String key = "command:" + command.getGroup() + ":" + command.getName();
            hashMapOperator.hSet(key, command);
        }
    }

    private void setCommandGroup(){
//        HashMapOperator<String, String> setOperator = HashMapOperator.getHashMapOperator();
//        String[] groups = new String[]{
//                "Cluster",
//                "Connection",
//                "Geo",
//                "Hashes",
//                "HyperLogLog",
//                "Keys",
//                "Lists",
//                "Pub/Sub",
//                "Scripting",
//                "Server",
//                "Sets",
//                "Sorted Sets",
//                "Strings",
//                "Transactions"};
//        List<String> groupList = Arrays.asList(groups);
//        String key = "groupOfCommands";
//        for (String group : groupList){
//            String value = group.toLowerCase();
//            if (group.equals("Hashes")){
//                value = "hash";
//            }
//            if (group.equals("Lists")){
//                value = "list";
//            }
//            if (group.equals("Sorted Sets")){
//                value = "sorted_set";
//            }
//            if (group.equals("Strings")){
//                value = "string";
//            }
//            if (group.equals("Sets")){
//                value = "set";
//            }
//            if (group.equals("Pub/Sub")){
//                value = "pubsub";
//            }
//            if (group.equals("Keys")){
//                value = "generic";
//            }
//            setOperator.hSet(key, group, value);
//        }
    }


    public static void main(String[] args) throws IOException {

    }
}
