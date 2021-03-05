package com.suxs.common;

import java.util.HashMap;
import java.util.Map;

public class Result {
    // 状态码
    private int status;
    // 提示信息
    private String message;

    // 封装有效数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Result success() {
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setStatus(400);
        result.setMessage("fail");
        return result;
    }

    public static Result noPermission() {
        Result result = new Result();
        result.setStatus(401);
        result.setMessage("no permission");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(500);
        result.setMessage("error");
        return result;
    }

    public static Result code(int code){
        Result result = new Result();
        result.setStatus(code);
        result.setMessage("exception");
        return result;
    }

    public Result add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
