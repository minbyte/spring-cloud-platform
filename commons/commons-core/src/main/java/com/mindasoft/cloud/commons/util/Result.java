package com.mindasoft.cloud.commons.util;

import com.mindasoft.cloud.commons.enums.CommonStateEnum;

import java.io.Serializable;

/**
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/8/6 16:39
 * @version: 1.0.0
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Result(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public static Result fail(){
        Result result = new Result();
        result.setCode(FAIL);
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setCode(FAIL);
        result.setMsg(msg);
        return result;
    }

    public static Result fail(CommonStateEnum commonStateEnum){
        Result result = new Result();
        result.setCode(commonStateEnum.getCode());
        result.setMsg(commonStateEnum.getMessage());
        return result;
    }

    public static Result ok(){
        return new Result();
    }

    public static Result ok(Object data){
        return new Result(data);
    }

    public static Result ok(Object data, String msg){
        return new Result(data, msg);
    }

    public boolean isOk(){
        return 0==getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}