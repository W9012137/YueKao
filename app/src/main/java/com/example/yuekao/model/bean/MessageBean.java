package com.example.yuekao.model.bean;

/**
 * Created by 王有道 on 2018/4/7.
 */

public class MessageBean<T> {

    /**
     * msg : 请求成功
     * code : 0
     * data : []
     */

    private String msg;
    private String code;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
