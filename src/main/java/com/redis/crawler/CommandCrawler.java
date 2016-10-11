package com.redis.crawler;

import com.redis.util.HashMapOperator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
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

    public static void main(String[] args) throws IOException {
        List<Command> commandList = CommandCrawler.crawlerCommandInfo();
        HashMapOperator<String, Command> hashMapOperator = HashMapOperator.getHashMapOperator();
        for (Command command: commandList){
            System.out.println(command.toString());
            String key = command.getGroup() + ":" + command.getName();
            hashMapOperator.hSet(key, command);
        }
    }
}
