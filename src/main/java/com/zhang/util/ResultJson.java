package com.zhang.util;

/**
 * @author by SUNS3T
 * @Classname ResultJson
 * @Description TODO
 */
public class ResultJson {
    private Integer status;
    private String msg;
    private Object obj;

    private ResultJson(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static ResultJson ResData(Integer status, String msg, Object obj){
        return new ResultJson(status,msg,obj);
    }
}
