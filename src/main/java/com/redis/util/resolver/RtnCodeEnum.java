package com.redis.util.resolver;

/**
 * Created by Administrator on 2015/12/4.
 */
public enum RtnCodeEnum {
    SUCCESS("success", "0000000", "请求成功"),
    UNKNOW("unknow", "1000001", "未知异常"),
    APP_OVER_INVOCATION_LIMIT("over_limit", "1000002", "请不要频繁调用接口，您的调用次数已超过了限制!"),
    PARAMETER_ERROR("parameter_error", "1000003", "请求参数错误"),
    NET_ERROR("net_error", "1000004", "网络异常，请稍后重试");

    private String code;
    private String value;
    private String desc;

    private RtnCodeEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public static RtnCodeEnum codeOf(String code) {
        if(code == null) {
            return NET_ERROR;
        } else {
            RtnCodeEnum[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                RtnCodeEnum rtnCodeEnum = arr$[i$];
                if(code.equals(rtnCodeEnum.getCode())) {
                    return rtnCodeEnum;
                }
            }

            return NET_ERROR;
        }
    }

    public static RtnCodeEnum nameOf(String name) {
        if(name == null) {
            return NET_ERROR;
        } else {
            RtnCodeEnum[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                RtnCodeEnum rtnCodeEnum = arr$[i$];
                if(name.equals(rtnCodeEnum.name())) {
                    return rtnCodeEnum;
                }
            }

            return NET_ERROR;
        }
    }

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
