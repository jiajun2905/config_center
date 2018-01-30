package com.jiajun.config.model;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public class Result<T> {

    private Boolean success;

    private String msg;

    private T data;

    public static <T> Result generateSuccessResult(T data){
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result generateErrorResult(String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
    public static Result generateErrorResult(){
        Result result = new Result();
        result.setSuccess(false);
        return result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
