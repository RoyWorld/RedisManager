package com.redis.util.resolver;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
public class BizData<T> {
    //总页数
    private int total = 0;
    //页码
    private int page = 1;
    //每页记录数
    private int pagesize = 10;
    //总记录数
    private int records = 1;
    //具体数据
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.records = rows.size();
        this.total = rows.size()/pagesize + rows.size()%pagesize > 0 ? 1 : 0;
        this.rows = rows.subList((page-1)*pagesize, page*pagesize < rows.size() ? page*pagesize : rows.size());
    }
}
