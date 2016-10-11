package com.redis.crawler;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Command {
    private String group;
    private String name;
    private String args;
    private String summary;
    private String href;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Command{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", args='" + args + '\'' +
                ", summary='" + summary + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
