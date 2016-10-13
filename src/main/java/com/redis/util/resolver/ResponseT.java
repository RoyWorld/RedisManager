package com.redis.util.resolver;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/4.
 */

public class ResponseT<T> implements Serializable {
    private String rtnCode;
    private String msg;
//    private String developMsg;
//    private String uri;
    private long ts;
    private T bizData;

    public ResponseT() {
        this.ts = System.currentTimeMillis();
    }

    public ResponseT(RtnCodeEnum rtnCode) {
        this.ts = System.currentTimeMillis();
        this.rtnCode = rtnCode.getValue();
        this.msg = rtnCode.getDesc();
    }


    public String getRtnCode() {
        return this.rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public String getUri() {
//        return this.uri;
//    }
//
//    public void setUri(String uri) {
//        this.uri = uri;
//    }

    public T getBizData() {
        return this.bizData;
    }

    public void setBizData(T bizData) {
        this.bizData = bizData;
    }

//    public String getDevelopMsg() {
//        return this.developMsg;
//    }
//
//    /** @deprecated */
//    public void setDevelopMsg(String developMsg) {
//        this.developMsg = developMsg;
//    }

    /** @deprecated */
    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getTs() {
        return this.ts;
    }
}
