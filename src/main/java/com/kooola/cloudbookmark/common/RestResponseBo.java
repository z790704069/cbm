package com.kooola.cloudbookmark.common;

/**
 * Created by march on 2018/7/26.
 */

import java.io.Serializable;

/**
 * rest返回对象
 *^
 * @param <T>
 */
public class RestResponseBo<T> implements Serializable{

    /**
     * 服务器响应数据
     */
    private T model;


    /**
     * 状态码描述
     */
    private String msg;

    /**
     * 状态码
     */
    private String code;

    /**
     * 服务器响应时间
     */
    private long timestamp;




    public RestResponseBo(String code, T payload){
        setCodeMsg(code);
        setModel(payload);
    }

    public RestResponseBo(String code){
        setCodeMsg(code);
    }

    private void setCodeMsg(String code){
        setCode(code);
        setMsg(ResultConstants.resultInfosMap.get(code));
        setTimestamp(System.currentTimeMillis() / 1000);
    }


    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}